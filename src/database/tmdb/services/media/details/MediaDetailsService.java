package database.tmdb.services.media.details;

import database.network.RequestDownloader;
import database.tmdb.json.parsers.Parser;
import database.tmdb.requests.Request;
import database.tmdb.requests.builders.RequestBuilder;
import database.tmdb.services.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MediaDetailsService<T> extends Service<T> {
    private final String apiKey;
    private final int numPages;
    private final List<Integer> mediaIdList;

    public MediaDetailsService(RequestDownloader requestDownloader, Parser<T> parser, String apiKey, int numPages, final List<Integer> mediaIdList) {
        super(requestDownloader, parser);

        this.apiKey = apiKey;
        this.numPages = numPages;
        this.mediaIdList = mediaIdList;
    }

    protected abstract Request getRequest(final int id);

    @Override
    protected List<URI> getURIList() {
        return mediaIdList.stream()
                .map(id -> new RequestBuilder(getRequest(id), apiKey).buildURIList(numPages))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
