package com.ontimehelps.OntimeHelps.services;

import com.ontimehelps.OntimeHelps.dtos.UserDto;

import java.util.List;

public interface UserService {

    //create
    UserDto creteUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto , String id);

    //read

    //delete
    void deleteUserById(String id);

    //search user
    //get all users
    List<UserDto> getAllUser();

    //get user by id
    UserDto getUserById(String id);

    //get user by email
    UserDto getUserByEmail(String email);

    //other user specific feature
    List<UserDto> searchUser(String keyword);
}
