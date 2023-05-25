package ru.job4j.shortcut.service;

import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.model.Url;

public interface UrlService {
    UrlDto create(Url url);
}