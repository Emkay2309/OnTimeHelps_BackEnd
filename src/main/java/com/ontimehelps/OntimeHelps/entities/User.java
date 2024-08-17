package com.ontimehelps.OntimeHelps.entities;


import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity // For creating tables in database
@Table(name = "users") // for changing table names
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "user_name")
    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    private String gender;
    private String address;
    @Column(unique = true , length = 10)
    private String phone;

    @Column(name = "image")
    private String imageName;



}
