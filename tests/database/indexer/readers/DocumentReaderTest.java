package database.indexer.readers;

import database.entities.media.Movie;
import database.entities.media.details.Credit;
import database.entities.media.details.Review;
import database.indexer.analyzers.IndexAnalyzer;
import database.indexer.documents.builders.entities.CreditDocumentBuilder;
import database.indexer.documents.builders.entities.MovieDocumentBuilder;
import database.indexer.documents.builders.entities.ReviewDocumentBuilder;
import database.indexer.documents.fields.DocumentFields;
import database.indexer.documents.mappers.CreditDocumentMapper;
import database.indexer.documents.mappers.MovieDocumentMapper;
import database.indexer.documents.mappers.ReviewDocumentMapper;
import database.indexer.indexes.IndexManager;
import database.indexer.indexes.entities.CreditIndex;
import database.indexer.indexes.entities.MovieIndex;
import database.indexer.indexes.entities.ReviewIndex;
import database.indexer.writers.DocumentWriter;
import database.indexer.writers.WriterManager;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentReaderTest {
    private static final int NUM_HITS = 10;
    private static final String MOVIE_QUERY_FIELD = DocumentFields.TITLE;
    private static final String MOVIE_QUERY_TEXT = "rings~";
    private static final int EXPECTED_MOVIE_RESULTS = 3;
    private static final String REVIEW_QUERY_FIELD = DocumentFields.REVIEW_MOVIE_ID;
    private static final String REVIEW_QUERY_TEXT = "1";
    private static final int EXPECTED_REVIEW_RESULTS = 2;
    private static final String CREDIT_QUERY_FIELD = DocumentFields.CREDIT_MOVIE_ID;
    private static final String CREDIT_QUERY_TEXT = "2";
    private static final int EXPECTED_CREDIT_RESULTS = 2;

    private static IndexManager movieIndex;
    private static IndexManager reviewIndex;
    private static IndexManager creditIndex;
    private static WriterManager movieWriter;
    private static WriterManager reviewWriter;
    private static WriterManager creditWriter;

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

    @BeforeAll
    static void initAll() {
        movieIndex = new MovieIndex();
        reviewIndex = new ReviewIndex();
        creditIndex = new CreditIndex();
        movieWriter = new WriterManager(movieIndex.getDirectory());
        reviewWriter = new WriterManager(reviewIndex.getDirectory());
        creditWriter = new WriterManager(creditIndex.getDirectory());

        final List<Movie> movieList = List.of(
                new Movie(1, "path1", "Lord of the Ring", "one ring to rule them all", "01-01-1990", new int[]{28, 12}, 10, 100),
                new Movie(2, "path2", "Rings of the Lord", "10 rings to rule one", "01-01-1990", new int[]{28, 12}, 10, 100),
                new Movie(3, "path3", "Lord of the Strings", "one rule to ring them all", "01-01-1990", new int[]{28, 12}, 10, 100)
        );
        final List<Review> reviewList = List.of(
                new Review(1, "author1", "Great review"),
                new Review(1, "author2", "amazing movie")
        );
        final List<Credit> creditList = List.of(
                new Credit(2, "actor1", "char1", 100),
                new Credit(3, "actor2", "char2", 200),
                new Credit(2, "dir", "Director", 1000)
        );
        new DocumentWriter<>(movieWriter.getIndexWriter(), new MovieDocumentBuilder()).writeDocuments(movieList);
        new DocumentWriter<>(reviewWriter.getIndexWriter(), new ReviewDocumentBuilder()).writeDocuments(reviewList);
        new DocumentWriter<>(creditWriter.getIndexWriter(), new CreditDocumentBuilder()).writeDocuments(creditList);

        try {
            movieWriter.getIndexWriter().commit();
            reviewWriter.getIndexWriter().commit();
            creditWriter.getIndexWriter().commit();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void cleanupAll() {
        cleanup(movieIndex, movieWriter);
        cleanup(reviewIndex, reviewWriter);
        cleanup(creditIndex, creditWriter);
    }

    private Query getQuery(final String queryField, final String queryText) {
        try {
            return new QueryParser(queryField, IndexAnalyzer.ANALYZER.analyzer).parse(queryText);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    void searchMovies() {
        final ReaderManager readerManager = new ReaderManager(movieIndex.getDirectory());
        final DocumentReader<Movie> documentReader = new DocumentReader<>(readerManager.getIndexSearcher(), new MovieDocumentMapper(), NUM_HITS);
        final Query query = getQuery(MOVIE_QUERY_FIELD, MOVIE_QUERY_TEXT);
        final Sort sort = new Sort(SortField.FIELD_SCORE);
        final List<Movie> resultsList = documentReader.readDocuments(query, sort);
        assertEquals(EXPECTED_MOVIE_RESULTS, resultsList.size());
    }

    @Test
    void searchReviews() {
        final ReaderManager readerManager = new ReaderManager(reviewIndex.getDirectory());
        final DocumentReader<Review> documentReader = new DocumentReader<>(readerManager.getIndexSearcher(), new ReviewDocumentMapper(), NUM_HITS);
        final Query query = getQuery(REVIEW_QUERY_FIELD, REVIEW_QUERY_TEXT);
        final Sort sort = new Sort(SortField.FIELD_SCORE);
        final List<Review> resultsList = documentReader.readDocuments(query, sort);
        assertEquals(EXPECTED_REVIEW_RESULTS, resultsList.size());
    }

    @Test
    void searchCredits() {
        final ReaderManager readerManager = new ReaderManager(creditIndex.getDirectory());
        final DocumentReader<Credit> documentReader = new DocumentReader<>(readerManager.getIndexSearcher(), new CreditDocumentMapper(), NUM_HITS);
        final Query query = getQuery(CREDIT_QUERY_FIELD, CREDIT_QUERY_TEXT);
        final Sort sort = new Sort(SortField.FIELD_SCORE);
        final List<Credit> resultsList = documentReader.readDocuments(query, sort);
        assertEquals(EXPECTED_CREDIT_RESULTS, resultsList.size());
    }
}
