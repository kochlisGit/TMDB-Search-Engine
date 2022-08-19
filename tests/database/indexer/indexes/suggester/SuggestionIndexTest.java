package database.indexer.indexes.suggester;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuggestionIndexTest {

    @Test
    void open() {
        assertDoesNotThrow(() -> {new SuggestionIndex();});
    }

    @Test
    void getDirectory() {
        assertNotNull(new SuggestionIndex().getDirectory());
    }

    @Test
    void close() {
        assertDoesNotThrow(() -> new SuggestionIndex().close());
    }
}
