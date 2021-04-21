package org.example.car.service.impl;

import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.example.car.model.User;
import org.example.car.repository.UserRepository;
import org.example.car.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by lovro.vrlec on Apr,2021
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  /**
   * @param specification query Users.
   * @param pageable      page.
   * @return Page with User Objects.
   */
  @Override
  public Page<User> findUsers(final Specification<User> specification, final Pageable pageable) {
    return userRepository.findAll(specification,pageable);
  }

  /**
   * @param uuid uuid of User.
   * @return Optional User.
   */
  @Override
  public Optional<User> findUserByUUID(final UUID uuid) {
    return userRepository.findUserByUuidEquals(uuid);
  }

  /**
   * @param id id of User.
   * @return Optional of User.
   */
  @Override
  public Optional<User> findUserByID(final long id) {
    return userRepository.findById(id);
  }

  /**
   * @param user user to add.
   * @return User saved User.
   */
  @Override
  public User addUser(final User user) {
    user.setId(0L);
    user.setUuid(UUID.randomUUID());
    return userRepository.save(user);
  }

  /**
   * @param user user to update.
   * @return User updated.
   */
  @Override
  public User updateUser(final User user) {
    return userRepository.save(user);
  }
}
