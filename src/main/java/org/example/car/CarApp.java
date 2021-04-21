package org.example.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by lovro.vrlec on Apr,2021
 */
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@SpringBootApplication
@EnableAsync
public class CarApp extends SpringBootServletInitializer {

  /**
   * Spring application configuration.
   *
   * @param springApplicationBuilder springApplicationBuilder
   * @return SpringApplicationBuilder
   */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
    return springApplicationBuilder.sources(CarApp.class);
  }

  /**
   * Main method.
   *
   * @param args args
   */
  public static void main(final String[] args) {
    SpringApplication.run(CarApp.class, args);
  }
}
