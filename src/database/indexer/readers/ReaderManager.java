package database.indexer.readers;

import database.indexer.Manager;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class ReaderManager implements Manager {
    private IndexReader indexReader;
    private IndexSearcher indexSearcher;

    public ReaderManager(Directory directory) {
        assert directory != null : "AssertionError: Injected null Directory to IndexReader class";

        try {
            indexReader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(indexReader);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IndexReader getIndexReader() {
        return indexReader;
    }

    public IndexSearcher getIndexSearcher() {
        return indexSearcher;
    }

    @Override
    public void close() {
        try {
            indexReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
