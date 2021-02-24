package indexes.readers;

import indexes.indexes.Index;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySearcher implements Cloneable
{
    private IndexSearcher searcher;

    public EntitySearcher(IndexSearcher searcher) {
        this.searcher = searcher;
    }
    public EntitySearcher(Index index) {
        try {
            IndexReader reader = DirectoryReader.open( index.getDirectory() );
            searcher = new IndexSearcher(reader);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Document> searchEntities(Query query, Sort sort, int nHits)
    {
        try {
            TopDocs topDocs = searcher.search(query, nHits, sort);
            return Arrays.stream(topDocs.scoreDocs)
                    .map(scoreDoc -> {
                        try {
                            return searcher.doc(scoreDoc.doc);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object Clone() {
        return new EntitySearcher(searcher);
    }
}
