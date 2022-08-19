package database.indexer.documents.mappers;

import org.apache.lucene.document.Document;

public interface DocumentMapper<T> {

    T toEntity(final Document document);
}
