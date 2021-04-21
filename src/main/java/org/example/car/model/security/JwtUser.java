package org.example.car.model.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtUser {

  @JsonProperty("name")
  private String fullName;

  @JsonProperty("preferred_username")
  private String username;

  @JsonProperty("given_name")
  private String name;

  @JsonProperty("family_name")
  private String surname;

  private String email;

  private String encodedJwt;
}
