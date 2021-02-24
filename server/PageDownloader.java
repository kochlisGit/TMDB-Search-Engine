package server;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class PageDownloader
{
    private static final int N_THREADS = 2;

    public List<String> downloadPages(List<URI> uriList)
    {
        ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
        HttpClient client = HttpClient.newBuilder()
                .executor(executor)
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout( Duration.ofSeconds(60) )
                .build();

        List<String> pageList = uriList.parallelStream()
                .map(uri -> {
                    try {
                        return client.sendAsync(
                                HttpRequest.newBuilder(uri)
                                        .GET()
                                        .build(),
                                HttpResponse.BodyHandlers.ofString() )
                                .thenApply(HttpResponse::body).get();
                    }
                    catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList() );

        executor.shutdownNow();

        return pageList;
    }
}
