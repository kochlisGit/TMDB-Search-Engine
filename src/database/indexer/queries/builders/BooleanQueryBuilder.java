package database.indexer.queries.builders;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;

public class BooleanQueryBuilder implements QueryBuilder {
    private final BooleanQuery.Builder booleanQueryBuilder;

    public BooleanQueryBuilder() {
        booleanQueryBuilder = new BooleanQuery.Builder();
    }

    private BooleanClause.Occur getBooleanClause(final String clause) {
        switch (clause) {
            case "MUST":
                return BooleanClause.Occur.MUST;
            case "MUST NOT":
                return BooleanClause.Occur.MUST_NOT;
            case "SHOULD":
                return BooleanClause.Occur.SHOULD;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static BooleanQueryBuilder newBuilder() {
        return new BooleanQueryBuilder();
    }

    public BooleanQueryBuilder add(final String field, final String text, final String clause) {
        final Query query = SingleFieldQueryBuilder.newBuilder().setField(field).setText(text).build();
        booleanQueryBuilder.add(query, getBooleanClause(clause));
        return this;
    }

    @Override
    public Query build() {
        return booleanQueryBuilder.build();
    }
}
