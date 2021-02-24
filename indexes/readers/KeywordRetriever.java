package indexes.readers;

import indexes.queries.QueryBuilder;
import indexes.writers.MovieWriter;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Query;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KeywordRetriever extends DocumentRetriever
{
    public KeywordRetriever(EntitySearcher searcher) {
        super(searcher);
    }

    @Override
    protected Query buildQuery(String searchKeyword) {
        String[] keywordsArray = searchKeyword.split(",");
        List<Query> queryList = Arrays.stream(keywordsArray)
                .map( keyword -> QueryBuilder.newBuilder().buildPhrase(MovieWriter.OVERVIEW, keyword) )
                .collect( Collectors.toList() );
        return QueryBuilder.newBuilder().booleanQuery(queryList, BooleanClause.Occur.MUST);
    }
}
