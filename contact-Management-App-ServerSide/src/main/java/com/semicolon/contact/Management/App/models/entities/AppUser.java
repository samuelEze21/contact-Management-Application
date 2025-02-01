//package com.semicolon.contact.Management.App.models.entities;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@Entity
//public class AppUser {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private String email;
//
//    @Column(unique = true, nullable = false)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    private Set<AppRole> roles = new HashSet<>();
////
////    @Column(nullable = false)
////    private boolean isEnabled = true;
////
////    // Additional fields like email, phone, etc.
//}
