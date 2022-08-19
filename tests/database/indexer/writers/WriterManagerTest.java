package database.indexer.writers;

import database.indexer.indexes.entities.MovieIndex;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class WriterManagerTest {
    private static MovieIndex movieIndex;
    private WriterManager writerManager;

    @BeforeAll
    static void initAll() {
        movieIndex = new MovieIndex();
    }

    @AfterAll
    static void cleanupAll() {
        movieIndex.close();
    }

    @AfterEach
    void cleanup() {
        if (writerManager != null && writerManager.getIndexWriter().isOpen()) {
            writerManager.close();
        }
    }

    @Test
    public void open() {
        assertDoesNotThrow(() -> writerManager = new WriterManager(movieIndex.getDirectory()));
        assertTrue(writerManager.getIndexWriter().isOpen());
    }

    @Test
    public void getIndexWriter() {
        writerManager = new WriterManager(movieIndex.getDirectory());
        assertNotNull(writerManager.getIndexWriter());
    }

    @Test
    public void close() {
        writerManager = new WriterManager(movieIndex.getDirectory());
        assertDoesNotThrow(() -> writerManager.close());
        assertFalse(writerManager.getIndexWriter().isOpen());
    }
}
