package database.indexer.documents.mappers;

import database.entities.media.Movie;
import database.indexer.documents.fields.DocumentFields;
import database.tmdb.genres.GenreMapper;
import org.apache.lucene.document.Document;

import java.util.Arrays;

public class MovieDocumentMapper implements DocumentMapper<Movie> {

    @Override
    public Movie toEntity(final Document document) {
        return new Movie(
                Integer.parseInt(document.get(DocumentFields.ID)),
                document.get(DocumentFields.POSTER_PATH),
                document.get(DocumentFields.TITLE),
                document.get(DocumentFields.OVERVIEW),
                document.get(DocumentFields.RELEASE_DATE),
                Arrays.stream(document.getValues(DocumentFields.GENRE_IDS)).mapToInt(GenreMapper.MAPPER::getGenreIdByName).toArray(),
                Double.parseDouble(document.get(DocumentFields.POPULARITY)),
                Double.parseDouble(document.get(DocumentFields.RATING))
        );
    }
}
