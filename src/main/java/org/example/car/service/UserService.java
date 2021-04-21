package org.example.car.service;

import java.util.Optional;
import java.util.UUID;

import org.example.car.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by lovro.vrlec on Apr,2021
 */
public interface UserService {
  /**
   *
   * @param specification query Users.
   * @param pageable page.
   * @return Page with User Objects.
   */
  Page<User> findUsers(Specification<User> specification, Pageable pageable);

  /**
   *
   * @param uuid uuid of User.
   * @return Optional User.
   */
  Optional<User> findUserByUUID(UUID uuid);

  /**
   *
   * @param id id of User.
   * @return Optional of User.
   */
  Optional<User> findUserByID(long id);

  /**
   *
   * @param user user to add.
   * @return User saved User.
   */
  User addUser(User user);

  /**
   *
   * @param user user to update.
   * @return User updated.
   */
  User updateUser(User user);
}
