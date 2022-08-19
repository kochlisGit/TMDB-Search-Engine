package database.indexer.services.entities;

import database.entities.media.Movie;
import database.indexer.documents.builders.entities.MovieDocumentBuilder;
import database.indexer.documents.mappers.MovieDocumentMapper;
import database.indexer.indexes.entities.MovieIndex;
import database.indexer.readers.DocumentReader;
import database.indexer.writers.DocumentWriter;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;

public class MovieService extends BaseEntityService<Movie> {
    private final int hitsPerPage;

    public MovieService(int hitsPerPage) {
        super(new MovieIndex());
        this.hitsPerPage = hitsPerPage;
    }

    @Override
    protected DocumentWriter<Movie> getDocumentWriter(final IndexWriter indexWriter) {
        return new DocumentWriter<>(indexWriter, new MovieDocumentBuilder());
    }

    @Override
    protected DocumentReader<Movie> getDocumentReader(final IndexSearcher indexSearcher) {
        return new DocumentReader<>(indexSearcher, new MovieDocumentMapper(), hitsPerPage);
    }
}
