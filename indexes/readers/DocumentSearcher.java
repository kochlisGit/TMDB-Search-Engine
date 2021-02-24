package indexes.readers;

import indexes.queries.QueryBuilder;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentSearcher
{
    private final EntitySearcher searcher;

    public DocumentSearcher(EntitySearcher searcher) {
        this.searcher = searcher;
    }

    public List<Document> searchDocs(String searchField, String keywordField, int nHits) {
        String[] keywordsArray = keywordField.split(" ");
        List<Query> queryList = Arrays.stream(keywordsArray)
                .map( keyword -> QueryBuilder.newBuilder().buildFuzzy(searchField, keyword) )
                .collect( Collectors.toList() );
        Query multiFuzzyQuery = QueryBuilder.newBuilder().booleanQuery(queryList, BooleanClause.Occur.MUST);
        Sort relevantSort = new Sort(SortField.FIELD_SCORE);
        return searcher.searchEntities(multiFuzzyQuery, relevantSort, nHits);
    }
}
