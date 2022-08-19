package database.indexer.documents.mappers;

import database.entities.media.details.Credit;
import database.indexer.documents.builders.entities.CreditDocumentBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditDocumentMapperTest {

    @Test
    void toEntity() {
        final Credit expectedCredit = new Credit(500, "badass name", "badass role", 100.53);
        final Credit actualCredit = new CreditDocumentMapper().toEntity(new CreditDocumentBuilder().toDocument(expectedCredit));
        assertEquals(expectedCredit.toString(), actualCredit.toString());
    }
}
