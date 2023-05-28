package ru.job4j.shortcut.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Site name must be not empty")
    @Column(unique = true)
    private String name;

    @EqualsAndHashCode.Include
    @Column(unique = true)
    private String login;

    @EqualsAndHashCode.Include
    private String password;
}