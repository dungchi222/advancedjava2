package com.samsung.basicsecurity.repositories.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String username;
//    private String password;

//
//    private String fullname;
//    private String phone;
//    private String email;

    private static final long serialVersionUID = 1L;

    private String confirmpassword;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 150)
    private String fullname;

    @Column(nullable = false)
    private boolean status;
}
