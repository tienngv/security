package com.tienngv.security.configs;

import com.google.gson.*;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.Instant;

@Getter
@Configuration
public class Configs {
    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(Instant.class, new JsonDeserializer<Instant>() {
                    public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                            throws JsonParseException {
                        return Instant.parse(json.getAsString());
                    }
                })
                .registerTypeAdapter(Instant.class, (JsonSerializer<Instant>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
                .create();
    }

}
