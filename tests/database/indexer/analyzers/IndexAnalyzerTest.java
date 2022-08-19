package database.indexer.analyzers;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexAnalyzerTest {

    @Test
    void getAnalyzer() {
        assertEquals(StandardAnalyzer.class, IndexAnalyzer.ANALYZER.analyzer.getClass());
    }
}
