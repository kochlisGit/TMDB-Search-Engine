package indexes.readers;

import indexes.indexes.IndexManager;
import indexes.queries.SortFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;

import java.util.List;

public abstract class DocumentRetriever
{
    private final EntitySearcher searcher;

    public DocumentRetriever(EntitySearcher searcher) {
        this.searcher = searcher;
    }

    protected abstract Query buildQuery(String searchKeyword);

    protected List<Document> retrieveDocs(String searchKeyword, String sortingField, int nHits) {
        Query query = buildQuery(searchKeyword);
        Sort sort = SortFactory.getSort(sortingField);
        return searcher.searchEntities(query, sort, nHits);
    }

    public static void main(String[] Args)
    {
        IndexManager manager = new IndexManager();
        EntitySearcher searcher = new EntitySearcher( manager.getMoviesIndex() );

        DocumentRetriever popRetriever = new GenreRetriever(searcher);

        String searchKeyword = "Comedy";
        String sortingField = "rating";
        for (Document doc : popRetriever.retrieveDocs(searchKeyword, sortingField, 30))
            System.out.println(doc);
    }
}
