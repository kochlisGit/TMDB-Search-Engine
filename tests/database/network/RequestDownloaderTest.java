package database.network;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class RequestDownloaderTest {
    private static final String MOVIES_URL =
            "https://api.themoviedb.org/3/movie/popular?api_key=ba73ff82af6b7ac884a65bf5247d4a11&language=en-US&page=1";
    private static final String REVIEWS_URL =
            "https://api.themoviedb.org/3/movie/550/reviews?api_key=ba73ff82af6b7ac884a65bf5247d4a11&language=en-US&page=1";
    private static final String CREDITS_URL =
            "https://api.themoviedb.org/3/movie/550/credits?api_key=ba73ff82af6b7ac884a65bf5247d4a11&language=en-US&page=1";

    private List<URI> uriList;

    RequestDownloaderTest() {
        try {
            uriList = List.of(new URI(MOVIES_URL), new URI(REVIEWS_URL), new URI(CREDITS_URL));
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void downloadRequests() {
        final List<CompletableFuture<String>> actualPageList = new RequestDownloader().downloadRequests(uriList);
        actualPageList.forEach(page -> {
            try {
                final String resultStr = page.get();
                assertTrue(resultStr.length() > 0);
            }
            catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
