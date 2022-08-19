package database.indexer.services.entities;

import database.entities.media.Movie;
import database.indexer.analyzers.IndexAnalyzer;
import database.indexer.documents.fields.DocumentFields;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {
    private static final int hitsPerPage = 10;

    private final MovieService movieService = new MovieService(hitsPerPage);

    @Test
    @Order(1)
    void writeMovies() {
        final List<Movie> movieList = List.of(
                new Movie(500, "/poster1.jpg", "the half-blood prince", "Overview1", "1990-10-10", new int[]{14, 28}, 100.608, 6.7),
                new Movie(450, "/poster2.jpg", "bloody nights", "Overview2", "2022-07-06", new int[]{28, 14}, 200.608, 5.9),
                new Movie(400, "/poster2.jpg", "the big blow", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7),
                new Movie(400, "/poster2.jpg", "bleeding nights", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7)
        );
        final int numDocs = movieService.writeEntities(movieList);
        assertEquals(movieList.size(), numDocs);
        assertEquals(movieList.size(), movieService.getNumDocs());
    }

    @Test
    @Order(2)
    void readMovies() {
        List<Movie> movieList;
        try {
            final Query query = new QueryParser(DocumentFields.TITLE, IndexAnalyzer.ANALYZER.analyzer).parse("blow");
            final Sort sort = new Sort(SortField.FIELD_SCORE);
            movieList = movieService.readEntities(query, sort);
        }
        catch (ParseException e) {
            e.printStackTrace();
            movieList = null;
        }
        assertNotNull(movieList);
    }

    @Test
    @Order(3)
    void deleteAll() {
        final List<Movie> movieList = List.of(
                new Movie(500, "/poster1.jpg", "the half-blood prince", "Overview1", "1990-10-10", new int[]{14, 28}, 100.608, 6.7),
                new Movie(450, "/poster2.jpg", "bloody nights", "Overview2", "2022-07-06", new int[]{28, 14}, 200.608, 5.9),
                new Movie(400, "/poster2.jpg", "the big blow", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7),
                new Movie(400, "/poster2.jpg", "bleeding nights", "Overview3", "2000-07-06", new int[]{28, 14}, 35, 5.7)
        );
        movieService.writeEntities(movieList);
        movieService.deleteAll();

        List<Movie> resultsList;
        try {
            final Query query = new QueryParser(DocumentFields.TITLE, IndexAnalyzer.ANALYZER.analyzer).parse("bloody");
            final Sort sort = new Sort(SortField.FIELD_SCORE);
            resultsList = movieService.readEntities(query, sort);
        }
        catch (ParseException e) {
            e.printStackTrace();
            resultsList = null;
        }
        assertNotNull(resultsList);
        assertEquals(0, resultsList.size());
    }

    @Test
    @Order(5)
    void close() {
        assertDoesNotThrow(movieService::close);
    }
}
