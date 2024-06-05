package com.example.repo;

import com.example.model.Anime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @BeforeEach
    public void setUp() {
        animeRepository.deleteAll(); // Clear the database before each test
    }

    @Test
    public void testSaveAndFind() {
        Anime anime = new Anime();
        anime.setName("Test Anime");
        anime.setSynopsis("Test Synopsis");
        anime.setRating(8.5f);

        anime = animeRepository.save(anime);

        Anime foundAnime = animeRepository.findById(anime.getId()).orElse(null);
        assertNotNull(foundAnime);
        assertEquals(anime.getName(), foundAnime.getName());
    }

    @Test
    public void testFindAll() {
        Anime anime1 = new Anime();
        anime1.setName("Test Anime 1");
        anime1.setSynopsis("Test Synopsis 1");
        anime1.setRating(8.5f);

        Anime anime2 = new Anime();
        anime2.setName("Test Anime 2");
        anime2.setSynopsis("Test Synopsis 2");
        anime2.setRating(7.5f);

        animeRepository.save(anime1);
        animeRepository.save(anime2);

        List<Anime> animeList = animeRepository.findAll();
        assertEquals(2, animeList.size());
    }
}
