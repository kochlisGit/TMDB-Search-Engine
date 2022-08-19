package database.indexer.services.entities;

import database.entities.media.details.Credit;
import database.indexer.documents.builders.entities.CreditDocumentBuilder;
import database.indexer.documents.mappers.CreditDocumentMapper;
import database.indexer.indexes.entities.CreditIndex;
import database.indexer.readers.DocumentReader;
import database.indexer.writers.DocumentWriter;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;

public class CreditService extends BaseEntityService<Credit> {
    private final int hitsPerPage;

    public CreditService(int hitsPerPage) {
        super(new CreditIndex());
        this.hitsPerPage = hitsPerPage;
    }

    @Override
    protected DocumentWriter<Credit> getDocumentWriter(final IndexWriter indexWriter) {
        return new DocumentWriter<>(indexWriter, new CreditDocumentBuilder());
    }

    @Override
    protected DocumentReader<Credit> getDocumentReader(final IndexSearcher indexSearcher) {
        return new DocumentReader<>(indexSearcher, new CreditDocumentMapper(), hitsPerPage);
    }
}
