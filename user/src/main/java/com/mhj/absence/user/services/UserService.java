package com.mhj.absence.user.services;

import com.mhj.absence.user.dto.UserDto;
import com.mhj.absence.user.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity createUser(UserDto userDetails);

    UserEntity getUserDetailsByEmail(String email);

    UserEntity getUserByUserId(String userId);
}




