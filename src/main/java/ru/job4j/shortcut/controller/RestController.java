package ru.job4j.shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.shortcut.dto.SiteDTO;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.service.SiteService;
import ru.job4j.shortcut.util.LoginGenerator;
import ru.job4j.shortcut.util.PasswordGenerator;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/site")
@AllArgsConstructor
public class RestController {
    private static final Logger LOG = LogManager.getLogger(RestController.class.getName());
    private final SiteService siteService;
    private final PasswordGenerator passwordGenerator;
    private final LoginGenerator loginGenerator;
    private final BCryptPasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    @PostMapping("/registration")
    public ResponseEntity<SiteDTO> create(@RequestBody Site site) {
        /**
         * Пароли хешируются и прямом виде не хранятся в базе.
         */
        site.setPassword(encoder.encode(passwordGenerator.generatePassword()));
        site.setLogin(loginGenerator.generateLogin(site.getName()));
        var rsl = siteService.create(site);
        if (!rsl.isRegStatus()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "The site has already registered.");
        }
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }
//
//    POST /convert
//
//    GET /redirect/УНИКАЛЬНЫЙ_КОД
//
//    GET /statistic
}