package configs.tmdb;

public class TMDBConfig {
    private String apiKey;
    private int moviesNumPages;
    private int reviewsNumPages;
    private int creditsNumPages;

    public TMDBConfig() {

    }

    public TMDBConfig(String apiKey, int moviesNumPages, int reviewsNumPages, int creditsNumPages) {
        this.apiKey = apiKey;
        this.moviesNumPages = moviesNumPages;
        this.reviewsNumPages = reviewsNumPages;
        this.creditsNumPages = creditsNumPages;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getMoviesNumPages() {
        return moviesNumPages;
    }

    public void setMoviesNumPages(int moviesNumPages) {
        this.moviesNumPages = moviesNumPages;
    }

    public int getReviewsNumPages() {
        return reviewsNumPages;
    }

    public void setReviewsNumPages(int reviewsNumPages) {
        this.reviewsNumPages = reviewsNumPages;
    }

    public int getCreditsNumPages() {
        return creditsNumPages;
    }

    public void setCreditsNumPages(int creditsNumPages) {
        this.creditsNumPages = creditsNumPages;
    }

    @Override
    public String toString() {
        return "TMDBConfig{" +
                "apiKey='" + apiKey + '\'' +
                ", moviesNumPages=" + moviesNumPages +
                ", reviewsNumPages=" + reviewsNumPages +
                ", creditsNumPages=" + creditsNumPages +
                '}';
    }
}
