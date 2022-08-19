package database.indexer.queries.terms.preprocessors;

import database.indexer.queries.terms.decorators.TermDecorator;
import database.indexer.queries.terms.decorators.TermDecoratorFactory;

public class QueryPreprocessor implements TermPreprocessor {

    @Override
    public String preprocessText(final String text) {
        return new WhitespacePreprocessor().preprocessText(text);
    }

    public String preprocessText(final String text, final String decoratorMethod) {
        final String preprocessedText = preprocessText(text);
        final TermDecorator termDecorator = TermDecoratorFactory.getTermDecorator(decoratorMethod);

        if (termDecorator != null) {
            return termDecorator.decorateText(preprocessedText);
        }
        return preprocessedText;
    }
}
