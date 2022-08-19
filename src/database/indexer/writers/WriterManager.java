package database.indexer.writers;

import database.indexer.Manager;
import database.indexer.analyzers.IndexAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class WriterManager implements Manager {
    private IndexWriter indexWriter;

    public WriterManager(Directory directory) {
        assert directory != null : "AssertionError: Injected null Directory to IndexWriter class";

        try {
            indexWriter = new IndexWriter(directory, new IndexWriterConfig(IndexAnalyzer.ANALYZER.analyzer));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IndexWriter getIndexWriter() {
        return indexWriter;
    }

    @Override
    public void close() {
        try {
            indexWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
