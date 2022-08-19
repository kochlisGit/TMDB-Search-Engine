package database.indexer.indexes;

import database.indexer.Manager;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public abstract class IndexManager implements Manager {
    private static final String DIRECTORY_PATH = "index";

    private Directory directory;

    public IndexManager(String indexName) {
        try {
            directory = MMapDirectory.open(Paths.get(DIRECTORY_PATH, indexName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Directory getDirectory() {
        return directory;
    }

    public void close() {
        if (directory != null) {
            try {
                directory.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
