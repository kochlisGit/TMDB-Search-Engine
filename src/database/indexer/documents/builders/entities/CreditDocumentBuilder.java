package database.indexer.documents.builders.entities;

import database.entities.media.details.Credit;
import database.indexer.documents.builders.DocumentBuilder;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.document.*;

public class CreditDocumentBuilder implements DocumentBuilder<Credit> {

    @Override
    public Document toDocument(final Credit credit) {
        final Document doc = new Document();
        doc.add(new StringField(DocumentFields.CREDIT_MOVIE_ID, String.valueOf(credit.getMediaId()), Field.Store.YES));
        doc.add(new TextField(DocumentFields.ROLE, credit.getRole(), Field.Store.YES));
        doc.add(new TextField(DocumentFields.NAME, credit.getName(), Field.Store.YES));
        doc.add(new StoredField(DocumentFields.CREDIT_POPULARITY, credit.getPopularity()));
        doc.add(new NumericDocValuesField(DocumentFields.CREDIT_POPULARITY, Double.doubleToRawLongBits(credit.getPopularity())));
        return doc;
    }
}
