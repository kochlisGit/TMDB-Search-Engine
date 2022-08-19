package database.indexer.indexes.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditIndexTest {

    @Test
    void open() {
        assertDoesNotThrow(() -> {new CreditIndex();});
    }

    @Test
    void getDirectory() {
        assertNotNull(new CreditIndex().getDirectory());
    }

    @Test
    void close() {
        assertDoesNotThrow(() -> new CreditIndex().close());
    }
}
