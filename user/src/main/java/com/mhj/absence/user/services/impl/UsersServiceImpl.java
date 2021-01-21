package com.mhj.absence.user.services.impl;

import com.mhj.absence.user.dto.UserDto;
import com.mhj.absence.user.entities.UserEntity;
import com.mhj.absence.user.repositories.UserRepository;
import com.mhj.absence.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UserService {

    UserRepository usersRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //RestTemplate restTemplate;
    Environment environment;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UsersServiceImpl(UserRepository usersRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder,
                            Environment environment) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.environment = environment;
    }

    @Override
    public UserEntity createUser(UserDto userDetails) {
        // TODO Auto-generated method stub
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        return usersRepository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(username);
        if (userEntity == null) throw new UsernameNotFoundException(username);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserEntity getUserDetailsByEmail(String email) {
        UserEntity userEntity = usersRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new ModelMapper().map(userEntity, UserEntity.class);
    }

    @Override
    public UserEntity getUserByUserId(String userId) {
        UserEntity userEntity = usersRepository.findByUserId(userId);
        if (userEntity == null) throw new UsernameNotFoundException("User not found");
        UserEntity userDto = new ModelMapper().map(userEntity, UserEntity.class);
        return userDto;
    }


}
