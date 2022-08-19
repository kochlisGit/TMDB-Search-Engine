package database.tmdb.json.parsers;

import com.jsoniter.JsonIterator;
import database.entities.media.details.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ReviewParser implements Parser<Review> {

    @Override
    public List<Review> parseEntitiesFromString(final List<CompletableFuture<String>> pageList) {
        final List<Review> reviewList = new ArrayList<>();

        pageList.parallelStream().forEach(page -> {
            try {
                final JsonIterator iter = JsonIterator.parse(page.get());
                Review review = new Review();
                int movieId = 0;

                for (String field = iter.readObject(); field != null; field = iter.readObject()) {
                    if ("id".equals(field)) {
                        movieId = iter.readInt();
                    }
                    else if ("results".equals(field)) {
                        while (iter.readArray()) {
                            for (String reviewField = iter.readObject(); reviewField != null; reviewField = iter.readObject()) {
                                switch (reviewField) {
                                    case "author":
                                        review = new Review();
                                        review.setMediaId(movieId);
                                        review.setAuthor(iter.readString());
                                        break;
                                    case "content":
                                        review.setContent(iter.readString());
                                        reviewList.add(review);
                                        break;
                                    default:
                                        iter.skip();
                                }
                            }
                        }
                    }
                    else {
                        iter.skip();
                    }
                }
                iter.close();
            }
            catch (InterruptedException | ExecutionException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        return reviewList;
    }
}
