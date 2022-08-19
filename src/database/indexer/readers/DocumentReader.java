package database.indexer.readers;

import database.indexer.documents.mappers.DocumentMapper;
import org.apache.lucene.search.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentReader<T> {
    private final IndexSearcher indexSearcher;
    private final DocumentMapper<T> documentMapper;
    private final int hitsPerPage;

    public DocumentReader(IndexSearcher indexSearcher, DocumentMapper<T> documentMapper, int hitsPerPage) {
        this.indexSearcher = indexSearcher;
        this.documentMapper = documentMapper;
        this.hitsPerPage = hitsPerPage;
    }

    public List<T> readDocuments(final Query query, final Sort sort) {
        try {
            final TopDocs topDocs = indexSearcher.search(query, hitsPerPage, sort);
            final ScoreDoc[] scoreDocs =  topDocs.scoreDocs;
            return Arrays.stream(scoreDocs)
                    .map(scoreDoc -> {
                        try {
                            return documentMapper.toEntity(indexSearcher.doc(scoreDoc.doc));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
