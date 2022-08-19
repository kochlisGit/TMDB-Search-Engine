package database.indexer.documents.mappers;

import database.entities.media.details.Credit;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.document.Document;

public class CreditDocumentMapper implements DocumentMapper<Credit> {

    @Override
    public Credit toEntity(final Document document) {
        return new Credit(
                Integer.parseInt(document.get(DocumentFields.CREDIT_MOVIE_ID)),
                document.get(DocumentFields.NAME),
                document.get(DocumentFields.ROLE),
                Double.parseDouble(document.get(DocumentFields.CREDIT_POPULARITY))
        );
    }
}
