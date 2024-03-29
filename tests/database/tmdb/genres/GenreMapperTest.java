package database.tmdb.genres;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GenreMapperTest {
    private final Map<Integer, String> genreMap;

    public GenreMapperTest() {
        genreMap = new HashMap<>();
        genreMap.put(28, "Action");
        genreMap.put(12, "Adventure");
        genreMap.put(16, "Animation");
        genreMap.put(35, "Comedy");
        genreMap.put(80, "Crime");
        genreMap.put(99, "Documentary");
        genreMap.put(18, "Drama");
        genreMap.put(10751, "Family");
        genreMap.put(14, "Fantasy");
        genreMap.put(36, "History");
        genreMap.put(27, "Horror");
        genreMap.put(10402, "Music");
        genreMap.put(9648, "Mystery");
        genreMap.put(10749, "Romance");
        genreMap.put(878, "Science Fiction");
        genreMap.put(10770, "TV Movie");
        genreMap.put(53, "Thriller");
        genreMap.put(10752, "War");
        genreMap.put(37, "Western");
    }

    @Test
    void getGenreNameById() {
        genreMap.forEach((key, value) -> assertEquals(value, GenreMapper.MAPPER.getGenreNameById(key)));
    }

    @Test
    void getGenreIdByName() {
        genreMap.forEach((key, value) -> assertEquals(key, GenreMapper.MAPPER.getGenreIdByName(value)));
    }
}
