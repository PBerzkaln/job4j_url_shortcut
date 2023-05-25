package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.model.Url;
import ru.job4j.shortcut.repository.UrlRepository;
import ru.job4j.shortcut.util.LoginAndUrlKeyGenerator;

@Service
@AllArgsConstructor
@ThreadSafe
public class SimpleUrlService implements UrlService {
    private final UrlRepository urlRepository;
    private final LoginAndUrlKeyGenerator keyGenerator;

    @Override
    public UrlDto create(Url url) {
        String key;
        do {
            key = keyGenerator.generateKey();
        } while (urlRepository.existsByKey(key));
        url.setKey(key);
        urlRepository.save(url);
        var urlDto = new UrlDto();
        urlDto.setGeneratedKey(key);
        return urlDto;
    }
}