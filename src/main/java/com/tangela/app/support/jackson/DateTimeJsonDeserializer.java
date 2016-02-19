package com.tangela.app.support.jackson;

import static org.joda.time.format.DateTimeFormat.forPattern;

import java.io.IOException;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateTimeJsonDeserializer extends JsonDeserializer<DateTime> {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return forPattern(PATTERN).parseDateTime(jp.getText());
    }

}