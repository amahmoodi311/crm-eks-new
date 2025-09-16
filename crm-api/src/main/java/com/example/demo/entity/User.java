package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "name Cannot be empty")
    String name;

    @Column
    @NotEmpty(message = "email Cannot be empty")
    String email;

    @Column
    @NotEmpty(message = "mobile Cannot be empty")
    String mobile;

    @Column
    @NotEmpty(message = "username Cannot be empty")
    String username;

    @Column
    @NotEmpty(message = "password Cannot be empty")
    String password;

}
