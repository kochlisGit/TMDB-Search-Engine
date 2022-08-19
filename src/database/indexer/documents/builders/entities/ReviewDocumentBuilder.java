package database.indexer.documents.builders.entities;

import database.entities.media.details.Review;
import database.indexer.documents.builders.DocumentBuilder;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class ReviewDocumentBuilder implements DocumentBuilder<Review> {

    @Override
    public Document toDocument(final Review review) {
        final Document doc = new Document();
        doc.add(new StringField(DocumentFields.REVIEW_MOVIE_ID, String.valueOf(review.getMediaId()), Field.Store.YES));
        doc.add(new TextField(DocumentFields.AUTHOR, review.getAuthor(), Field.Store.YES));
        doc.add(new TextField(DocumentFields.CONTENT, review.getContent(), Field.Store.YES));
        return doc;
    }
}
