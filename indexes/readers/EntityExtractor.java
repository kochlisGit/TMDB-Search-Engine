package indexes.readers;

import org.apache.lucene.document.Document;

import java.util.List;

public interface EntityExtractor<T> {
    List<T> extractEntities(List<Document> docList);
}
