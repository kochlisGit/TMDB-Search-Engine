package database.indexer.queries.terms.decorators;

public class TermDecoratorFactory {
    public static final String FUZZY = "fuzzy";
    public static final String RANGE = "range";
    public static final String PREFIX = "prefix";
    public static final String PHRASE = "phrase";
    public static final String SIMPLE = "simple";

    public static TermDecorator getTermDecorator(final String decoratorMethod) {
        switch (decoratorMethod) {
            case FUZZY:
                return new FuzzyTermDecorator();
            case RANGE:
                return new RangeTermDecorator();
            case PREFIX:
                return new PrefixDecorator();
            case PHRASE:
                return new PhraseTermDecorator();
            case SIMPLE:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
