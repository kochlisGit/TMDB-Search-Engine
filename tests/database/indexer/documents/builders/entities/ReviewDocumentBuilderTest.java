package database.indexer.documents.builders.entities;

import database.entities.media.details.Review;
import database.indexer.documents.builders.entities.ReviewDocumentBuilder;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.document.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewDocumentBuilderTest {

    @Test
    void toDocument() {
        final Review review = new Review(500, "anAuthor", "Nice movie.");
        final Document document = new ReviewDocumentBuilder().toDocument(review);

        assertEquals(review.getMediaId(), Integer.parseInt(document.get(DocumentFields.REVIEW_MOVIE_ID)));
        assertEquals(review.getAuthor(), document.get(DocumentFields.AUTHOR));
        assertEquals(review.getContent(), document.get(DocumentFields.CONTENT));
    }
}
