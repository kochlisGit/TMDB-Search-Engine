package database.indexer.suggester;

import org.apache.lucene.search.suggest.Lookup;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentSuggester {
    private final Lookup indexSuggester;
    private final int numSuggestions;

    public DocumentSuggester(Lookup indexSuggester, int numSuggestions) {
        this.indexSuggester = indexSuggester;
        this.numSuggestions = numSuggestions;
    }

    public List<String> suggestTerms(final String text) {
        final List<Lookup.LookupResult> lookupResults;
        try {
            lookupResults = indexSuggester.lookup(text, false, numSuggestions);
            return lookupResults.stream()
                    .map(lookup -> lookup.key.toString())
                    .collect(Collectors.toList());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
