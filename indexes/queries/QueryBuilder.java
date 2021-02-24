package indexes.queries;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

import java.util.List;

public class QueryBuilder
{
    public QueryBuilder() {

    }

    public static QueryBuilder newBuilder() {
        return new QueryBuilder();
    }

    public Query buildTerm(String searchField, String searchKeyword) {
        Term term = new Term(searchField, searchKeyword);
        return new TermQuery(term);
    }

    public Query buildFuzzy(String searchField, String searchKeyword) {
        Term term = new Term(searchField, searchKeyword);
        return new FuzzyQuery(term);
    }

    public Query buildPrefix(String searchField, String searchKeyword) {
        Term term = new Term(searchField, searchKeyword);
        return new PrefixQuery(term);
    }

    public Query buildPhrase(String searchField, String searchKeyword) {
        PhraseQuery.Builder builder = new PhraseQuery.Builder();
        String[] keywordsArray = searchKeyword.split(" ");
        int nKeywords = keywordsArray.length;

        for(int i = 0; i < nKeywords; i++)
            builder.add( new Term(searchField, keywordsArray[i] ), i);

        return builder.build();
    }

    public Query booleanQuery(List<Query> queryList, BooleanClause.Occur clause) {
        BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();

        queryList.forEach( e -> queryBuilder.add(e, clause) );

        return queryBuilder.build();
    }
}
