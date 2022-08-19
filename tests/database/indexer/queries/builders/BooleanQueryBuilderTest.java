package database.indexer.queries.builders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BooleanQueryBuilderTest {
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
        final String expectedQueryStr = "+(title:the title:lord) -(title:two title:towers) (overview:middle overview:earth)";
        final String actualQueryStr = BooleanQueryBuilder.newBuilder()
                .add(FIELD_1, TEXT_1, CLAUSE_1)
                .add(FIELD_2, TEXT_2, CLAUSE_2)
                .add(FIELD_3, TEXT_3, CLAUSE_3)
                .build()
                .toString();
        assertEquals(expectedQueryStr, actualQueryStr);
    }
}
