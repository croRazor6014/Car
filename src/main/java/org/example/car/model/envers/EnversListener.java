package org.example.car.model.envers;


import java.util.Optional;

import org.example.car.model.security.JwtUser;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by lovro.vrlec on Jul,2020
 */
public class EnversListener implements RevisionListener {

  public void newRevision(Object revisionEntity) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String currentUser = Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getPrincipal)
            .map(JwtUser.class::cast)
            .map(JwtUser::getUsername)
            .orElse("Unknown-User");

    ExtendedRevEntity audit = (ExtendedRevEntity) revisionEntity;
    audit.setUsername(currentUser);
  }
}
