package indexes.readers;

import indexes.queries.QueryBuilder;
import indexes.writers.ActorWriter;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Query;

import java.util.Arrays;

public class ActorRetriever extends DocumentRetriever
{
    public ActorRetriever(EntitySearcher searcher) {
        super(searcher);
    }

    @Override
    protected Query buildQuery(String searchKeyword) {
        Query nameFuzzyQuery = QueryBuilder.newBuilder().buildFuzzy(ActorWriter.NAME, searchKeyword);
        Query namePhraseQuery = QueryBuilder.newBuilder().buildPhrase(ActorWriter.NAME, searchKeyword);
        Query characterFuzzyQuery = QueryBuilder.newBuilder().buildFuzzy(ActorWriter.CHARACTER, searchKeyword);
        Query characterPhraseQuery = QueryBuilder.newBuilder().buildPhrase(ActorWriter.CHARACTER, searchKeyword);
        return QueryBuilder.newBuilder().booleanQuery(
                Arrays.asList(nameFuzzyQuery, namePhraseQuery, characterFuzzyQuery, characterPhraseQuery),
                BooleanClause.Occur.SHOULD);
    }
}