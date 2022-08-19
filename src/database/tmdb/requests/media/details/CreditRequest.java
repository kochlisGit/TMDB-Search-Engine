package database.tmdb.requests.media.details;

import database.tmdb.requests.Request;

public class CreditRequest implements Request {
    private final int movieId;

    public CreditRequest(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String getBaseURL() {
        return "https://api.themoviedb.org/3/movie/" + movieId + "/credits";
    }
}
