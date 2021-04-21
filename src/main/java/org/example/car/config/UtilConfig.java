package org.example.car.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

/**
 * Created by lovro.vrlec on Apr,2021
 */
@Configuration
public class UtilConfig {
  @Bean
  public Module springDataPageModule() {
    return new SimpleModule().addSerializer(Page.class, new JsonSerializer<Page>() {
      @Override
      public void serialize(Page value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("totalElements", value.getTotalElements());
        gen.writeNumberField("totalPages", value.getTotalPages());
        gen.writeNumberField("number", value.getNumber());
        gen.writeNumberField("size", value.getSize());
        gen.writeBooleanField("first", value.isFirst());
        gen.writeBooleanField("last", value.isLast());
        gen.writeFieldName("content");
        serializers.defaultSerializeValue(value.getContent(), gen);
        gen.writeEndObject();
      }
    });
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
