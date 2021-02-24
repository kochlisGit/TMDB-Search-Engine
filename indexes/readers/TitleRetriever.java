package indexes.readers;

import indexes.queries.QueryBuilder;
import indexes.writers.MovieWriter;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Query;

import java.util.Arrays;

public class TitleRetriever extends DocumentRetriever
{
    public TitleRetriever(EntitySearcher searcher) {
        super(searcher);
    }

    @Override
    protected Query buildQuery(String searchKeyword) {
        Query prefixQuery = QueryBuilder.newBuilder().buildPrefix(MovieWriter.TITLE, searchKeyword);
        Query phraseQuery = QueryBuilder.newBuilder().buildPhrase(MovieWriter.TITLE, searchKeyword);
        return QueryBuilder.newBuilder().booleanQuery(Arrays.asList(prefixQuery, phraseQuery), BooleanClause.Occur.SHOULD);
    }
}

