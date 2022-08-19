package database.indexer.indexes.entities;

import database.indexer.indexes.IndexManager;

public class ReviewIndex extends IndexManager {
    private static final String INDEX_NAME = "reviews";

    public ReviewIndex() {
        super(INDEX_NAME);
    }
}
