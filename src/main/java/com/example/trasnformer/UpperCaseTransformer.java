package com.example.trasnformer;

import java.nio.charset.StandardCharsets;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformerV2;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;

public class UpperCaseTransformer implements ResponseDefinitionTransformerV2 {

    @Override
    public ResponseDefinition transform(ServeEvent serveEvent) {
        return new ResponseDefinitionBuilder()
                .withHeader("MyHeader", "Transformed")
                .withStatus(200)
                .withBody(new String(serveEvent.getRequest().getBody(), StandardCharsets.UTF_8).toUpperCase())
                .build();
    }

    @Override
    public String getName() {
        return "upper-case-transformer";
    }
}