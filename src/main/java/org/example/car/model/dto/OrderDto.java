package org.example.car.model.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.car.model.jsonviews.View;

/**
 * Created by lovro.vrlec on Apr,2021
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class OrderDto implements Serializable {

  @JsonView( {View.Basic.class})
  private long id;

  @JsonView({View.Basic.class})
  private UUID uuid;

  @JsonView({View.Basic.class})
  private String name;
}
