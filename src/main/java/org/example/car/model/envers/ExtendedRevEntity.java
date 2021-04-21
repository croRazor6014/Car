package org.example.car.model.envers;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.car.model.jsonviews.View;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

/**
 * Created by lovro.vrlec on Jul,2020
 */
//
@Entity
@JsonView(View.Basic.class)
@Table(name = "REVINFO")
@AttributeOverrides( {
                        @AttributeOverride(name = "timestamp", column = @Column(name = "REVTSTMP")),
                        @AttributeOverride(name = "id", column = @Column(name = "REV"))
                    })
@RevisionEntity(EnversListener.class)
public class ExtendedRevEntity extends DefaultRevisionEntity{

  @JsonView(View.Basic.class)
  @Column(name = "username")
  private String username;

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }
}
