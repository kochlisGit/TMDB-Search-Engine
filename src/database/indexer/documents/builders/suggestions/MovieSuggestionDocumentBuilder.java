package database.indexer.documents.builders.suggestions;

import database.entities.media.Movie;
import database.indexer.documents.builders.DocumentBuilder;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class MovieSuggestionDocumentBuilder implements DocumentBuilder<Movie> {

    @Override
    public Document toDocument(final Movie movie) {
        final Document doc = new Document();
        doc.add(new TextField(DocumentFields.SUGGESTION, movie.getTitle(), Field.Store.YES));
        return doc;
    }
}
