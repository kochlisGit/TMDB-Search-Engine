package database.entities.media.details;

public abstract class MediaDetails {
    protected int mediaId;

    public MediaDetails() {

    }

    public MediaDetails(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(final int mediaId) {
        this.mediaId = mediaId;
    }
}
