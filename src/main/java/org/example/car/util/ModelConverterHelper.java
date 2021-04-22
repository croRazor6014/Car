package org.example.car.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * Created by lovro.vrlec on Nov,2018
 */
@Component
public class ModelConverterHelper {

  private ModelConverterHelper() {
  }

  /**
   * @param viewClass  viewClass
   * @param modelClass viewClass
   * @param <T>        T
   * @param <E>        E
   * @return String
   * @throws JsonProcessingException JsonProcessingException
   */
  public static <T, E> String toJson(final Class<E> viewClass, final T modelClass)
          throws JsonProcessingException {
    final ObjectMapper mapper = new ObjectMapper();
    return mapper.writerWithView(viewClass).forType(modelClass.getClass())
            .writeValueAsString(modelClass);
  }

}
