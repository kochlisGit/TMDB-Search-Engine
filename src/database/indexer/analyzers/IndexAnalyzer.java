package database.indexer.analyzers;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public enum IndexAnalyzer {
    ANALYZER;

    public final Analyzer analyzer;

    IndexAnalyzer() {
        analyzer = new StandardAnalyzer();
    }
}
