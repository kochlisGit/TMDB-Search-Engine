package database.tmdb.services.media.details;

import database.entities.media.details.Review;
import database.network.RequestDownloader;
import database.tmdb.json.parsers.ReviewParser;
import database.tmdb.requests.Request;
import database.tmdb.requests.media.details.ReviewRequest;

import java.util.List;

public class ReviewService extends MediaDetailsService<Review> {

    public ReviewService(RequestDownloader requestDownloader, String apiKey, int numPages,
                         List<Integer> mediaIdList) {
        super(requestDownloader, new ReviewParser(), apiKey, numPages, mediaIdList);
    }

    @Override
    protected Request getRequest(int id) {
        return new ReviewRequest(id);
    }
}
