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
import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.model.Url;
import ru.job4j.shortcut.service.SiteService;
import ru.job4j.shortcut.service.UrlService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/site")
@AllArgsConstructor
public class RestController {
    private static final Logger LOG = LogManager.getLogger(RestController.class.getName());
    private final SiteService siteService;
    private final UrlService urlService;
    private final ObjectMapper objectMapper;

    @PostMapping("/registration")
    public ResponseEntity<SiteDTO> create(@RequestBody Site site) {
        var rsl = siteService.create(site);
        if (!rsl.isRegStatus()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "The site has already registered.");
        }
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }

    @PostMapping("/convert")
    public ResponseEntity<UrlDto> convert(@RequestBody Url url) {
        var rsl = urlService.create(url);
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }
}