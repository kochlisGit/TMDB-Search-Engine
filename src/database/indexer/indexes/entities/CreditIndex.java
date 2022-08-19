package database.indexer.indexes.entities;

import database.indexer.indexes.IndexManager;

public class CreditIndex extends IndexManager {
    private static final String INDEX_NAME = "credits";

    public CreditIndex() {
        super(INDEX_NAME);
    }
}
