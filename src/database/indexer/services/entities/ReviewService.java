package database.indexer.services.entities;

import database.entities.media.details.Review;
import database.indexer.documents.builders.entities.ReviewDocumentBuilder;
import database.indexer.documents.mappers.ReviewDocumentMapper;
import database.indexer.indexes.entities.ReviewIndex;
import database.indexer.readers.DocumentReader;
import database.indexer.writers.DocumentWriter;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;

public class ReviewService extends BaseEntityService<Review> {
    private final int hitsPerPage;

    public ReviewService(int hitsPerPage) {
        super(new ReviewIndex());
        this.hitsPerPage = hitsPerPage;
    }

    @Override
    protected DocumentWriter<Review> getDocumentWriter(final IndexWriter indexWriter) {
        return new DocumentWriter<>(indexWriter, new ReviewDocumentBuilder());
    }

    @Override
    protected DocumentReader<Review> getDocumentReader(final IndexSearcher indexSearcher) {
        return new DocumentReader<>(indexSearcher, new ReviewDocumentMapper(), hitsPerPage);
    }
}
