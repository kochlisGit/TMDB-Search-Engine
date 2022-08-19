package database.tmdb;

import configs.tmdb.TMDBConfig;
import configs.tmdb.TMDBConfigReader;
import database.entities.media.Movie;
import database.entities.media.details.Credit;
import database.entities.media.details.Review;
import database.network.RequestDownloader;
import database.tmdb.services.media.MovieService;
import database.tmdb.services.media.details.CreditService;
import database.tmdb.services.media.details.ReviewService;

import java.util.List;

public class TMDBFacade {
    final RequestDownloader requestDownloader;
    final TMDBConfig tmdbConfig;

    public TMDBFacade() {
        requestDownloader = new RequestDownloader();
        tmdbConfig = new TMDBConfigReader().readConfig();
    }

    public List<Movie> getMovies() {
        return new MovieService(
                requestDownloader, tmdbConfig.getApiKey(), tmdbConfig.getMoviesNumPages()).getEntities();
    }

    public List<Review> getReviews(final List<Integer> movieIdList) {
        return new ReviewService(
                requestDownloader, tmdbConfig.getApiKey(), tmdbConfig.getReviewsNumPages(), movieIdList).getEntities();
    }

    public List<Credit> getCredits(final List<Integer> movieIdList) {
        return new CreditService(
                requestDownloader, tmdbConfig.getApiKey(), tmdbConfig.getCreditsNumPages(), movieIdList).getEntities();
    }
}
