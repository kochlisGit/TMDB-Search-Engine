package database.indexer.services.entities;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;

import java.util.List;

public interface EntityService<T> {

    int writeEntities(final List<T> entityList);

    List<T> readEntities(final Query query, final Sort sort);

    void deleteAll();

    void close();
}
