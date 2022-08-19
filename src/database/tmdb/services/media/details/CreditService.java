package database.tmdb.services.media.details;

import database.entities.media.details.Credit;
import database.network.RequestDownloader;
import database.tmdb.json.parsers.CreditParser;
import database.tmdb.requests.Request;
import database.tmdb.requests.media.details.CreditRequest;

import java.util.List;

public class CreditService extends MediaDetailsService<Credit> {

    public CreditService(RequestDownloader requestDownloader, String apiKey, int numPages,
                         List<Integer> mediaIdList) {
        super(requestDownloader, new CreditParser(), apiKey, numPages, mediaIdList);
    }

    @Override
    protected Request getRequest(int id) {
        return new CreditRequest(id);
    }
}
