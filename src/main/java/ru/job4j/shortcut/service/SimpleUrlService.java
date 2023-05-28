package ru.job4j.shortcut.service;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.model.Url;
import ru.job4j.shortcut.repository.SiteRepository;
import ru.job4j.shortcut.repository.UrlRepository;
import ru.job4j.shortcut.util.LoginAndUrlKeyGenerator;

import java.util.*;

@Service
@AllArgsConstructor
@ThreadSafe
public class SimpleUrlService implements UrlService {
    private final UrlRepository urlRepository;
    private final SiteRepository siteRepository;
    private final LoginAndUrlKeyGenerator keyGenerator;

    @Override
    public UrlDto create(Url url) {
        String key;
        do {
            key = keyGenerator.generateKey();
        } while (urlRepository.existsByKey(key));
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Site site = siteRepository.findByLogin(login).get();
        url.setKey(key);
        url.setSite(site);
        urlRepository.save(url);
        var urlDto = new UrlDto();
        urlDto.setGeneratedKey(key);
        return urlDto;
    }

    @Transactional
    @Override
    public Optional<Url> findByKey(String key) {
        var rsl = urlRepository.findByKey(key);
        if (rsl.isPresent()) {
            urlRepository.incrementByCode(key);
            return rsl;
        }
        return rsl;
    }

    private <T> String toJson(T object) {
        var gson = new Gson();
        return gson.toJson(object);
    }

    @Override
    public String findAllUrlAndCountPerEach() {
        var map = new HashMap<>();
        urlRepository.findAll().forEach(p -> map.put(p.getUrl(), p.getCount()));
        return toJson(map);
    }
}