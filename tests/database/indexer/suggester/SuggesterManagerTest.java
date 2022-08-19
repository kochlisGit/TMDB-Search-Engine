package database.indexer.suggester;

import database.indexer.indexes.IndexManager;
import database.indexer.indexes.suggester.SuggestionIndex;
import database.indexer.readers.ReaderManager;
import database.indexer.writers.WriterManager;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SuggesterManagerTest {
    private IndexManager index;
    private ReaderManager reader;
    private SuggesterManager suggester;

    @BeforeAll
    static void initAll() {
        final IndexManager index = new SuggestionIndex();
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
        final IndexManager index = new SuggestionIndex();
        final WriterManager writerManager = new WriterManager(index.getDirectory());

        try {
            writerManager.getIndexWriter().deleteAll();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        writerManager.close();
        index.close();
    }

    @BeforeEach
    void init() {
        index = new SuggestionIndex();
        reader = new ReaderManager(index.getDirectory());
    }

    @AfterEach
    void afterEach() {
        reader.close();
        index.close();
        suggester.close();
    }

    @Test
    void open() {
        assertDoesNotThrow(() -> suggester = new SuggesterManager(index.getDirectory(), reader.getIndexReader()));
        assertDoesNotThrow(() -> suggester.getIndexSuggester());
    }

    @Test
    void getIndexReader() {
        suggester = new SuggesterManager(index.getDirectory(), reader.getIndexReader());
        assertNotNull(suggester.getIndexSuggester());
    }

    @Test
    void close() {
        suggester = new SuggesterManager(index.getDirectory(), reader.getIndexReader());
        assertDoesNotThrow(() -> suggester.close());
    }
}
