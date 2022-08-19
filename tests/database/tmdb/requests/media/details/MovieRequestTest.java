package database.tmdb.requests.media.details;

import database.tmdb.requests.media.MovieRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRequestTest {
    private static final String EXPECTED_URL = "https://api.themoviedb.org/3/movie/popular";

    @Test
    void getBaseURL() {
        assertEquals(EXPECTED_URL, new MovieRequest().getBaseURL());
    }
}
