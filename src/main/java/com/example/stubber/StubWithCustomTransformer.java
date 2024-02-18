package com.example.stubber;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;

public class StubWithCustomTransformer extends BaseStub{

    public StubWithCustomTransformer(WireMockServer wms) {
        super(wms);
    }

    @Override
    public MappingBuilder stub() throws Exception {
        
        return get("/custom-transformer-test")
            .willReturn(aResponse()
                .withTransformers("upper-case-transformer"));
    }
    
}
