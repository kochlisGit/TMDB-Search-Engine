package database.indexer.documents.mappers;

import database.entities.media.details.Review;
import database.indexer.documents.builders.entities.ReviewDocumentBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewDocumentMapperTest {

    @Test
    void toEntity() {
        final Review expectedReview = new Review(500, "anAuthor", "Nice movie.");
        final Review actualReview = new ReviewDocumentMapper().toEntity(new ReviewDocumentBuilder().toDocument(expectedReview));
        assertEquals(expectedReview.toString(), actualReview.toString());
    }
}
