package indexes.writers;

import indexes.indexes.Index;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

import java.io.IOException;
import java.util.List;

public abstract class EntityWriter<T>
{
    public EntityWriter() {

    }

    private IndexWriter getWriter(Index index) {
        IndexWriterConfig config = new IndexWriterConfig( index.getAnalyzer() );
        try {
            return new IndexWriter(index.getDirectory(), config);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected abstract void writeEntitiesToIndex(IndexWriter writer, List<T> entityList);

    public void indexEntities(Index index, List<T> entityList) {
        IndexWriter writer = getWriter(index);

        if (writer != null)
        {
            writeEntitiesToIndex(writer, entityList);
            try {
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
