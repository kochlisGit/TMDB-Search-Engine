package database.indexer.queries.terms.decorators;

public class PhraseTermDecorator implements TermDecorator {

    @Override
    public String decorateText(final String text) {
        return "\"" + text + "\"";
    }
}
