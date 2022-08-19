package database.indexer.queries.builders;

import org.apache.lucene.search.Query;

public interface QueryBuilder {

    Query build();
}
