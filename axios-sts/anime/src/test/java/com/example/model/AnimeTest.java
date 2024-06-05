//package com.example.model;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AnimeTest {
//
//    @Test
//    public void testAnimeModel() {
//        Anime anime = new Anime();
//        anime.setId(1L);
//        anime.setName("Test Anime");
//        anime.setSynopsis("Test Synopsis");
//        anime.setRating(9.5f);
//
//        assertEquals(1L, anime.getId());
//        assertEquals("Test Anime", anime.getName());
//        assertEquals("Test Synopsis", anime.getSynopsis());
//        assertEquals(9.5f, anime.getRating());
//    }
//
//    @Test
//    public void testAnimeAllArgsConstructor() {
//        Anime anime = new Anime(1L, "Test Anime", "Test Synopsis", 9.5f);
//
//        assertEquals(1L, anime.getId());
//        assertEquals("Test Anime", anime.getName());
//        assertEquals("Test Synopsis", anime.getSynopsis());
//        assertEquals(9.5f, anime.getRating());
//    }
//
//    @Test
//    public void testAnimeNoArgsConstructor() {
//        Anime anime = new Anime();
//
//        assertNotNull(anime);
//    }
//
//    @Test
//    public void testSettersAndGetters() {
//        Anime anime = new Anime();
//        anime.setId(1L);
//        anime.setName("Test Anime");
//        anime.setSynopsis("Test Synopsis");
//        anime.setRating(9.5f);
//
//        assertEquals(1L, anime.getId());
//        assertEquals("Test Anime", anime.getName());
//        assertEquals("Test Synopsis", anime.getSynopsis());
//        assertEquals(9.5f, anime.getRating());
//    }
//
//    @Test
//    public void testInvalidRatingTooHigh() {
//        Anime anime = new Anime();
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            anime.setRating(11.0f);
//        });
//
//        String expectedMessage = "Rating must be between 0 and 10";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    public void testInvalidRatingTooLow() {
//        Anime anime = new Anime();
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            anime.setRating(-1.0f);
//        });
//
//        String expectedMessage = "Rating must be between 0 and 10";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    public void testValidRating() {
//        Anime anime = new Anime();
//        anime.setRating(5.0f);
//
//        assertEquals(5.0f, anime.getRating());
//    }
//}


package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimeTest {

    @Test
    public void testAnimeModel() {
        Anime anime = new Anime();
        anime.setId(1L);
        anime.setName("Test Anime");
        anime.setSynopsis("Test Synopsis");
        anime.setRating(9.5f);

        assertEquals(1L, anime.getId());
        assertEquals("Test Anime", anime.getName());
        assertEquals("Test Synopsis", anime.getSynopsis());
        assertEquals(9.5f, anime.getRating());
    }

    @Test
    public void testAnimeAllArgsConstructor() {
        Anime anime = new Anime(1L, "Test Anime", "Test Synopsis", 9.5f);

        assertEquals(1L, anime.getId());
        assertEquals("Test Anime", anime.getName());
        assertEquals("Test Synopsis", anime.getSynopsis());
        assertEquals(9.5f, anime.getRating());
    }

    @Test
    public void testAnimeNoArgsConstructor() {
        Anime anime = new Anime();

        assertNotNull(anime);
    }

    @Test
    public void testSettersAndGetters() {
        Anime anime = new Anime();
        anime.setId(1L);
        anime.setName("Test Anime");
        anime.setSynopsis("Test Synopsis");
        anime.setRating(9.5f);

        assertEquals(1L, anime.getId());
        assertEquals("Test Anime", anime.getName());
        assertEquals("Test Synopsis", anime.getSynopsis());
        assertEquals(9.5f, anime.getRating());
    }

    @Test
    public void testInvalidRatingTooHigh() {
        Anime anime = new Anime();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            anime.setRating(11.0f);
        });

        String expectedMessage = "Rating must be between 0 and 10";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testInvalidRatingTooLow() {
        Anime anime = new Anime();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            anime.setRating(-1.0f);
        });

        String expectedMessage = "Rating must be between 0 and 10";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testValidRating() {
        Anime anime = new Anime();
        anime.setRating(5.0f);

        assertEquals(5.0f, anime.getRating());
    }

    @Test
    public void testEqualsAndHashCode() {
        Anime anime1 = new Anime(1L, "Test Anime", "Test Synopsis", 9.5f);
        Anime anime2 = new Anime(1L, "Test Anime", "Test Synopsis", 9.5f);
        Anime anime3 = new Anime(2L, "Another Anime", "Another Synopsis", 8.0f);

        assertEquals(anime1, anime2);
        assertNotEquals(anime1, anime3);
        assertEquals(anime1.hashCode(), anime2.hashCode());
        assertNotEquals(anime1.hashCode(), anime3.hashCode());
    }

    @Test
    public void testToString() {
        Anime anime = new Anime(1L, "Test Anime", "Test Synopsis", 9.5f);
        String expectedToString = "Anime(id=1, name=Test Anime, synopsis=Test Synopsis, rating=9.5)";
        
        assertEquals(expectedToString, anime.toString());
    }
}
