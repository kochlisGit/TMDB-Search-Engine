package indexes.readers;

import indexes.queries.QueryBuilder;
import indexes.writers.MovieWriter;
import org.apache.lucene.search.Query;

public class IdRetriever extends DocumentRetriever
{
    public IdRetriever(EntitySearcher searcher) {
        super(searcher);
    }

    @Override
    protected Query buildQuery(String searchKeyword) {
        return QueryBuilder.newBuilder().buildTerm(MovieWriter.MID, searchKeyword);
    }
}
