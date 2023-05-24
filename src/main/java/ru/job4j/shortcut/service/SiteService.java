package ru.job4j.shortcut.service;

import ru.job4j.shortcut.dto.SiteDTO;
import ru.job4j.shortcut.model.Site;

public interface SiteService {
    SiteDTO create(Site site);
}