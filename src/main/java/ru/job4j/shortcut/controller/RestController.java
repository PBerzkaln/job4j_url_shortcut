package ru.job4j.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    private final SiteService siteService;
    private final UrlService urlService;

    @PostMapping("/registration")
    public ResponseEntity<SiteDTO> create(@RequestBody Site site) {
        var rsl = siteService.create(site);
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }

    @PostMapping("/convert")
    public ResponseEntity<UrlDto> convert(@RequestBody Url url) {
        var rsl = urlService.create(url);
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }

    @GetMapping("/redirect/{key}")
    public ResponseEntity<String> convert(@PathVariable String key) {
        var rsl = urlService.findByKey(key);
        if (rsl.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
        var body = rsl.get().getUrl();
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Status", "HTTP CODE - 302 REDIRECT URL")
                .body(body);
    }

    @GetMapping("/statistic")
    public ResponseEntity<String> getStatistic() {
        var body = urlService.findAllUrlAndCountPerEach();
        return ResponseEntity.status(HttpStatus.OK)
                .header("Statistic", "Successful")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(body.length())
                .body(body);
    }
}