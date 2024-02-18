package com.example.stubber;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.github.tomakehurst.wiremock.client.MappingBuilder;

import wiremock.com.google.common.net.MediaType;

public class SimpleStub implements BaseStubber{

    @Override
    public MappingBuilder stub() throws Exception {
        String uuid = UUID.randomUUID().toString();
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
