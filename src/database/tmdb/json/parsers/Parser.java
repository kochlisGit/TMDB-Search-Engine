package database.tmdb.json.parsers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Parser<T> {

    List<T> parseEntitiesFromString(final List<CompletableFuture<String>> pageList);
}
