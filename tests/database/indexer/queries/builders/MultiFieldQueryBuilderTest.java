package database.indexer.queries.builders;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiFieldQueryBuilderTest {
    private static final String FIELD_1 = "title";
    private static final String TEXT_1 = "The lord";
    private static final String CLAUSE_1 = "MUST";
    private static final String FIELD_2 = "title";
    private static final String TEXT_2 = "two towers";
    private static final String CLAUSE_2 = "MUST NOT";
    private static final String FIELD_3 = "overview";
    private static final String TEXT_3 = "middle earth";
    private static final String CLAUSE_3 = "SHOULD";

    @Test
    void build() {
        final List<String> fieldList = List.of(FIELD_1, FIELD_2, FIELD_3);
        final List<String> textList = List.of(TEXT_1, TEXT_2, TEXT_3);
        final List<String> clauseList = List.of(CLAUSE_1, CLAUSE_2, CLAUSE_3);

        final String expectedQueryStr = "+(title:the title:lord) -(title:two title:towers) (overview:middle overview:earth)";
        final String actualQueryStr = MultiFieldQueryBuilder.newBuilder().addAll(fieldList, textList, clauseList).build().toString();
        assertEquals(expectedQueryStr, actualQueryStr);
    }
}
