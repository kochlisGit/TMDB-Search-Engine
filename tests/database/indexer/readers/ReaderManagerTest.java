package database.indexer.readers;

import database.indexer.indexes.IndexManager;
import database.indexer.indexes.entities.MovieIndex;
import database.indexer.writers.WriterManager;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReaderManagerTest {
    private static final IndexManager index = new MovieIndex();
    private ReaderManager readerManager;

    @BeforeAll
    static void initAll() {
        final WriterManager writerManager = new WriterManager(index.getDirectory());

        try {
            writerManager.getIndexWriter().commit();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        writerManager.close();
        index.close();
    }

    @AfterAll
    static void cleanupAll() {
        final WriterManager writerManager = new WriterManager(index.getDirectory());

        try {
            writerManager.getIndexWriter().deleteAll();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        writerManager.close();
    }

    @AfterEach
    void cleanup() {
        if (readerManager != null && readerManager.getIndexReader() != null) {
            readerManager.close();
        }
    }

    @Test
    void open() {
        assertDoesNotThrow(() -> readerManager = new ReaderManager(index.getDirectory()));
        assertDoesNotThrow(() -> readerManager.getIndexReader());
    }

    @Test
    void getIndexReader() {
        readerManager = new ReaderManager(index.getDirectory());
        assertNotNull(readerManager.getIndexReader());
    }

    @Test
    void getIndexSearcher() {
        readerManager = new ReaderManager(index.getDirectory());
        assertNotNull(readerManager.getIndexSearcher());
    }

    @Test
    void close() {
        readerManager = new ReaderManager(index.getDirectory());
        assertDoesNotThrow(() -> readerManager.close());
    }
}
