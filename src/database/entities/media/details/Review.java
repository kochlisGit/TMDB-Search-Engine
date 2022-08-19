package database.entities.media.details;

public class Review extends MediaDetails {
    private String author;
    private String content;

    public Review() {

    }

    public Review(int entityId, String author, String content) {
        super(entityId);

        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Review{" +
                "entityId=" + mediaId +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
