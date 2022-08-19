package database.indexer.services;

import database.indexer.indexes.IndexManager;
import database.indexer.readers.ReaderManager;
import database.indexer.writers.WriterManager;

import java.io.IOException;

public abstract class Service {
    protected final IndexManager indexManager;

    protected Service(IndexManager indexManager) {
        this.indexManager = indexManager;

        final WriterManager writerManager = new WriterManager(indexManager.getDirectory());
        try {
            writerManager.getIndexWriter().commit();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        writerManager.close();
    }

    public int getNumDocs() {
        final ReaderManager readerManager = new ReaderManager(indexManager.getDirectory());
        final int numDocs = readerManager.getIndexReader().numDocs();
        readerManager.close();
        return numDocs;
    }

    public void deleteAll() {
        final WriterManager writerManager = new WriterManager(indexManager.getDirectory());
        try {
            writerManager.getIndexWriter().deleteAll();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        writerManager.close();
    }

    public void close() {
        indexManager.close();
    }
}
