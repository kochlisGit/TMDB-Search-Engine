package database.indexer.queries.terms.decorators;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTermDecoratorTest {
    private static final String TEXT = "5.5,7.0";

    private final TermDecorator termDecorator;

    public RangeTermDecoratorTest() {
        termDecorator = new RangeTermDecorator();
    }

    @Test
    void decorateText() {
        final String expectedText = "[5.5 TO 7.0]";
        final String actualText = termDecorator.decorateText(TEXT);
        assertEquals(expectedText, actualText);
    }

    @Test
    void decorateQuery() {
        final String expectedQueryText = "title:[5.5 TO 7.0]";
        final String decoratedText = termDecorator.decorateText(TEXT);

        String actualQueryText = null;
        try {
            actualQueryText = new QueryParser("title", new StandardAnalyzer()).parse(decoratedText).toString();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        assertNotNull(actualQueryText);
        assertEquals(expectedQueryText, actualQueryText);
    }
}
