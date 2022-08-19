package database.tmdb.services.media.details;

import configs.tmdb.TMDBConfig;
import configs.tmdb.TMDBConfigReader;
import database.entities.media.Movie;
import database.entities.media.details.Review;
import database.network.RequestDownloader;

import database.tmdb.services.media.MovieService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceTest {
    private final List<Integer> idList;
    private final TMDBConfig tmdbConfig;

    public ReviewServiceTest() {
        tmdbConfig = new TMDBConfigReader().readConfig();
        final List<Movie> movieList = new MovieService(
                new RequestDownloader(), tmdbConfig.getApiKey(), tmdbConfig.getReviewsNumPages()).getEntities();
        idList = movieList.stream().map(Movie::getId).collect(Collectors.toList());
    }

    @Test
    void getEntities() {
        final List<Review> entityList = new ReviewService(
                new RequestDownloader(), tmdbConfig.getApiKey(), tmdbConfig.getReviewsNumPages(), idList).getEntities();
        assertTrue(entityList.size() > idList.size());
    }
}
