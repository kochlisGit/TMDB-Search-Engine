package server;

import entities.Actor;
import entities.Movie;
import entities.Review;
import parsers.ActorParser;
import parsers.EntityParser;
import parsers.MovieParser;
import parsers.ReviewParser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EntityRetrieverFacade extends ActorParser {
    private static final String PARAM_POPULAR = "popular";
    private static final String PARAM_TOP_RATED = "top_rated";
    private static final String PARAM_SIMILAR = "/similar";
    private static final String PARAM_RECOMMENDATIONS = "/recommendations";
    private static final String PARAM_REVIEWS = "/reviews";
    private static final String PARAM_ACTORS = "/credits";

    private final EntityParser<Movie> movieParser;
    private final EntityParser<Review> reviewParser;
    private final EntityParser<Actor> actorParser;

    public EntityRetrieverFacade() {
        this.movieParser = new MovieParser();
        this.reviewParser = new ReviewParser();
        this.actorParser = new ActorParser();
    }

    public List<Movie> retrieveAllMovies(int nPages) {
        return new EntityRetriever<Movie>().retrieveEntities(
                movieParser,
                Arrays.asList(PARAM_POPULAR, PARAM_TOP_RATED),
                "",
                nPages
        );
    }
    public List<Movie> retrieveSimilarMovies(String movieId) {
        return  new EntityRetriever<Movie>().retrieveEntities(
                movieParser,
                Collections.singletonList(movieId),
                PARAM_SIMILAR,
                1
        );
    }
    public List<Movie> retrieveRecommendedMovies(String movieId) {
        return  new EntityRetriever<Movie>().retrieveEntities(
                movieParser,
                Collections.singletonList(movieId),
                PARAM_RECOMMENDATIONS,
                1
        );
    }
    public List<Review> retrieveReviews(List<String> requestList) {
        return new EntityRetriever<Review>().retrieveEntities(
                reviewParser,
                requestList,
                PARAM_REVIEWS,
                1
        );
    }
    public List<Actor> retrieveActors(List<String> requestList) {
        return new EntityRetriever<Actor>().retrieveEntities(
                actorParser,
                requestList,
                PARAM_ACTORS,
                1
        );
    }
}
