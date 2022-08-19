package database.tmdb.requests.media;

import database.tmdb.requests.Request;

public class MovieRequest implements Request {

    @Override
    public String getBaseURL() {
        return "https://api.themoviedb.org/3/movie/popular";
    }
}
