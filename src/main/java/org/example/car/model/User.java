package org.example.car.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.car.model.jsonviews.View;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

/**
 * Created by lovro.vrlec on Apr,2021
 */

@Slf4j
@Getter
@Setter
@Audited
@NoArgsConstructor
@Entity
@Table(name = "example_user")
public class User implements Serializable {

  private static final long serialVersionUID = -1163971585432722024L;

  @JsonView({View.Basic.class})
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @JsonView({View.Basic.class})
  @Type(type = "uuid-char")
  private UUID uuid;

  @JsonView({View.Basic.class})
  private String name;
}
