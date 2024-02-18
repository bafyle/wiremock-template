package com.example.helper;

import java.util.Map;

import com.github.tomakehurst.wiremock.extension.TemplateHelperProviderExtension;

import wiremock.com.github.jknack.handlebars.Helper;

public class StringLengthHelper implements TemplateHelperProviderExtension {

    @Override
    public String getName() {
        return "Custom String length";
    }

    @Override
    public Map<String, Helper<?>> provideTemplateHelpers() {
        Helper<String> helper = (context, options) -> context.length();
        return Map.of("string-length", helper);
    }
}
