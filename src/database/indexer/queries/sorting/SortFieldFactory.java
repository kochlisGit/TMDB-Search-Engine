package database.indexer.queries.sorting;

import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

public class SortFieldFactory {

    public static Sort getSort(final String fieldName) {
        switch (fieldName) {
            case DocumentFields.TITLE:
                return new Sort(new SortField(DocumentFields.TITLE, SortField.Type.STRING, false));
            case DocumentFields.RELEASE_DATE:
                return new Sort(new SortField(DocumentFields.RELEASE_DATE, SortField.Type.STRING, true));
            case DocumentFields.RATING:
                return new Sort(new SortField(DocumentFields.RATING, SortField.Type.DOUBLE, true));
            case DocumentFields.POPULARITY:
                return new Sort(new SortField(DocumentFields.POPULARITY, SortField.Type.DOUBLE, true));
            case DocumentFields.CREDIT_POPULARITY:
                return new Sort(new SortField(DocumentFields.CREDIT_POPULARITY, SortField.Type.DOUBLE, true));
            default:
                return new Sort(SortField.FIELD_SCORE);
        }
    }
}
