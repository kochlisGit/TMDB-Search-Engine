package indexes.operations;

import indexes.indexes.IndexManager;

public class IndexOperationFacade
{
    private final IndexChecker checker;
    private final IndexRemover remover;

    public IndexOperationFacade() {
        checker = new IndexChecker();
        remover = new IndexRemover();
    }

    public boolean checkIndexes(IndexManager manager) {
        return checker.executeOperation( manager.getMoviesIndex() ) &&
                checker.executeOperation( manager.getActorsIndex() ) &&
                checker.executeOperation( manager.getReviewsIndex() );
    }
    public boolean removeIndexes(IndexManager manager) {
        return remover.executeOperation( manager.getMoviesIndex() ) &&
                remover.executeOperation( manager.getActorsIndex() ) &&
                remover.executeOperation( manager.getReviewsIndex() );
    }
}
