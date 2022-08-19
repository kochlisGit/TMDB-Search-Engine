package database.indexer.documents.builders;

import org.apache.lucene.document.Document;

public interface DocumentBuilder<T> {

    Document toDocument(final T entity);
}
