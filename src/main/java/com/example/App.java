package com.example;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.stubber.BaseStubber;
import com.example.stubber.SimpleStub;


public class App 
{
    private static final int PORT = 8080; // Port on which the server will run

    private static List<Class<? extends BaseStubber>> getStubberClasses()
    {
        List<Class<? extends BaseStubber>> classes = new ArrayList<>();
        classes.add(SimpleStub.class);

        return classes;
        
    }
    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(PORT).templatingEnabled(true));
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                wireMockServer.stop();
                System.out.println("WireMock server stopped");
            }
        });
        try {
            wireMockServer.start();
            System.out.println("WireMock server started on port " + PORT);

            List<Class<? extends BaseStubber>> classes = getStubberClasses();
            for(var class_ : classes)
            {
                BaseStubber instance = class_.getDeclaredConstructor().newInstance();
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

    private static Arguments parseCLArguments(String[] args)
    {
        Map<String, String> parsedArguments = new HashMap<>();
        for(int i = 0; i < args.length - 1; i+=2)
        {
            parsedArguments.put(args[i], args[i+1]);
        }
        Field[] fields = Arguments.class.getDeclaredFields();
        for(Field field : fields)
        {
            if(field.getName())
        }
        
    }
}
