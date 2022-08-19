package database.indexer.writers;

import database.indexer.documents.builders.DocumentBuilder;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentWriter<T> {
    private final IndexWriter indexWriter;
    private final DocumentBuilder<T> documentBuilder;

    public DocumentWriter(IndexWriter indexWriter, DocumentBuilder<T> documentBuilder) {
        this.indexWriter = indexWriter;
        this.documentBuilder = documentBuilder;
    }

    public int writeDocuments(final List<T> entityList) {
        final List<Document> documentList = entityList.parallelStream()
                .map(documentBuilder::toDocument)
                .collect(Collectors.toList());
        try {
            indexWriter.addDocuments(documentList);
        }
        catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return documentList.size();
    }
}
