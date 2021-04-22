package org.example.car.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
public class UserDto implements Serializable {

  @JsonView( {View.Basic.class})
  private long id;

  @JsonView({View.Basic.class})
  private UUID uuid;

  @JsonView({View.Basic.class})
  private String name;

  @JsonView({View.Basic.class})
  private String surname;

  @JsonView({View.Basic.class})
  private String email;

  @JsonView({View.Basic.class})
  private Set<CarDto> ownedCars = new HashSet<>();

  @JsonView({View.Basic.class})
  private Set<OrderDto> orders = new HashSet<>();
}
