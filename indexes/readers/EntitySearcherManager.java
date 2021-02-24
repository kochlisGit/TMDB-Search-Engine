package indexes.readers;

import indexes.indexes.IndexManager;
import indexes.writers.ActorWriter;
import indexes.writers.MovieWriter;

public class EntitySearcherManager
{
    private final EntitySearcher movieSearcher;
    private final EntitySearcher reviewSearcher;
    private final EntitySearcher actorSearcher;

    public EntitySearcherManager(IndexManager manager) {
        movieSearcher = new EntitySearcher( manager.getMoviesIndex() );
        reviewSearcher = new EntitySearcher( manager.getReviewsIndex() );
        actorSearcher = new EntitySearcher( manager.getActorsIndex() );
    }

    public EntitySearcher getSearcher(String searcherType) {
        switch (searcherType)
        {
            case MovieWriter.TITLE:
            case MovieWriter.OVERVIEW:
                return movieSearcher;
            case "review":
                return reviewSearcher;
            case ActorWriter.NAME:
                return actorSearcher;
            default:
                return null;
        }
    }
}
