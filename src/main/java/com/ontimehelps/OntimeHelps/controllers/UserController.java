package com.ontimehelps.OntimeHelps.controllers;


import com.ontimehelps.OntimeHelps.dtos.UserDto;
import com.ontimehelps.OntimeHelps.helpers.ApiResponseMessage;
import com.ontimehelps.OntimeHelps.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto user = userService.creteUser(userDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") String userId,
            @RequestBody @Valid UserDto userDto) {
        try {
            UserDto updatedDto = userService.updateUser(userDto, userId);
            return ResponseEntity.ok(updatedDto);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId) {
        ApiResponseMessage message = ApiResponseMessage.builder()
                .message("User is deleted")
                .status(HttpStatus.OK)
                .success(true)
                .build();

        userService.deleteUserById(userId);

        return new ResponseEntity<>(message , HttpStatus.OK );
    }

    //get all
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUser = userService.getAllUser();

        return new ResponseEntity<>(allUser , HttpStatus.OK);
    }

    //get single
    @GetMapping("/{userId}") //uri path variable
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        System.out.println(userId + "in contoller");
        UserDto user = userService.getUserById(userId);

        return new ResponseEntity<>(user , HttpStatus.OK);
    }


    //get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto userDto = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDto , HttpStatus.OK);
    }


    //get by email and password


    // get by keyword
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> getUserByKeyword(@PathVariable String keyword) {
        List<UserDto> userDtos = userService.searchUser(keyword);

        return new ResponseEntity<>(userDtos , HttpStatus.OK);
    }

}
