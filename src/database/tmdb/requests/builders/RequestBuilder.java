package database.tmdb.requests.builders;

import database.tmdb.requests.Request;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class RequestBuilder {
    private final Request request;
    protected final String paramsStr;

    public RequestBuilder(Request request, String apiKey) {
        this.request = request;
        this.paramsStr = "?api_key=" + apiKey + "&language=en-US&page=";
    }

    public List<URI> buildURIList(final int numPages) {
        final String baseURL = request.getBaseURL();
        final List<URI> urlList = new ArrayList<>();

        for (int i = 1; i < numPages + 1; i++) {
            try {
                urlList.add(new URI(baseURL + paramsStr + i));
            }
            catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        return urlList;
    }
}
