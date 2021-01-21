package com.mhj.absence.user.repositories;

import com.mhj.absence.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String username);

    UserEntity findByUserId(String userId);
}
