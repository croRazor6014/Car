package org.example.car.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ForwardedHeaderFilter;

/**
 * Created by lovro.vrlec on Apr,2021
 *
 * OpenApi Configuration class.
 */
public class OpenApiConfiguration {
  /**
   * Setup openAPI page.
   *
   * @return {@link OpenAPI}
   */
  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI().info(
        new Info().title("Car API").description("Car service application")
            .version("v0.0.1").license(new License().name("Apache 2.0").url(
            "http://www.apache.org/licenses/LICENSE-2.0.html")));
  }

  /**
   * Setup forwarded header filter.
   *
   * @return {@link ForwardedHeaderFilter}
   */
  @Bean
  ForwardedHeaderFilter forwardedHeaderFilter() {
    return new ForwardedHeaderFilter();
  }
}
