package com.mhj.absence.user.controllers;

import com.mhj.absence.user.dto.UserDto;
import com.mhj.absence.user.dto.UserReponseDto;
import com.mhj.absence.user.entities.UserEntity;
import com.mhj.absence.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity createUser(@NotNull @RequestBody UserDto userDetail) {
        UserEntity user = userService.createUser(userDetail);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserReponseDto userEntity = modelMapper.map(user, UserReponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }

    @GetMapping(value="/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserReponseDto> getUser(@PathVariable("userId") String userId) {
        UserEntity userDto = userService.getUserByUserId(userId);
        UserReponseDto returnValue = new ModelMapper().map(userDto, UserReponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
