package entities;

public class Review
{
    private String mid;
    private String author;
    private String content;
    private String postDate;

    public Review() {

    }

    public String getMid() {
        return mid;
    }
    public void setMid(int mid) {
        this.mid = "" + mid;
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
    public String getPostDate() {
        return postDate;
    }
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + mid +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", created_at='" + postDate + '\'' +
                '}';
    }
}
