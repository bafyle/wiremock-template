package com.example.stubber;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;

public abstract class BaseStub {
    
    protected String CONTENT_TYPE_HEADER = "Content-Type";
    protected WireMockServer wireMockServer;

    public BaseStub(WireMockServer wms)
    {
        this.wireMockServer = wms;
    }

    public abstract MappingBuilder stub() throws Exception;

}
