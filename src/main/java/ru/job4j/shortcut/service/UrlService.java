package ru.job4j.shortcut.service;

import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.model.Url;

import java.util.Optional;

public interface UrlService {
    UrlDto create(Url url);

    Optional<Url> findByKey(String key);

    String findAllUrlAndCountPerEach();
}