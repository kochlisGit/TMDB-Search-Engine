package database.indexer.documents.mappers;

import database.entities.media.details.Review;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.document.Document;

public class ReviewDocumentMapper implements DocumentMapper<Review> {

    @Override
    public Review toEntity(final Document document) {
        return new Review(
                Integer.parseInt(document.get(DocumentFields.REVIEW_MOVIE_ID)),
                document.get(DocumentFields.AUTHOR),
                document.get(DocumentFields.CONTENT)
        );
    }
}
