package indexes.operations;

import entities.Actor;
import entities.Movie;
import entities.Review;
import indexes.indexes.Index;
import indexes.indexes.IndexManager;
import indexes.writers.IndexBuilderProgress;
import indexes.writers.EntityWriterFacade;
import server.EntityRetrieverFacade;

import java.util.List;
import java.util.stream.Collectors;

public class IndexBuilder
{
    private static final int N_MOVIE_PAGES = 50;
    private static final int DOWNLOAD_MOVIES_PROGRESS = 10;
    private static final int INDEX_MOVIES_PROGRESS = 10;
    private static final int DOWNLOAD_REVIEWS_PROGRESS = 20;
    private static final int INDEX_REVIEWS_PROGRESS = 20;
    private static final int DOWNLOAD_ACTORS_PROGRESS = 20;
    private static final int INDEX_ACTORS_PROGRESS = 20;

    private final EntityWriterFacade writerFacade;
    private final EntityRetrieverFacade retrieverFacade;

    public IndexBuilder() {
        writerFacade = new EntityWriterFacade();
        retrieverFacade = new EntityRetrieverFacade();
    }

    public void buildIndex(IndexManager manager)
    {
        IndexOperationFacade operationFacade = new IndexOperationFacade();
        if ( operationFacade.checkIndexes(manager) ) {
            IndexBuilderProgress.INSTANCE.addProgress(100);
            return;
        }

        List<Movie> movieList = retrieverFacade.retrieveAllMovies(N_MOVIE_PAGES);
        Index moviesIndex = manager.getMoviesIndex();
        IndexBuilderProgress.INSTANCE.addProgress(DOWNLOAD_MOVIES_PROGRESS);
        Thread t1 = new Thread( () -> writerFacade.writeMovies(moviesIndex, movieList) );
        t1.start();

        List<String> requestList = movieList.stream()
                .map(Movie::getMid)
                .collect( Collectors.toList() );

        List<Review> reviewList = retrieverFacade.retrieveReviews(requestList);
        Index reviewsIndex = manager.getReviewsIndex();
        IndexBuilderProgress.INSTANCE.addProgress(DOWNLOAD_REVIEWS_PROGRESS);
        Thread t2 = new Thread( () -> writerFacade.writeReviews(reviewsIndex, reviewList) );
        t2.start();

        List<Actor> actorList = retrieverFacade.retrieveActors(requestList);
        Index actorsIndex = manager.getActorsIndex();
        IndexBuilderProgress.INSTANCE.addProgress(DOWNLOAD_ACTORS_PROGRESS);
        Thread t3 = new Thread( () -> writerFacade.writeActors(actorsIndex, actorList) );
        t3.start();

        try {
            t1.join();
            IndexBuilderProgress.INSTANCE.addProgress(INDEX_MOVIES_PROGRESS);

            t2.join();
            IndexBuilderProgress.INSTANCE.addProgress(INDEX_REVIEWS_PROGRESS);

            t3.join();
            IndexBuilderProgress.INSTANCE.addProgress(INDEX_ACTORS_PROGRESS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
