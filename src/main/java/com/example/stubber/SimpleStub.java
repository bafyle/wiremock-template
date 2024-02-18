package com.example.stubber;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;

import wiremock.com.google.common.net.MediaType;

public class SimpleStub extends BaseStub{

    public SimpleStub(WireMockServer wms) {
        super(wms);
    }

    @Override
    public MappingBuilder stub() throws Exception {
        InputStream body = getClass().getClassLoader().getResourceAsStream("files/current_date.json");
        return get("/hello")
            .willReturn(
                aResponse()
                .withStatus(200)
                .withHeader("Content-Type", MediaType.JSON_UTF_8.toString())
                .withBody(new String(body.readAllBytes(), StandardCharsets.UTF_8))
                .withTransformers("response-template")
                );
    }
    
}
