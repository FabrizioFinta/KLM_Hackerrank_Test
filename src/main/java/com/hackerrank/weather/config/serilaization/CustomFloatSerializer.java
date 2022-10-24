package com.hackerrank.weather.config.serilaization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class CustomFloatSerializer extends JsonSerializer<Float> {

    @Override
    public void serialize(Float aFloat, JsonGenerator jsonGenerator, SerializerProvider p)
        throws IOException {
            jsonGenerator.writeNumber(String.format("%.4f", aFloat));

    }
}
