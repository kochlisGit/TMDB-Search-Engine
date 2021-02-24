package indexes.queries;

import indexes.writers.MovieWriter;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

public class SortFactory
{
    public static Sort getSort(String sortingField)
    {
        switch (sortingField)
        {
            case MovieWriter.TITLE:
                return new Sort( new SortField(MovieWriter.TITLE, SortField.Type.STRING, false) );
            case MovieWriter.RELEASE_DATE:
                return new Sort ( new SortField(MovieWriter.RELEASE_DATE, SortField.Type.STRING, true) );
            case MovieWriter.POPULARITY:
                return new Sort ( new SortField(MovieWriter.POPULARITY, SortField.Type.DOUBLE, true) );
            case MovieWriter.RATING:
                return new Sort ( new SortField(MovieWriter.RATING, SortField.Type.DOUBLE, true) );
            default:
                return new Sort (SortField.FIELD_SCORE);
        }
    }
}
