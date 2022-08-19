package database.indexer.services.entities;

import database.entities.media.details.Credit;
import database.indexer.analyzers.IndexAnalyzer;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditServiceTest {
    private static final int hitsPerPage = 10;

    private final CreditService creditService = new CreditService(hitsPerPage);

    @Test
    @Order(1)
    void writeMovies() {
        final List<Credit> creditList = List.of(
                new Credit(2, "actor1", "char1", 100),
                new Credit(3, "actor2", "char2", 200),
                new Credit(2, "dir", "Director", 1000)
        );
        final int numDocs = creditService.writeEntities(creditList);
        assertEquals(creditList.size(), numDocs);
        assertEquals(creditList.size(), creditService.getNumDocs());
    }

    @Test
    @Order(2)
    void readMovies() {
        List<Credit> creditList;
        try {
            final Query query = new QueryParser(DocumentFields.TITLE, IndexAnalyzer.ANALYZER.analyzer).parse("blow");
            final Sort sort = new Sort(SortField.FIELD_SCORE);
            creditList = creditService.readEntities(query, sort);
        }
        catch (ParseException e) {
            e.printStackTrace();
            creditList = null;
        }
        assertNotNull(creditList);
    }

    @Test
    @Order(3)
    void deleteAll() {
        final List<Credit> creditList = List.of(
                new Credit(2, "actor1", "char1", 100),
                new Credit(3, "actor2", "char2", 200),
                new Credit(2, "dir", "Director", 1000)
        );
        creditService.writeEntities(creditList);
        creditService.deleteAll();

        List<Credit> resultsList;
        try {
            final Query query = new QueryParser(DocumentFields.TITLE, IndexAnalyzer.ANALYZER.analyzer).parse("bloody");
            final Sort sort = new Sort(SortField.FIELD_SCORE);
            resultsList = creditService.readEntities(query, sort);
        }
        catch (ParseException e) {
            e.printStackTrace();
            resultsList = null;
        }
        assertNotNull(resultsList);
        assertEquals(0, resultsList.size());
    }

    @Test
    @Order(5)
    void close() {
        assertDoesNotThrow(creditService::close);
    }
}
