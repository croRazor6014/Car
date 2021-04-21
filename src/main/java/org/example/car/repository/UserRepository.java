package org.example.car.repository;

import java.util.Optional;
import java.util.UUID;

import org.example.car.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lovro.vrlec on Apr,2021
 */
@Repository
@Transactional
public interface UserRepository extends RevisionRepository<User, Long, Long>,
    JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

  Optional<User> findUserByUuidEquals(UUID uuid);
}
