package database.indexer.queries.terms.decorators;

public class FuzzyTermDecorator implements TermDecorator {

    @Override
    public String decorateText(final String text) {
        return text.replace(" ", "~2 ") + "~2";
    }
}
