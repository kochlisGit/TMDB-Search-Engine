package database.tmdb.services.media.details;

import configs.tmdb.TMDBConfig;
import configs.tmdb.TMDBConfigReader;
import database.entities.media.Movie;
import database.entities.media.details.Credit;
import database.network.RequestDownloader;
import database.tmdb.services.media.MovieService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CreditServiceTest {
    private final List<Integer> idList;
    private final TMDBConfig tmdbConfig;

    public CreditServiceTest() {
        tmdbConfig = new TMDBConfigReader().readConfig();
        final List<Movie> movieList = new MovieService(
                new RequestDownloader(), tmdbConfig.getApiKey(), tmdbConfig.getReviewsNumPages()).getEntities();
        idList = movieList.stream().map(Movie::getId).collect(Collectors.toList());
    }

    @Test
    void getEntities() {
        final List<Credit> entityList = new CreditService(
                new RequestDownloader(), tmdbConfig.getApiKey(), tmdbConfig.getCreditsNumPages(), idList).getEntities();
        assertTrue(entityList.size() > idList.size());
    }
}
