package database.indexer.documents.builders.entities;

import database.entities.media.details.Credit;
import database.indexer.documents.builders.entities.CreditDocumentBuilder;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.document.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditDocumentBuilderTest {

    @Test
    void toDocument() {
        final Credit credit = new Credit(500, "badass name", "badass role", 100.53);
        final Document document = new CreditDocumentBuilder().toDocument(credit);

        assertEquals(credit.getMediaId(), Integer.parseInt(document.get(DocumentFields.CREDIT_MOVIE_ID)));
        assertEquals(credit.getName(), document.get(DocumentFields.NAME));
        assertEquals(credit.getRole(), document.get(DocumentFields.ROLE));
        assertEquals(credit.getPopularity(), Double.parseDouble(document.get(DocumentFields.CREDIT_POPULARITY)));
    }
}
