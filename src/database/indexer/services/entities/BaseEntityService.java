package database.indexer.services.entities;


import database.indexer.indexes.IndexManager;
import database.indexer.readers.DocumentReader;
import database.indexer.readers.ReaderManager;
import database.indexer.services.Service;
import database.indexer.writers.DocumentWriter;
import database.indexer.writers.WriterManager;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;

import java.util.List;

public abstract class BaseEntityService<T> extends Service implements EntityService<T> {

    public BaseEntityService(IndexManager indexManager) {
        super(indexManager);
    }

    protected abstract DocumentWriter<T> getDocumentWriter(final IndexWriter indexWriter);

    protected abstract DocumentReader<T> getDocumentReader(final IndexSearcher indexSearcher);

    @Override
    public int writeEntities(final List<T> entityList) {
        final WriterManager writerManager = new WriterManager(indexManager.getDirectory());
        final int numDocs = getDocumentWriter(writerManager.getIndexWriter()).writeDocuments(entityList);
        writerManager.close();
        return numDocs;
    }

    @Override
    public List<T> readEntities(final Query query, final Sort sort) {
        final ReaderManager readerManager = new ReaderManager(indexManager.getDirectory());
        final List<T> entityList = getDocumentReader(readerManager.getIndexSearcher()).readDocuments(query, sort);
        readerManager.close();
        return entityList;
    }
}
