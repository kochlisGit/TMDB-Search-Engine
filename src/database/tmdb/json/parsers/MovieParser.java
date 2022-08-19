package database.tmdb.json.parsers;

import com.jsoniter.JsonIterator;
import database.entities.media.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MovieParser implements Parser<Movie> {

    @Override
    public List<Movie> parseEntitiesFromString(final List<CompletableFuture<String>> pageList) {
        final List<Movie> movieList = new ArrayList<>();

        pageList.parallelStream().forEach(page -> {
            try {
                final JsonIterator iter = JsonIterator.parse(page.get());
                Movie movie = new Movie();

                for (String field = iter.readObject(); field != null; field = iter.readObject()) {
                    if ("results".equals(field)) {
                        while (iter.readArray()) {
                            for (String movieField = iter.readObject(); movieField != null; movieField = iter.readObject()) {
                                switch (movieField) {
                                    case "id":
                                        movie.setId(iter.readInt());
                                        break;
                                    case "title":
                                        movie.setTitle(iter.readString());
                                        break;
                                    case "overview":
                                        movie.setOverview(iter.readString());
                                        break;
                                    case "release_date":
                                        movie.setReleaseDate(iter.readString());
                                        break;
                                    case "vote_average":
                                        movie.setRating(iter.readDouble());
                                        movieList.add(movie);
                                        break;
                                    case "popularity":
                                        movie.setPopularity(iter.readDouble());
                                        break;
                                    case "poster_path":
                                        movie.setPosterPath(iter.readString());
                                        break;
                                    case "genre_ids":
                                        movie = new Movie();
                                        movie.setGenreIds(iter.read(int[].class));
                                        break;
                                    default:
                                        iter.skip();
                                }
                            }
                        }
                    }
                    else {
                        iter.skip();
                    }
                }
                iter.close();
            }
            catch (InterruptedException | ExecutionException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        return movieList;
    }
}
