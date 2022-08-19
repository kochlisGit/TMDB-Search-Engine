package database.indexer.queries.terms.decorators;

public class PrefixDecorator implements TermDecorator {

    @Override
    public String decorateText(final String text) {
        return text.replace(" ", "* ") + "*";
    }
}
