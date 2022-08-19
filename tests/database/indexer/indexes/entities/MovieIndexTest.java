package database.indexer.indexes.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieIndexTest {

    @Test
    void open() {
        assertDoesNotThrow(() -> {new MovieIndex();});
    }

    @Test
    void getDirectory() {
        assertNotNull(new MovieIndex().getDirectory());
    }

    @Test
    void close() {
        assertDoesNotThrow(() -> new MovieIndex().close());
    }
}
