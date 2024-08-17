package com.ontimehelps.OntimeHelps.dtos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private String password;
    @Size(min = 2 , max = 6)
    private String gender;
    private String address;
    private String phone;
    private String imageName;
}
