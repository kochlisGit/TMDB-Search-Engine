package database.indexer.suggester;

import database.entities.media.Movie;
import database.indexer.documents.builders.suggestions.MovieSuggestionDocumentBuilder;
import database.indexer.indexes.IndexManager;
import database.indexer.indexes.suggester.SuggestionIndex;
import database.indexer.readers.ReaderManager;
import database.indexer.writers.DocumentWriter;
import database.indexer.writers.WriterManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentSuggesterTest {
    private static final int NUM_SUGGESTIONS = 10;
    private static final String TERM = "blo";

    @BeforeAll
    static void init() {
        final IndexManager index = new SuggestionIndex();
        final WriterManager writer = new WriterManager(index.getDirectory());

        final List<Movie> movieList = List.of(
                new Movie(500, "/poster1.jpg", "the half-blood prince", "Overview1", "1990-10-10", new int[]{14, 28}, 100.608, 6.7),
                new Movie(450, "/poster2.jpg", "bloody nights", "Overview2", "2022-07-06", new int[]{28, 14}, 200.608, 5.9),
                new Movie(400, "/poster2.jpg", "the big blow", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7),
                new Movie(400, "/poster2.jpg", "bleeding nights", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7)
        );
        new DocumentWriter<>(writer.getIndexWriter(), new MovieSuggestionDocumentBuilder()).writeDocuments(movieList);

        writer.close();
        index.close();
    }

    @AfterAll()
    static void cleanup() {
        final IndexManager index = new SuggestionIndex();
        final WriterManager writer = new WriterManager(index.getDirectory());

        try {
            writer.getIndexWriter().deleteAll();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        writer.close();
        index.close();
    }

    @Test
    void suggestTerms() {
        final IndexManager index = new SuggestionIndex();
        final ReaderManager reader = new ReaderManager(index.getDirectory());

        final SuggesterManager suggester = new SuggesterManager(index.getDirectory(), reader.getIndexReader());
        final DocumentSuggester documentSuggester = new DocumentSuggester(suggester.getIndexSuggester(), NUM_SUGGESTIONS);
        final List<String> suggestionList = documentSuggester.suggestTerms(TERM);
        assertEquals(3, suggestionList.size());
    }
}
