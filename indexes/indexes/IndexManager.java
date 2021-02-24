package indexes.indexes;

public class IndexManager
{
    private final Index moviesIndex;
    private final Index reviewsIndex;
    private final Index actorsIndex;

    public IndexManager() {
        moviesIndex = new MovieIndex();
        reviewsIndex = new ReviewIndex();
        actorsIndex = new ActorIndex();
    }

    public Index getMoviesIndex() {
        return moviesIndex;
    }
    public Index getReviewsIndex() {
        return reviewsIndex;
    }
    public Index getActorsIndex() {
        return actorsIndex;
    }

    public void closeAll() {
        moviesIndex.close();
        reviewsIndex.close();
        actorsIndex.close();
    }
}
