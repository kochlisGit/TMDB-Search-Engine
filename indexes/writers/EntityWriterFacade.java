package indexes.writers;

import entities.Actor;
import entities.Movie;
import entities.Review;
import indexes.indexes.Index;

import java.util.List;

public class EntityWriterFacade
{
    private final EntityWriter<Movie> movieWriter;
    private final EntityWriter<Review> reviewWriter;
    private final EntityWriter<Actor> actorWriter;

    public EntityWriterFacade() {
        movieWriter = new MovieWriter();
        reviewWriter = new ReviewWriter();
        actorWriter = new ActorWriter();
    }

    public void writeMovies(Index index, List<Movie> movieList) {
        movieWriter.indexEntities(index, movieList);
    }
    public void writeReviews(Index index, List<Review> reviewList) {
        reviewWriter.indexEntities(index, reviewList);
    }
    public void writeActors(Index index, List<Actor> actorList) {
        actorWriter.indexEntities(index, actorList);
    }
}
