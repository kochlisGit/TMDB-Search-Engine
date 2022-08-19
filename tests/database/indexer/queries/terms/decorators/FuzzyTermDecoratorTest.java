package database.indexer.queries.terms.decorators;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuzzyTermDecoratorTest {
    private static final String TEXT = "Lore OF th ring";

    private final TermDecorator termDecorator;

    public FuzzyTermDecoratorTest() {
        termDecorator = new FuzzyTermDecorator();
    }

    @Test
    void decorateText() {
        final String expectedText = "Lore~2 OF~2 th~2 ring~2";
        final String actualText = termDecorator.decorateText(TEXT);
        assertEquals(expectedText, actualText);
    }

    @Test
    void decorateQuery() {
        final String expectedQueryText = "title:lore~2 title:of~2 title:th~2 title:ring~2";
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
