package database.indexer.documents.builders.entities;

import database.entities.media.Movie;
import database.indexer.documents.builders.DocumentBuilder;
import database.indexer.documents.fields.DocumentFields;
import database.tmdb.genres.GenreMapper;
import org.apache.lucene.document.*;
import org.apache.lucene.util.BytesRef;

public class MovieDocumentBuilder implements DocumentBuilder<Movie> {
    private static final String DEFAULT_RELEASE_DATE = "1990-01-01";
    private static final String DEFAULT_POSTER_PATH = "https://i.pinimg.com/564x/a8/30/d5/a830d5ce33d163055920bd028e2f1fa3.jpg";

    @Override
    public Document toDocument(final Movie movie) {
        String releaseDate = movie.getReleaseDate();
        if (releaseDate == null) {
            releaseDate = DEFAULT_RELEASE_DATE;
        }

        String posterPath = movie.getPosterPath();
        if (posterPath == null) {
            posterPath = DEFAULT_POSTER_PATH;
        }

        final Document doc = new Document();
        doc.add(new StringField(DocumentFields.ID, String.valueOf(movie.getId()), Field.Store.YES));
        doc.add(new TextField(DocumentFields.TITLE, movie.getTitle(), Field.Store.YES));
        doc.add(new SortedDocValuesField(DocumentFields.TITLE, new BytesRef(movie.getTitle())));
        doc.add(new StoredField(DocumentFields.POSTER_PATH, posterPath));
        doc.add(new TextField(DocumentFields.OVERVIEW, movie.getOverview(), Field.Store.YES));
        doc.add(new StoredField(DocumentFields.RELEASE_DATE, releaseDate));
        doc.add(new SortedDocValuesField(DocumentFields.RELEASE_DATE, new BytesRef(releaseDate)));
        doc.add(new StoredField(DocumentFields.POPULARITY, movie.getPopularity()));
        doc.add(new NumericDocValuesField(DocumentFields.POPULARITY, Double.doubleToRawLongBits(movie.getPopularity())));
        doc.add(new StoredField(DocumentFields.RATING, movie.getRating()));
        doc.add(new NumericDocValuesField(DocumentFields.RATING, Double.doubleToRawLongBits(movie.getRating())));

        for (int genre_id : movie.getGenreIds()) {
            doc.add(new StringField(DocumentFields.GENRE_IDS, GenreMapper.MAPPER.getGenreNameById(genre_id), Field.Store.YES));
        }
        return doc;
    }
}
