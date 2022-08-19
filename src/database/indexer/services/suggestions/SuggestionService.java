package database.indexer.services.suggestions;

import database.entities.media.Movie;
import database.entities.media.details.Credit;
import database.indexer.documents.builders.suggestions.CreditSuggestionDocumentBuilder;
import database.indexer.documents.builders.suggestions.MovieSuggestionDocumentBuilder;
import database.indexer.indexes.suggester.SuggestionIndex;
import database.indexer.readers.ReaderManager;
import database.indexer.services.Service;
import database.indexer.suggester.DocumentSuggester;
import database.indexer.suggester.SuggesterManager;
import database.indexer.writers.DocumentWriter;
import database.indexer.writers.WriterManager;

import java.util.List;

public class SuggestionService extends Service {
    private final int numSuggestions;

    public SuggestionService(int numSuggestions) {
        super(new SuggestionIndex());
        this.numSuggestions = numSuggestions;
    }

    public int writeMovies(final List<Movie> movieList) {
        final WriterManager writerManager = new WriterManager(indexManager.getDirectory());
        final int numDocs = new DocumentWriter<>(writerManager.getIndexWriter(), new MovieSuggestionDocumentBuilder()).writeDocuments(movieList);
        writerManager.close();
        return numDocs;
    }

    public int writeCredits(final List<Credit> creditList) {
        final WriterManager writerManager = new WriterManager(indexManager.getDirectory());
        final int numDocs = new DocumentWriter<>(writerManager.getIndexWriter(), new CreditSuggestionDocumentBuilder()).writeDocuments(creditList);
        writerManager.close();
        return numDocs;
    }

    public List<String> suggestTerms(final String term) {
        final ReaderManager readerManager = new ReaderManager(indexManager.getDirectory());
        final SuggesterManager suggesterManager = new SuggesterManager(indexManager.getDirectory(), readerManager.getIndexReader());

        final List<String> suggestedTerms = new DocumentSuggester(suggesterManager.getIndexSuggester(), numSuggestions).suggestTerms(term);

        suggesterManager.close();
        readerManager.close();
        return suggestedTerms;
    }
}
