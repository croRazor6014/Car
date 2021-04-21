package org.example.car.model;

import java.io.Serializable;
import java.security.acl.Owner;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "example_car")
public class Car implements Serializable {

  private static final long serialVersionUID = -1163971585432722024L;

  @JsonView( {View.Basic.class})
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @JsonView({View.Basic.class})
  @Type(type = "uuid-char")
  private UUID uuid;

  @JsonView({View.Basic.class})
  private String name;

  @JsonView({View.Basic.class})
  @JsonIgnoreProperties(value = {"ownedCars"}, allowSetters = true)
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "id_owner", insertable = false, updatable = false)
  private User owner;
}
