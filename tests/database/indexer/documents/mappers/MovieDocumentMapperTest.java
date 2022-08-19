package database.indexer.documents.mappers;

import database.entities.media.Movie;
import database.indexer.documents.builders.entities.MovieDocumentBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieDocumentMapperTest {

    @Test
    void toEntity() {
        final Movie expectedMovie = new Movie(
                1, "poster.jpg", "movietitle", "movieoverview", "1990-01-01",
                new int[]{14, 28}, 10.0, 10.0);
        final Movie actualMovie = new MovieDocumentMapper().toEntity(new MovieDocumentBuilder().toDocument(expectedMovie));
        assertEquals(expectedMovie.toString(), actualMovie.toString());
    }
}
