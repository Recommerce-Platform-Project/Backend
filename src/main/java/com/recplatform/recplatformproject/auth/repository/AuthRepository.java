package com.recplatform.recplatformproject.auth.repository;

import com.recplatform.recplatformproject.auth.model.entity.Auth;
import com.recplatform.recplatformproject.user.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByUsers(Users users);
    boolean existsByUsers(Users users);
}
