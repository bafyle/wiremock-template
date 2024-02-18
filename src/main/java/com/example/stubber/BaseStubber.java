package com.example.stubber;

import com.github.tomakehurst.wiremock.client.MappingBuilder;

public interface BaseStubber {
    
    String CONTENT_TYPE_HEADER = "Content-Type";

    MappingBuilder stub() throws Exception;
}
