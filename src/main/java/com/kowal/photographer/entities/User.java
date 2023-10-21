package com.kowal.photographer.entities;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing users
 */
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotBlank
    @Email
    private String username;// Email jest nazwą użytkownika
    @Column(nullable = false)
    @NotBlank
    private String password;
    @Column(nullable = false)
    private String role;
    @Column(name = "phone_number")
    @NotBlank
    private String phoneNumber;
    @Column(nullable = false)
    private Boolean active;
    @Column(name = "first_name")
    private String firstName;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pictures> pictures = new ArrayList<>();
}
