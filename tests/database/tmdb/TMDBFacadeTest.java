package database.tmdb;

import database.entities.media.Movie;
import database.entities.media.details.Credit;
import database.entities.media.details.Review;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TMDBFacadeTest {
    final TMDBFacade tmdbFacade;
    final List<Integer> movieIdList;

    public TMDBFacadeTest() {
        tmdbFacade = new TMDBFacade();
        movieIdList = tmdbFacade.getMovies().stream().map(Movie::getId).collect(Collectors.toList());
    }

    @Test
    public void getMovies() {
        final List<Movie> entityList = tmdbFacade.getMovies();
        assertTrue(entityList.size() > 0);
    }

    @Test
    public void getReviews() {
        final List<Review> entityList = tmdbFacade.getReviews(movieIdList);
        assertTrue(entityList.size() > 0);
    }

    @Test
    public void getCredits() {
        final List<Credit> entityList = tmdbFacade.getCredits(movieIdList);
        assertTrue(entityList.size() > 0);
    }
}
