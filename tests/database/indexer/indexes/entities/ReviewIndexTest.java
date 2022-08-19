package database.indexer.indexes.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewIndexTest {

    @Test
    void open() {
        assertDoesNotThrow(() -> {new ReviewIndex();});
    }

    @Test
    void getDirectory() {
        assertNotNull(new ReviewIndex().getDirectory());
    }

    @Test
    void close() {
        assertDoesNotThrow(() -> new ReviewIndex().close());
    }
}
