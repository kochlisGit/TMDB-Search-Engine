package database.indexer.queries.terms.preprocessors;

import database.indexer.queries.terms.decorators.TermDecoratorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryPreprocessorTest {

    @Test
    void PreprocessQuery() {
        final String queryText = " the big boss ";
        final String decoratorMethod = TermDecoratorFactory.PHRASE;
        final String expectedText = "\"the big boss\"";
        final String actualText = new QueryPreprocessor().preprocessText(queryText, decoratorMethod);
        assertEquals(expectedText, actualText);
    }
}
