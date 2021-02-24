package indexes.readers;

import entities.Movie;
import indexes.writers.MovieWriter;
import org.apache.lucene.document.Document;

import java.util.List;
import java.util.stream.Collectors;

public class MovieExtractor implements EntityExtractor<Movie>
{
    @Override
    public List<Movie> extractEntities(List<Document> docList) {
        return docList.stream()
                .map( doc -> {
                    Movie movie = new Movie();
                    movie.setMid( Integer.parseInt( doc.get(MovieWriter.MID) ) );
                    movie.setTitle( doc.get(MovieWriter.TITLE) );
                    movie.setOverview( doc.get(MovieWriter.OVERVIEW) );
                    movie.setReleaseDate( doc.get(MovieWriter.RELEASE_DATE) );
                    movie.setPopularity( Double.parseDouble(doc.get(MovieWriter.POPULARITY) ) );
                    movie.setRating( Double.parseDouble(doc.get(MovieWriter.RATING) ) );
                    movie.setPosterPath( doc.get(MovieWriter.POSTER_PATH) );

                    String[] genresArray = doc.getValues(MovieWriter.GENRE);
                    for (String genre : genresArray)
                        movie.addGenre(genre);

                    return movie;
                })
                .collect( Collectors.toList() );
    }
}
