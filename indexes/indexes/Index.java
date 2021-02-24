package indexes.indexes;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public abstract class Index
{
    private final String directoryPath;
    private Directory indexDir;

    public Index(String directoryPath) {
        this.directoryPath = directoryPath;

        try {
            indexDir = MMapDirectory.open( Paths.get(directoryPath) );
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public Directory getDirectory() {
        return indexDir;
    }
    public abstract Analyzer getAnalyzer();

    public void close() {
        try {
            indexDir.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
