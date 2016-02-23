package com.tangela.app.support.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;

import static org.joda.time.format.DateTimeFormat.forPattern;

public class DateTimeJsonSerializer extends JsonSerializer<DateTime> {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(forPattern(PATTERN).print(value));
    }
}
