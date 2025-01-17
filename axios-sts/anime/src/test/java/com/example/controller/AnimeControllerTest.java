package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Anime;
import com.example.repo.AnimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimeControllerTest {

    @Mock
    private AnimeRepository animeRepository;

    @InjectMocks
    private AnimeController animeController;

    private Anime testAnime;

    @BeforeEach
    public void setUp() {
        testAnime = new Anime();
        testAnime.setId(1);
        testAnime.setName("Test Anime");
        testAnime.setSynopsis("Test Synopsis");
        testAnime.setRating(9.5f);
    }

    @Nested
    class ReadTests {
        @Test
        public void testReadAllAnime() {
            List<Anime> animeList = new ArrayList<>();
            animeList.add(testAnime);

            when(animeRepository.findAll()).thenReturn(animeList);

            List<Anime> result = animeController.read();

            assertEquals(animeList, result);
        }

        @Test
        public void testReadOneAnime() {
            when(animeRepository.findById(1L)).thenReturn(Optional.of(testAnime));

            Optional<Anime> result = animeController.readOne(1L);

            assertEquals(Optional.of(testAnime), result);
        }

        @Test
        public void testReadOneAnime_NotFound() {
            when(animeRepository.findById(1L)).thenReturn(Optional.empty());

            Optional<Anime> result = animeController.readOne(1L);

            assertEquals(Optional.empty(), result);
        }

        @Test
        public void testReadAnime() throws ResourceNotFoundException {
            when(animeRepository.findById(1L)).thenReturn(Optional.of(testAnime));

            ResponseEntity<Anime> response = animeController.readAnime(1L);

            assertEquals(testAnime, response.getBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        public void testReadAnime_NotFound() {
            when(animeRepository.findById(1L)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                    () -> animeController.readAnime(1L));

            assertEquals("Anime not found for the ID: 1", exception.getMessage());
        }
    }

    @Nested
    class CreateUpdateTests {
        @Test
        public void testAddAnime() {
            when(animeRepository.save(testAnime)).thenReturn(testAnime);

            Anime result = animeController.add(testAnime);

            assertEquals(testAnime, result);
        }

        @Test
        public void testUpdateAnime() {
            when(animeRepository.findById(1L)).thenReturn(Optional.of(testAnime));

            Anime updatedAnime = new Anime();
            updatedAnime.setId(1);
            updatedAnime.setName("Updated Anime");
            updatedAnime.setSynopsis("Updated Synopsis");
            updatedAnime.setRating(8.0f);

            ResponseEntity<Void> response = animeController.update(updatedAnime, 1L);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(updatedAnime.getName(), testAnime.getName());
            assertEquals(updatedAnime.getSynopsis(), testAnime.getSynopsis());
            assertEquals(updatedAnime.getRating(), testAnime.getRating());
        }

        @Test
        public void testUpdateAnime_NotFound() {
            when(animeRepository.findById(1L)).thenReturn(Optional.empty());

            ResponseEntity<Void> response = animeController.update(new Anime(), 1L);

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }

    @Nested
    class DeleteTests {
        @Test
        public void testDeleteAnime() {
            when(animeRepository.findById(1L)).thenReturn(Optional.of(testAnime));

            ResponseEntity<Void> response = animeController.delete(1L);

            verify(animeRepository, times(1)).deleteById(1L);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        public void testDeleteAnime_NotFound() {
            when(animeRepository.findById(1L)).thenReturn(Optional.empty());

            ResponseEntity<Void> response = animeController.delete(1L);

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }
}
