package database.indexer.indexes.suggester;

import database.indexer.indexes.IndexManager;

public class SuggestionIndex extends IndexManager {
    private static final String INDEX_NAME = "suggestions";

    public SuggestionIndex() {
        super(INDEX_NAME);
    }
}
