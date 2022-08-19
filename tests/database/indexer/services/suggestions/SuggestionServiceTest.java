package database.indexer.services.suggestions;

import database.entities.media.Movie;
import database.entities.media.details.Credit;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuggestionServiceTest {
    private static final int NUM_SUGGESTIONS = 10;

    private final SuggestionService suggestionService = new SuggestionService(NUM_SUGGESTIONS);

    @BeforeEach
    void init() {
        suggestionService.deleteAll();
    }

    @Test
        @Order(1)
        void writeMovies() {
            final List<Movie> movieList = List.of(
                    new Movie(500, "/poster1.jpg", "the half-blood prince", "Overview1", "1990-10-10", new int[]{14, 28}, 100.608, 6.7),
                    new Movie(450, "/poster2.jpg", "bloody nights", "Overview2", "2022-07-06", new int[]{28, 14}, 200.608, 5.9),
                    new Movie(400, "/poster2.jpg", "the big blow", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7),
                    new Movie(400, "/poster2.jpg", "bleeding nights", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7)
            );
            final int numDocs = suggestionService.writeMovies(movieList);
            assertEquals(movieList.size(), numDocs);
    }

    @Test
    @Order(2)
    void writeCredits() {
        final List<Credit> creditList = List.of(
                new Credit(550, "Edward Norton", "The Narrator", 16.198),
                new Credit(550, "Dir Milchan", "Director", 2.2)
        );
        final int numDocs = suggestionService.writeCredits(creditList);
        assertEquals(creditList.size(), numDocs);
        assertEquals(creditList.size(), suggestionService.getNumDocs());
    }

    @Test
    @Order(3)
    void suggestTerms() {
        final List<Movie> movieList = List.of(
                new Movie(500, "/poster1.jpg", "the half-blood prince", "Overview1", "1990-10-10", new int[]{14, 28}, 100.608, 6.7),
                new Movie(450, "/poster2.jpg", "bloody nights", "Overview2", "2022-07-06", new int[]{28, 14}, 200.608, 5.9),
                new Movie(400, "/poster2.jpg", "the big blow", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7),
                new Movie(400, "/poster2.jpg", "bleeding nights", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7)
        );
        suggestionService.writeMovies(movieList);

        final String term = "blo";
        final List<String> suggestedTerms = suggestionService.suggestTerms(term);
        assertEquals(3, suggestedTerms.size());
    }

    @Test
    @Order(4)
    void deleteAll() {
        final List<Movie> movieList = List.of(
                new Movie(500, "/poster1.jpg", "the half-blood prince", "Overview1", "1990-10-10", new int[]{14, 28}, 100.608, 6.7),
                new Movie(450, "/poster2.jpg", "bloody nights", "Overview2", "2022-07-06", new int[]{28, 14}, 200.608, 5.9),
                new Movie(400, "/poster2.jpg", "the big blow", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7),
                new Movie(400, "/poster2.jpg", "bleeding nights", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7)
        );
        suggestionService.writeMovies(movieList);
        suggestionService.deleteAll();

        final String term = "blo";
        final List<String> suggestedTerms = suggestionService.suggestTerms(term);
        assertEquals(0, suggestedTerms.size());
    }

    @Test
    @Order(5)
    void close() {
        assertDoesNotThrow(suggestionService::close);
    }
}
