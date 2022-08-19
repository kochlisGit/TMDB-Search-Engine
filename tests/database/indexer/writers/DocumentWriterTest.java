package database.indexer.writers;

import database.entities.media.Movie;
import database.entities.media.details.Credit;
import database.entities.media.details.Review;
import database.indexer.documents.builders.entities.CreditDocumentBuilder;
import database.indexer.documents.builders.entities.MovieDocumentBuilder;
import database.indexer.documents.builders.entities.ReviewDocumentBuilder;
import database.indexer.documents.builders.suggestions.CreditSuggestionDocumentBuilder;
import database.indexer.documents.builders.suggestions.MovieSuggestionDocumentBuilder;
import database.indexer.indexes.IndexManager;
import database.indexer.indexes.entities.CreditIndex;
import database.indexer.indexes.entities.MovieIndex;
import database.indexer.indexes.entities.ReviewIndex;
import database.indexer.indexes.suggester.SuggestionIndex;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentWriterTest {
    private static final IndexManager movieIndex = new MovieIndex();
    private static final IndexManager reviewIndex = new ReviewIndex();
    private static final IndexManager creditIndex = new CreditIndex();
    private static final IndexManager suggestionIndex = new SuggestionIndex();
    private static final WriterManager movieWriter = new WriterManager(movieIndex.getDirectory());
    private static final WriterManager reviewWriter = new WriterManager(reviewIndex.getDirectory());
    private static final WriterManager creditWriter = new WriterManager(creditIndex.getDirectory());
    private static final WriterManager suggestionWriter = new WriterManager(suggestionIndex.getDirectory());

    private static void cleanup(final IndexManager index, final WriterManager writerManager) {
        try {
            writerManager.getIndexWriter().deleteAll();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        writerManager.close();
        index.close();
    }

    @AfterAll()
    static void cleanupAll() {
        cleanup(movieIndex, movieWriter);
        cleanup(reviewIndex, reviewWriter);
        cleanup(creditIndex, creditWriter);
        cleanup(suggestionIndex, suggestionWriter);
    }

    @Test
    public void writeMovies() {
        final List<Movie> movieList = List.of(
                new Movie(500, "/poster1.jpg", "lord of the rings", "One rule to ring them all,", "1990-10-10", new int[]{14, 28}, 100.608, 6.7),
                new Movie(450, "/poster2.jpg", "lord of the strings", "One ring to rule them all", "2022-07-06", new int[]{28, 14}, 200.608, 5.9),
                new Movie(400, "/poster2.jpg", "ring of the lords", "All to rule one Ring", "2000-07-06", new int[]{28, 14}, 35, 5.7)
        );
        final DocumentWriter<Movie> documentWriter = new DocumentWriter<>(movieWriter.getIndexWriter(), new MovieDocumentBuilder());
        final int numIndexedDocs = documentWriter.writeDocuments(movieList);
        assertEquals(movieList.size(), numIndexedDocs);
    }

    @Test
    public void writeReviews() {
        final List<Review> reviewList = List.of(
                new Review(500, "Madmax93", "such a nice movie with trolling rings wow"),
                new Review(500, "Goddard72", "very nice movie")
        );
        final DocumentWriter<Review> documentWriter = new DocumentWriter<>(reviewWriter.getIndexWriter(), new ReviewDocumentBuilder());
        final int numIndexedDocs = documentWriter.writeDocuments(reviewList);
        assertEquals(reviewList.size(), numIndexedDocs);
    }

    @Test
    public void writeCredits() {
        final List<Credit> creditList = List.of(
                new Credit(550, "Edward Norton", "The Narrator", 16.198),
                new Credit(550, "Dir Milchan", "Director", 2.2)
        );
        final DocumentWriter<Credit> documentWriter = new DocumentWriter<>(creditWriter.getIndexWriter(), new CreditDocumentBuilder());
        final int numIndexedDocs = documentWriter.writeDocuments(creditList);
        assertEquals(creditList.size(), numIndexedDocs);
    }

    @Test
    public void writeMovieSuggestions() {
        final List<Movie> movieList = List.of(
                new Movie(500, "/poster1.jpg", "lord of the rings", "One rule to ring them all,", "1990-10-10", new int[]{14, 28}, 100.608, 6.7),
                new Movie(450, "/poster2.jpg", "lord of the strings", "One ring to rule them all", "2022-07-06", new int[]{28, 14}, 200.608, 5.9),
                new Movie(400, "/poster2.jpg", "ring of the lords", "All to rule one Ring", "2000-07-06", new int[]{28, 14}, 35, 5.7)
        );
        final DocumentWriter<Movie> documentWriter = new DocumentWriter<>(suggestionWriter.getIndexWriter(), new MovieSuggestionDocumentBuilder());
        final int numIndexedDocs = documentWriter.writeDocuments(movieList);
        assertEquals(movieList.size(), numIndexedDocs);
    }

    @Test
    public void writeCreditSuggestions() {
        final List<Credit> creditList = List.of(
                new Credit(550, "Edward Norton", "The Narrator", 16.198),
                new Credit(550, "Dir Milchan", "Director", 2.2)
        );
        final DocumentWriter<Credit> documentWriter = new DocumentWriter<>(suggestionWriter.getIndexWriter(), new CreditSuggestionDocumentBuilder());
        final int numIndexedDocs = documentWriter.writeDocuments(creditList);
        assertEquals(creditList.size(), numIndexedDocs);
    }
}
