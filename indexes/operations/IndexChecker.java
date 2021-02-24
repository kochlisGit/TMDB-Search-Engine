package indexes.operations;

import indexes.indexes.Index;
import org.apache.lucene.index.DirectoryReader;

import java.io.IOException;

public class IndexChecker implements IndexOperation
{
    @Override
    public boolean executeOperation(Index index) {
        try {
            return DirectoryReader.indexExists( index.getDirectory() );
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
