package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is mandatory")
    @Column
    private String name;

    @NotEmpty(message = "CC is mandatory")
    @Column
    private String cc;

    @NotEmpty(message = "Phone is mandatory")
    @Column
    private String phone;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column
    private String email;

    @Column
    private String feeQuoted;

    @Column
    private String batchTiming;

    @Column
    private String description;

    @Column
    private String leadStatus;

    @Column
    private String leadSource;

    @Column
    private String stack;

    @Column
    private String course;

    @Column
    private String classMode;

    @Column
    private String nextFollowUp;
}