package database.indexer.suggester;

import database.indexer.Manager;
import database.indexer.analyzers.IndexAnalyzer;
import database.indexer.documents.fields.DocumentFields;
import database.indexer.indexes.IndexManager;
import database.indexer.readers.ReaderManager;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.analyzing.BlendedInfixSuggester;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class SuggesterManager implements Manager {
    private static final String FIELD = DocumentFields.SUGGESTION;

    private BlendedInfixSuggester indexSuggester;

    public SuggesterManager(Directory directory, IndexReader indexReader) {
        assert directory != null : "AssertionError: Injected closed Directory to SuggesterManager class";
        assert indexReader != null : "Assertion Error: Injected closed IndexReader to SuggesterManager class";

        try {
            indexSuggester = new BlendedInfixSuggester(directory, IndexAnalyzer.ANALYZER.analyzer);
            indexSuggester.build(new LuceneDictionary(indexReader, FIELD));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Lookup getIndexSuggester() {
        return indexSuggester;
    }

    @Override
    public void close() {
        try {
            indexSuggester.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
