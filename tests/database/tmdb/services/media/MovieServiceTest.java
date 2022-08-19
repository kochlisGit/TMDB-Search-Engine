package database.tmdb.services.media;

import configs.tmdb.TMDBConfig;
import configs.tmdb.TMDBConfigReader;
import database.entities.media.Movie;
import database.network.RequestDownloader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    @Test
    void getEntities() {
        final TMDBConfig tmdbConfig = new TMDBConfigReader().readConfig();
        final List<Movie> entityList = new MovieService(
                new RequestDownloader(), tmdbConfig.getApiKey(), tmdbConfig.getMoviesNumPages()).getEntities();
        assertTrue(entityList.size() > 0);
    }
}
