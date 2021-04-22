package org.example.car.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
import org.hibernate.envers.AuditMappedBy;
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
  private long id;

  @JsonView({View.Basic.class})
  @Type(type = "uuid-char")
  private UUID uuid;

  @JsonView({View.Basic.class})
  private String name;

  @JsonView({View.Basic.class})
  private String surname;

  @JsonView({View.Basic.class})
  private String email;

  @JsonView({View.Basic.class})
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinColumn(name = "id_owner")
  @AuditMappedBy(mappedBy = "owner")
  private Set<Car> ownedCars = new HashSet<>();

  @JsonView({View.Basic.class})
  @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinColumn(name = "id_user")
  @AuditMappedBy(mappedBy = "user")
  private Set<Order> orders = new HashSet<>();
}
