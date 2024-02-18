package com.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.Extension;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.helper.StringLengthHelper;
import com.example.stubber.BaseStub;
import com.example.stubber.SimpleStub;
import com.example.stubber.StubWithCustomTransformer;
import com.example.trasnformer.UpperCaseTransformer;

public class App 
{
    private static final int DEFAULT_PORT = 9090;
    private static WireMockServer wireMockServer;

    /*
     * This method is for registering any stubs. All you need to do is add your class to the list
     * Note: All classes must extend the BaseStub abstract class and have a constructor with WireMockServer as parameter
     * and call the super constructor
     */
    private static List<Class<? extends BaseStub>> getStubberClasses()
    {
        List<Class<? extends BaseStub>> classes = new ArrayList<>();
        classes.add(SimpleStub.class);
        classes.add(StubWithCustomTransformer.class);

        return classes;
        
    }

    /*
     * This method is for registering all extensions to WireMock, such as handlebars extensions and custom transformers.
     * All you need to do to register a new extension is add new instance from your extension to the list
     * Note: All instances must be instances of classes that extend the Extension class
     */
    private static List<Extension> getExtensions()
    {
        List<Extension> extensions = new ArrayList<>();
        extensions.add(new StringLengthHelper());
        extensions.add(new UpperCaseTransformer());
        return extensions;
    }

    public static void main(String[] args) {
        try {
            setup(args);
            wireMockServer.start();
            System.out.println("WireMock server started on port %s".formatted(wireMockServer.port()));

            List<Class<? extends BaseStub>> classes = getStubberClasses();
            for(var class_ : classes)
            {
                BaseStub instance = class_.getConstructor(wireMockServer.getClass()).newInstance(wireMockServer);
                stubFor(instance.stub());
            }

            while (true) {
                Thread.sleep(10); // Sleep to prevent CPU hogging
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wireMockServer.stop();
            System.out.println("WireMock server stopped");
        }
    }

    private static void setup(String[] args) throws IllegalArgumentException, IllegalAccessException
    {
        int port = DEFAULT_PORT;
        for(int i = 0; i < args.length-1; i+=2)
        {
            var arg = args[i];
            if(arg.equals("--port"))
            {
                Objects.requireNonNull(args[i+1]);
                port = Integer.parseInt(args[i+1]);
            }
        }
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig()
            .port(port)
            .templatingEnabled(true)
            .extensions(getExtensions().toArray(new Extension[0]))
            );
        WireMock.configureFor(port);
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                wireMockServer.stop();
                System.out.println("WireMock server stopped");
            }
        });
    }
}
