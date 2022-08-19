package database.indexer.services.entities;

import database.entities.media.details.Review;
import database.indexer.analyzers.IndexAnalyzer;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceTest {
    private static final int hitsPerPage = 10;

    private final ReviewService reviewService = new ReviewService(hitsPerPage);

    @Test
    @Order(1)
    void writeReviews() {
        final List<Review> reviewList = List.of(
                new Review(1, "author1", "great and amazing movie by"),
                new Review(1, "author2", "this movie was truly truly amazing")
        );
        final int numDocs = reviewService.writeEntities(reviewList);
        assertEquals(reviewList.size(), numDocs);
    }

    @Test
    @Order(2)
    void readMovies() {
        List<Review> reviewList;
        try {
            final Query query = new QueryParser(DocumentFields.CONTENT, IndexAnalyzer.ANALYZER.analyzer).parse("amazing");
            final Sort sort = new Sort(SortField.FIELD_SCORE);
            reviewList = reviewService.readEntities(query, sort);
        }
        catch (ParseException e) {
            e.printStackTrace();
            reviewList = null;
        }
        assertNotNull(reviewList);
    }

    @Test
    @Order(3)
    void deleteAll() {
        final List<Review> reviewList = List.of(
                new Review(1, "author1", "great and amazing movie by"),
                new Review(1, "author2", "this movie was truly truly amazing")
        );
        reviewService.writeEntities(reviewList);
        reviewService.deleteAll();

        List<Review> resultsList;
        try {
            final Query query = new QueryParser(DocumentFields.CONTENT, IndexAnalyzer.ANALYZER.analyzer).parse("amazing");
            final Sort sort = new Sort(SortField.FIELD_SCORE);
            resultsList = reviewService.readEntities(query, sort);
        }
        catch (ParseException e) {
            e.printStackTrace();
            resultsList = null;
        }
        assertNotNull(resultsList);
        assertEquals(0, resultsList.size());
    }

    @Test
    @Order(4)
    void close() {
        assertDoesNotThrow(reviewService::close);
    }
}
