package database.indexer.documents.builders.entities;

import database.entities.media.Movie;
import database.indexer.documents.builders.entities.MovieDocumentBuilder;
import database.indexer.documents.fields.DocumentFields;
import database.tmdb.genres.GenreMapper;
import org.apache.lucene.document.Document;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MovieDocumentBuilderTest {

    @Test
    void toDocument() {
        final Movie movie = new Movie(
                1, "poster.jpg", "movietitle", "movieoverview", "1990-01-01",
                new int[]{14, 28}, 10.0, 10.0);
        final Document document = new MovieDocumentBuilder().toDocument(movie);

        assertEquals(movie.getId(), Integer.parseInt(document.get(DocumentFields.ID)));
        assertEquals(movie.getTitle(), document.get(DocumentFields.TITLE));
        assertEquals(movie.getPosterPath(), document.get(DocumentFields.POSTER_PATH));
        assertEquals(movie.getOverview(), document.get(DocumentFields.OVERVIEW));
        assertEquals(movie.getReleaseDate(), document.get(DocumentFields.RELEASE_DATE));

        final String[] expectedGenreIds = {GenreMapper.MAPPER.getGenreNameById(14), GenreMapper.MAPPER.getGenreNameById(28)};
        assertEquals(Arrays.toString(expectedGenreIds), Arrays.toString(document.getValues(DocumentFields.GENRE_IDS)));

        assertEquals(movie.getPopularity(), Double.parseDouble(document.get(DocumentFields.POPULARITY)));
        assertEquals(movie.getRating(), Double.parseDouble(document.get(DocumentFields.RATING)));
    }
}
