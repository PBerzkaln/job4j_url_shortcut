package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.Url;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    boolean existsByKey(String key);

    Optional<Url> findByKey(String key);

    @Modifying
    @Query(value = """
            UPDATE Url u
            SET u.count = u.count + 1
            WHERE u.key = ?1
            """)
    void incrementByCode(String key);

    List<Url> findAll();
}