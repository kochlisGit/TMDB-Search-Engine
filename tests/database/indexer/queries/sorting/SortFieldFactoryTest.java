package database.indexer.queries.sorting;

import database.indexer.documents.fields.DocumentFields;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortFieldFactoryTest {

    @Test
    void getSort() {
        assertEquals(DocumentFields.TITLE, SortFieldFactory.getSort(DocumentFields.TITLE).getSort()[0].getField());
        assertEquals(DocumentFields.RELEASE_DATE, SortFieldFactory.getSort(DocumentFields.RELEASE_DATE).getSort()[0].getField());
        assertEquals(DocumentFields.RATING, SortFieldFactory.getSort(DocumentFields.RATING).getSort()[0].getField());
        assertEquals(DocumentFields.POPULARITY, SortFieldFactory.getSort(DocumentFields.POPULARITY).getSort()[0].getField());
        assertEquals(DocumentFields.CREDIT_POPULARITY, SortFieldFactory.getSort(DocumentFields.CREDIT_POPULARITY).getSort()[0].getField());
        assertNull(SortFieldFactory.getSort("SCORE").getSort()[0].getField());
    }
}
