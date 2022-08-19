package database.tmdb.requests.media.details;

import database.tmdb.requests.Request;

public abstract class MediaDetailsRequest implements Request {
    protected final int mediaId;

    public MediaDetailsRequest(int mediaId) {
        this.mediaId = mediaId;
    }
}
