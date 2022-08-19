package database.network;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class RequestDownloader {
    private static final int TIMEOUT_SECONDS = 120;

    public List<CompletableFuture<String>> downloadRequests(final List<URI> uriList) {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .build();

        return uriList.parallelStream()
                .map(target -> client.sendAsync(
                        HttpRequest.newBuilder(target)
                                .GET()
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body))
                .collect(Collectors.toList());
    }
}
