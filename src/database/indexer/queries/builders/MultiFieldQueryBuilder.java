package database.indexer.queries.builders;

import org.apache.lucene.search.Query;

import java.util.List;

public class MultiFieldQueryBuilder implements QueryBuilder {
    private BooleanQueryBuilder booleanQueryBuilder;

    public static MultiFieldQueryBuilder newBuilder() {
        return new MultiFieldQueryBuilder();
    }

    public MultiFieldQueryBuilder addAll(final List<String> fieldList, final List<String> textList, final List<String> clauseList) {
        assert fieldList.size() == textList.size() && fieldList.size() == clauseList.size() :
                "Assertion Error: Size mismatch between fields, texts and clauses";

        booleanQueryBuilder = BooleanQueryBuilder.newBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            booleanQueryBuilder.add(fieldList.get(i), textList.get(i), clauseList.get(i));
        }
        return this;
    }

    @Override
    public Query build() {
        return booleanQueryBuilder.build();
    }
}
