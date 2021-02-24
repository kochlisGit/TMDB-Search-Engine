package parsers;

import com.jsoniter.JsonIterator;
import entities.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieParser implements EntityParser<Movie>
{
    @Override
    public List<Movie> parseEntities(List<String> pageList)
    {
        List<Movie> movieList = new ArrayList<>();

        pageList.forEach(page -> {
            JsonIterator iter = JsonIterator.parse(page);
            Movie movie = new Movie();

            try {
                for ( String field = iter.readObject(); field != null; field = iter.readObject() )
                    if ( "results".equals(field) )
                        while ( iter.readArray() )
                            for ( String movieField = iter.readObject(); movieField != null; movieField = iter.readObject() )
                                switch (movieField)
                                {
                                    case "id":
                                        movie.setMid( iter.readInt() );
                                        break;
                                    case "title":
                                        movie.setTitle( iter.readString() );
                                        break;
                                    case "overview":
                                        movie.setOverview( iter.readString() );
                                        break;
                                    case "release_date":
                                        movie.setReleaseDate( iter.readString() );
                                        break;
                                    case "popularity":
                                        movie.setPopularity( iter.readDouble() );
                                        break;
                                    case "vote_average":
                                        movie.setRating( iter.readDouble() );
                                        movieList.add(movie);
                                        break;
                                    case "poster_path":
                                        movie.setPosterPath( iter.readString() );
                                        break;
                                    case "genre_ids":
                                        movie = new Movie();
                                        movie.setGenreArray( iter.read(int[].class) );
                                        break;
                                    default:
                                        iter.skip();
                                }
                    else
                        iter.skip();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });

        return movieList;
    }
}
