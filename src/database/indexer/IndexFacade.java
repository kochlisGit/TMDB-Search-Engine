package database.indexer;

import configs.database.DatabaseConfig;
import configs.database.DatabaseConfigReader;
import database.entities.media.Movie;
import database.entities.media.details.Credit;
import database.entities.media.details.Review;
import database.indexer.services.entities.CreditService;
import database.indexer.services.entities.MovieService;
import database.indexer.services.entities.ReviewService;
import database.indexer.services.suggestions.SuggestionService;
import database.tmdb.TMDBFacade;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class IndexFacade {
    private final MovieService movieService;
    private final ReviewService reviewService;
    private final CreditService creditService;
    private final SuggestionService suggestionService;
    private final boolean cleanupAfterTermination;

    public IndexFacade() {
        final DatabaseConfig config = new DatabaseConfigReader().readConfig();
        movieService = new MovieService(config.getHitsPerPage());
        reviewService = new ReviewService(config.getHitsPerPage());
        creditService = new CreditService(config.getHitsPerPage());
        suggestionService = new SuggestionService(config.getNumSuggestions());
        cleanupAfterTermination = config.isCleanupAfterTermination();

        if (cleanupAfterTermination) {
            createDatabase();
        }
    }

    private void createDatabase() {
        final TMDBFacade tmdbFacade = new TMDBFacade();
        final List<Movie> movieList = tmdbFacade.getMovies();
        final List<Integer> movieIdsList = movieList.stream()
                .map(Movie::getId)
                .collect(Collectors.toList());
        final List<Review> reviewList = tmdbFacade.getReviews(movieIdsList);
        final List<Credit> creditList = tmdbFacade.getCredits(movieIdsList);
        movieService.writeEntities(movieList);
        reviewService.writeEntities(reviewList);
        creditService.writeEntities(creditList);
    }

    private void destroyDatabase() {
        movieService.deleteAll();
        reviewService.deleteAll();
        creditService.deleteAll();
        suggestionService.deleteAll();
    }

    public List<Movie> readMovies(final Query query, final Sort sort) {
        return movieService.readEntities(query, sort);
    }

    public List<Review> readReviews(final Query query, final Sort sort) {
        return reviewService.readEntities(query, sort);
    }

    public List<Credit> readCredits(final Query query, final Sort sort) {
        return creditService.readEntities(query, sort);
    }

    public List<String> getSuggestions(final String term) {
        return suggestionService.suggestTerms(term);
    }

    public void close() {
        if (cleanupAfterTermination) {
            destroyDatabase();

            movieService.close();
            reviewService.close();
            creditService.close();
            suggestionService.close();
        }
    }
}
