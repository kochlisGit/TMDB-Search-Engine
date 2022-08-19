package database.indexer.documents.builders.suggestions;

import database.entities.media.details.Credit;
import database.indexer.documents.builders.DocumentBuilder;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class CreditSuggestionDocumentBuilder implements DocumentBuilder<Credit> {

    @Override
    public Document toDocument(final Credit credit) {
        final Document doc = new Document();
        doc.add(new TextField(DocumentFields.SUGGESTION, credit.getName(), Field.Store.YES));
        return doc;
    }
}
