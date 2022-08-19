package database.tmdb.json.parsers;

import database.entities.media.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class MovieParserTest {
    private static final String PAGE_STR =
            "{\"page\":1,\"results\":[{\"adult\":false,\"backdrop_path\":\"/p1F51Lvj3sMopG948F5HsBbl43C.jpg\"," +
            "\"genre_ids\":[1, 2, 3],\"id\":1,\"original_language\":\"en\",\"overview\":\"This is overview 1\"," +
            "\"popularity\":100.608,\"poster_path\":\"/poster1.jpg\",\"release_date\":\"1990-10-10\"," +
            "\"title\":\"title 1\",\"video\":false,\"vote_average\":6.7,\"vote_count\":1554}," +
            "{\"adult\":false,\"backdrop_path\":\"/p1F51Lvj3sMopG948F5HsBbl43C.jpg\"," +
            "\"genre_ids\":[10, 20, 30],\"id\":2,\"original_language\":\"en\",\"overview\":\"This is overview 2\"," +
            "\"popularity\":200.608,\"poster_path\":\"/poster2.jpg\",\"release_date\":\"2022-07-06\"," +
            "\"title\":\"title 2\",\"video\":false,\"vote_average\":4.7,\"vote_count\":1554}" +
            "],\"total_pages\":34575,\"total_results\":691487}";
    private final List<CompletableFuture<String>> pageList;
    private final List<Movie> expectedEntityList;

    public MovieParserTest() {
        final CompletableFuture<String> completablePage = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            completablePage.complete(PAGE_STR);
        });
        pageList = List.of(completablePage);

        expectedEntityList = List.of(
                new Movie(1, "/poster1.jpg", "title 1", "This is overview 1",
                        "1990-10-10", new int[]{1, 2, 3}, 100.608, 6.7),
                new Movie(2, "/poster2.jpg", "title 2", "This is overview 2",
                        "2022-07-06", new int[]{10, 20, 30}, 200.608, 4.7)
        );
    }

    @Test
    void parseEntitiesFromString() {
        final List<Movie> actualEntityList = new MovieParser().parseEntitiesFromString(pageList);

        assertEquals(2, actualEntityList.size());
        for (int i = 0; i < 2; i++) {
            assertEquals(expectedEntityList.get(i).toString(), actualEntityList.get(i).toString());
        }
    }
}
