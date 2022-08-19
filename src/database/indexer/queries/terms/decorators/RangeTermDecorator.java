package database.indexer.queries.terms.decorators;

public class RangeTermDecorator implements TermDecorator {

    @Override
    public String decorateText(final String text) {
        final String[] ranges = text.split(",");

        assert ranges.length == 2 : "AssertionError: Interval [a,b] should be given as a,b, but more dimensions were found";

        return "[" + ranges[0] + " TO " + ranges[1] + "]";
    }
}
