package database.tmdb.requests.media.details;

public class ReviewRequest extends MediaDetailsRequest {
    public ReviewRequest(int movieId) {
        super(movieId);
    }

    @Override
    public String getBaseURL() {
        return "https://api.themoviedb.org/3/movie/" + mediaId + "/reviews";
    }
}
