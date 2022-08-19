package database.indexer.queries.terms.preprocessors;

public class WhitespacePreprocessor implements TermPreprocessor {

    public String preprocessText(final String text) {
        return text.trim();
    }
}
