package database.indexer.queries.builders;

import database.indexer.analyzers.IndexAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;

public class SingleFieldQueryBuilder implements QueryBuilder {
    private String field;
    private String text;

    public SingleFieldQueryBuilder() {
        field = null;
        text = null;
    }

    public static SingleFieldQueryBuilder newBuilder() {
        return new SingleFieldQueryBuilder();
    }

    public SingleFieldQueryBuilder setField(final String field) {
        this.field = field;
        return this;
    }

    public SingleFieldQueryBuilder setText(final String text) {
        this.text = text;
        return this;
    }

    @Override
    public Query build() {
        assert text != null : "AssertionError: Cannot build query, because 'text' is null";
        assert field != null : "AssertionError: Cannot build query, because 'field' is null";

        try {
            return new QueryParser(field, IndexAnalyzer.ANALYZER.analyzer).parse(text);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
