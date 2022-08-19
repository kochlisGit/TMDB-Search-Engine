package configs.database;

public class DatabaseConfig {
    private boolean cleanupAfterTermination;
    private int hitsPerPage;
    private int numSuggestions;

    public DatabaseConfig() {

    }

    public DatabaseConfig(boolean cleanupAfterTermination, int hitsPerPage, int numSuggestions) {
        this.cleanupAfterTermination = cleanupAfterTermination;
        this.hitsPerPage = hitsPerPage;
        this.numSuggestions = numSuggestions;
    }

    public boolean isCleanupAfterTermination() {
        return cleanupAfterTermination;
    }

    public void setCleanupAfterTermination(boolean cleanupAfterTermination) {
        this.cleanupAfterTermination = cleanupAfterTermination;
    }

    public int getHitsPerPage() {
        return hitsPerPage;
    }

    public void setHitsPerPage(int hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }

    public int getNumSuggestions() {
        return numSuggestions;
    }

    public void setNumSuggestions(int numSuggestions) {
        this.numSuggestions = numSuggestions;
    }

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "cleanupAfterTermination=" + cleanupAfterTermination +
                ", hitsPerPage=" + hitsPerPage +
                ", numSuggestions=" + numSuggestions +
                '}';
    }
}
