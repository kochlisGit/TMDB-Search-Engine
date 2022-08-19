package database.tmdb.services;

import database.network.RequestDownloader;
import database.tmdb.json.parsers.Parser;

import java.net.URI;
import java.util.List;

public abstract class Service<T> {
    private final RequestDownloader requestDownloader;
    private final Parser<T> parser;

    public Service(RequestDownloader requestDownloader, Parser<T> parser) {
        this.requestDownloader = requestDownloader;
        this.parser = parser;

    }

    protected abstract List<URI> getURIList();

    public List<T> getEntities() {
        return parser.parseEntitiesFromString(requestDownloader.downloadRequests(getURIList()));
    }
}
