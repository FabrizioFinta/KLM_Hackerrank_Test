package com.hackerrank.weather.config.serilaization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;

public class CustomListDoubleSerializer extends JsonSerializer<List<Double>> {

    @Override
    public void serialize(List<Double> doubles, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        doubles.forEach(aDouble -> {
            try {
                jsonGenerator.writeNumber(String.format("%.2f", aDouble));
            } catch (IOException e) {
                throw new IllegalArgumentException(
                    String.format("Cannot serialize the following value: %s", aDouble));
            }
        });
        jsonGenerator.writeEndArray();
    }
}
