package com.task.taskapp.model;


import jakarta.persistence.*;
import lombok.Data;


import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(unique = true,nullable = false)
    private String username;

    private String password;
}
