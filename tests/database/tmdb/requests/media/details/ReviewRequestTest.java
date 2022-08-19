package database.tmdb.requests.media.details;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewRequestTest {
    private static final int MOVIE_ID = 550;
    private static final String EXPECTED_URL = "https://api.themoviedb.org/3/movie/" + MOVIE_ID + "/reviews";

    @Test
    void getBaseURL() {
        assertEquals(EXPECTED_URL, new ReviewRequest(MOVIE_ID).getBaseURL());
    }
}
