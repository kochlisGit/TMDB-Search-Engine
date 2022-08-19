package database.tmdb.services.media;

import database.entities.media.Movie;
import database.network.RequestDownloader;
import database.tmdb.json.parsers.MovieParser;
import database.tmdb.requests.builders.RequestBuilder;
import database.tmdb.requests.media.MovieRequest;
import database.tmdb.services.Service;

import java.net.URI;
import java.util.List;

public class MovieService extends Service<Movie> {
    private final RequestBuilder requestBuilder;
    private final int numPages;

    public MovieService(RequestDownloader requestDownloader, String apiKey, int numPages) {
        super(requestDownloader, new MovieParser());

        this.numPages = numPages;
        requestBuilder = new RequestBuilder(new MovieRequest(), apiKey);
    }

    @Override
    protected List<URI> getURIList() {
        return requestBuilder.buildURIList(numPages);
    }
}
