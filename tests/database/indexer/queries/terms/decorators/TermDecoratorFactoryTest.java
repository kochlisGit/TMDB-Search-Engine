package database.indexer.queries.terms.decorators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermDecoratorFactoryTest {

    @Test
    void getFuzzy() {
        assertEquals(FuzzyTermDecorator.class, TermDecoratorFactory.getTermDecorator(TermDecoratorFactory.FUZZY).getClass());
    }

    @Test
    void getPhrase() {
        assertEquals(PhraseTermDecorator.class, TermDecoratorFactory.getTermDecorator(TermDecoratorFactory.PHRASE).getClass());
    }

    @Test
    void getRange() {
        assertEquals(RangeTermDecorator.class, TermDecoratorFactory.getTermDecorator(TermDecoratorFactory.RANGE).getClass());
    }

    @Test
    void getPrefix() {
        assertEquals(PrefixDecorator.class, TermDecoratorFactory.getTermDecorator(TermDecoratorFactory.PREFIX).getClass());
    }

    @Test
    void getSimple() {
        assertNull(TermDecoratorFactory.getTermDecorator(TermDecoratorFactory.SIMPLE));
    }

    @Test
    void getUnsupported() {
        assertThrows(UnsupportedOperationException.class, () -> TermDecoratorFactory.getTermDecorator("other"));
    }
}
