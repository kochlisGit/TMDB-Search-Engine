package server;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class URIBuilder
{
    private static final String URL_MOVIES = "https://api.themoviedb.org/3/movie/";
    private static final String URL_TAIL = "?api_key=e9f21b42185a3b3fa6569e47291768d0&language=en-US&page=";

    private String baseUrl;
    private String params;
    private int nPages;

    public URIBuilder() {
        baseUrl = URL_MOVIES;
        params = "";
        nPages = 1;
    }

    public static URIBuilder newBuilder() {
        return new URIBuilder();
    }

    public URIBuilder setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
    public URIBuilder setParams(String params) {
        this.params = params;
        return this;
    }
    public URIBuilder setnPages(int nPages) {
        this.nPages = nPages;
        return this;
    }

    // Builds a URI List of the requested pages.
    public List<URI> build(List<String> requestList)
    {
        List<URI> uriList = new ArrayList<>();

        requestList
                .forEach(request -> {
                    String url = baseUrl + request + params + URL_TAIL;
                    try {
                        for (int i = 1; i < nPages+1; i++)
                            uriList.add( new URI(url + i) );
                    }
                    catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                } );

        return uriList;
    }
}
