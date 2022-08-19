package database.tmdb.requests.builders;

import database.tmdb.requests.Request;
import database.tmdb.requests.media.MovieRequest;
import database.tmdb.requests.media.details.CreditRequest;
import database.tmdb.requests.media.details.ReviewRequest;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestBuilderTest {
    private static final String API_KEY = "ba73ff82af6b7ac884a65bf5247d4a11";
    private static final String PARAMS_STR = "?api_key=" + API_KEY + "&language=en-US&page=";
    private static final int MOVIE_ID = 550;
    private static final int NUM_PAGES = 3;

    private void assertRequests(final Request request, final List<URI> expectedRequestList) {
        final List<URI> uriList = new RequestBuilder(request, API_KEY).buildURIList(NUM_PAGES);
        for (int i = 0; i < NUM_PAGES; i++) {
            final String expectedRequest = expectedRequestList.get(i).toString();
            final String actualRequest = uriList.get(i).toString();
            assertEquals(expectedRequest, actualRequest);
        }
    }

    @Test
    void buildMoviesURIList() {
        final List<URI> expectedUrlList = new ArrayList<>();
        for (int i = 1; i < NUM_PAGES + 1; i++) {
            try {
                expectedUrlList.add(new URI("https://api.themoviedb.org/3/movie/popular" + PARAMS_STR + i));
            }
            catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        final Request request = new MovieRequest();
        assertRequests(request, expectedUrlList);
    }

    @Test
    void buildReviewsURIList() {
        final List<URI> expectedUrlList = new ArrayList<>();
        for (int i = 1; i < NUM_PAGES + 1; i++) {
            try {
                expectedUrlList.add(new URI("https://api.themoviedb.org/3/movie/" + MOVIE_ID + "/reviews" + PARAMS_STR + i));
            }
            catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        final Request request = new ReviewRequest(MOVIE_ID);
        assertRequests(request, expectedUrlList);
    }

    @Test
    void buildCreditsURIList() {
        final List<URI> expectedUrlList = new ArrayList<>();
        for (int i = 1; i < NUM_PAGES + 1; i++) {
            try {
                expectedUrlList.add(new URI("https://api.themoviedb.org/3/movie/" + MOVIE_ID + "/credits" + PARAMS_STR + i));
            }
            catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        final Request request = new CreditRequest(MOVIE_ID);
        assertRequests(request, expectedUrlList);
    }
}
