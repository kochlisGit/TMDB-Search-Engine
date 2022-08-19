package database.indexer.queries.terms;

import database.indexer.queries.terms.preprocessors.WhitespacePreprocessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhitespacePreprocessorTest {

    @Test
    public void preprocessText() {
        final String text = " jack sparrow  ";
        final String expectedText = "jack sparrow";
        final String actualText = new WhitespacePreprocessor().preprocessText(text);
        assertEquals(expectedText, actualText);
    }
}
