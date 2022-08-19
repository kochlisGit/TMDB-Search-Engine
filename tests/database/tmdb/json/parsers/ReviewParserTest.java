package database.tmdb.json.parsers;

import database.entities.media.details.Review;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class ReviewParserTest {
    private static final String PAGE_STR =
            "{\"id\":550,\"page\":1,\"results\":[{\"author\":\"Goddard1\",\"author_details\":{\"name\":\"\",\"username\":\"Goddard\"," +
                    "\"rating\":null},\"content\":\"Pretty awesome movie.\",\"created_at\":\"2018-06-09T17:51:53.359Z\",\"id\":\"5b1c13b9c3a36848f2026384\"," +
                    "\"updated_at\":\"2021-06-23T15:58:09.421Z\",\"url\":\"https\"}," +
                    "{\"author\":\"Goddard2\",\"author_details\":{\"name\":\"\",\"username\":\"Goddard\"," +
                    "\"rating\":null},\"content\":\"Pretty bad movie.\",\"created_at\":\"2018-06-09T17:51:53.359Z\",\"id\":\"5b1c13b9c3a36848f2026384\"," +
                    "\"updated_at\":\"2021-06-23T15:58:09.421Z\",\"url\":\"https\"}],\"total_pages\":1,\"total_results\":7}";
    private final List<CompletableFuture<String>> pageList;
    private final List<Review> expectedEntityList;

    public ReviewParserTest() {
        final CompletableFuture<String> completablePage = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            completablePage.complete(PAGE_STR);
        });
        pageList = List.of(completablePage);

        expectedEntityList = List.of(
                new Review(550, "Goddard1", "Pretty awesome movie."),
                new Review(550, "Goddard2", "Pretty bad movie.")
        );
    }

    @Test
    void parseEntitiesFromString() {
        final List<Review> actualEntityList = new ReviewParser().parseEntitiesFromString(pageList);

        assertEquals(2, actualEntityList.size());
        for (int i = 0; i < 2; i++) {
            assertEquals(expectedEntityList.get(i).toString(), actualEntityList.get(i).toString());
        }
    }
}
