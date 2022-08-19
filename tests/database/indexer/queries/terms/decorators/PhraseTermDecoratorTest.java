package database.indexer.queries.terms.decorators;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhraseTermDecoratorTest {
    private static final String TEXT = "the rings";

    private final TermDecorator termDecorator;

    public PhraseTermDecoratorTest() {
        termDecorator = new PhraseTermDecorator();
    }

    @Test
    void decorateText() {
        final String expectedText = "\"the rings\"";
        final String actualText = termDecorator.decorateText(TEXT);
        assertEquals(expectedText, actualText);
    }

    @Test
    void decorateQuery() {
        final String expectedQueryText = "title:\"the rings\"";
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
