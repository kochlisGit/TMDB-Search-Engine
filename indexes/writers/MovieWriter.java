package indexes.writers;

import entities.GenresMap;
import entities.Movie;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.util.List;

public class MovieWriter extends EntityWriter<Movie>
{
    private static final String NO_RELEASE_DATE = "1900-01-01";

    public static final String MID =  "mid";
    public static final String TITLE = "title";
    public static final String OVERVIEW  = "overview";
    public static final String RELEASE_DATE = "release_date";
    public static final String POPULARITY = "popularity";
    public static final String RATING = "rating";
    public static final String POSTER_PATH = "poster_path";
    public static final String GENRE = "genre";

    @Override
    protected void writeEntitiesToIndex(IndexWriter writer, List<Movie> entityList)
    {
        entityList.forEach(movie ->
        {
            String releaseDate = movie.getReleaseDate();
            if (releaseDate == null)
                releaseDate = NO_RELEASE_DATE;

            Document doc = new Document();
            doc.add( new StringField(MID, movie.getMid(), Field.Store.YES) );
            doc.add( new SortedDocValuesField( TITLE, new BytesRef(movie.getTitle() ) ) );
            doc.add( new TextField(TITLE, movie.getTitle(), Field.Store.YES) );
            doc.add( new TextField(OVERVIEW, movie.getOverview(), Field.Store.YES) );
            doc.add( new SortedDocValuesField(RELEASE_DATE, new BytesRef(releaseDate) ) );
            doc.add( new StoredField(RELEASE_DATE, releaseDate) );
            doc.add( new NumericDocValuesField( POPULARITY, Double.doubleToRawLongBits( movie.getPopularity() ) ) );
            doc.add( new StoredField( POPULARITY, movie.getPopularity() ) );
            doc.add( new NumericDocValuesField( RATING, Double.doubleToRawLongBits( movie.getRating() ) ) );
            doc.add( new StoredField( RATING, movie.getRating() ) );
            doc.add( new StoredField( POSTER_PATH, movie.getPosterPath() ) );
            for ( int genre_id : movie.getGenreArray() )
                doc.add( new StringField(GENRE, GenresMap.get(genre_id), Field.Store.YES) );

            try {
                writer.addDocument(doc);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
