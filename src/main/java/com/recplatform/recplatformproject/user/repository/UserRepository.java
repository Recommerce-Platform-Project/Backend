package com.recplatform.recplatformproject.user.repository;

import com.recplatform.recplatformproject.user.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUserId(String userId);

    Optional<Users> findByUserName(String userName);

    Optional<Users> findBySocialEmail(String socialEmail);
}
