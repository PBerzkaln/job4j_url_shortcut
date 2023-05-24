package ru.job4j.shortcut.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Min(value = 1, message = "Id must be non null")
    private int id;

    private int count;

    @EqualsAndHashCode.Include
    @NotBlank(message = "Url must be not empty")
    private String url;

    @EqualsAndHashCode.Include
    private String key;
}