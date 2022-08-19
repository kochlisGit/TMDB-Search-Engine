package database.indexer.indexes.entities;

import database.indexer.indexes.IndexManager;

public class MovieIndex extends IndexManager {
    private static final String INDEX_NAME = "movies";

    public MovieIndex() {
        super(INDEX_NAME);
    }
}
