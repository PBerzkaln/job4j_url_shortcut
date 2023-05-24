package ru.job4j.shortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteDTO {
    private String generatedLogin;
    private String generatedPassword;
    private boolean regStatus;
}