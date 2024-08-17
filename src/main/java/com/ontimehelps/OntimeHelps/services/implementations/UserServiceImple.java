package com.ontimehelps.OntimeHelps.services.implementations;

import com.ontimehelps.OntimeHelps.dtos.UserDto;
import com.ontimehelps.OntimeHelps.entities.User;
import com.ontimehelps.OntimeHelps.repositories.UserRepository;
import com.ontimehelps.OntimeHelps.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto creteUser(UserDto userDto) {

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        //Generate unique user id
        String id = UUID.randomUUID().toString();
        userDto.setUserId(id);

        User user = dtoToEntity(userDto);
        userRepository.save(user);

        UserDto newDto = entityToDto(user);
        return newDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with this id"));

        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setImageName(userDto.getImageName());
        user.setAddress(userDto.getAddress());

        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);

        return updatedDto;
    }

    @Override
    public void deleteUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User id not found"));

        //delete
        userRepository.delete(user);

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user);
        UserDto newDto = entityToDto(user);
        return newDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("No user found by this email"));
        UserDto userDto = entityToDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }


    //Mappers
    private UserDto entityToDto(User user) {

        return modelMapper.map(user , UserDto.class);

//        UserDto newDto = UserDto.builder()
//                .userId(user.getUserId())
//                .name(user.getName())
//                .address(user.getAddress())
//                .phone(user.getPhone())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .gender(user.getGender())
//                .imageName(user.getImageName())
//                .build();
//
//        return newDto;
    }

    private User dtoToEntity(UserDto userDto) {

        return modelMapper.map(userDto , User.class);

//        User newEntity = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .gender(userDto.getGender())
//                .address(userDto.getAddress())
//                .phone(userDto.getPhone())
//                .imageName(userDto.getImageName())
//                .build();
//        return newEntity;


    }
}
