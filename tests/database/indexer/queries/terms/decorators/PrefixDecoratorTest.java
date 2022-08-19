package database.indexer.queries.terms.decorators;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrefixDecoratorTest {
    private static final String TEXT = "Th Lor of the ring";

    private final TermDecorator termDecorator;

    public PrefixDecoratorTest() {
        termDecorator = new PrefixDecorator();
    }

    @Test
    void decorateText() {
        final String expectedText = "Th* Lor* of* the* ring*";
        final String actualText = termDecorator.decorateText(TEXT);
        assertEquals(expectedText, actualText);
    }

    @Test
    void decorateQuery() {
        final String expectedQueryText = "title:th* title:lor* title:of* title:the* title:ring*";
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