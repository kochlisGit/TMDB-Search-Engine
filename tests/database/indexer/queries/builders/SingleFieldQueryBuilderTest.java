package database.indexer.queries.builders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleFieldQueryBuilderTest {

    @Test
    void build() {
        final String field = "title";
        final String text = "The LORD of, the Rings";

        final String expectedQueryStr = "title:the title:lord title:of title:the title:rings";
        final String actualQueryStr = SingleFieldQueryBuilder.newBuilder().setField(field).setText(text).build().toString();
        assertEquals(actualQueryStr, expectedQueryStr);
    }
}
