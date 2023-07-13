package com.proj.UserApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "first_name",nullable = false,length = 20)
    private String firstName;
    @Column(name = "last_name",nullable = false,length = 20)
    private String lastName;
    @Column(nullable = false,unique = true,length = 45)
    private String email;
    @Column(nullable = false,length = 64)
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false,length = 10)
    private String number;
//    @Column(length = 64)
//    private String token;
    private boolean approved = false;
//    @Enumerated(EnumType.STRING)
//    private Role roles;
    private String Roles;
}
