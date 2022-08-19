package configs.tmdb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TMDBConfigReaderTest {
    private static final String API_KEY = "ba73ff82af6b7ac884a65bf5247d4a11";
    private static final int NUM_MOVIES_PAGES = 50;
    private static final int NUM_REVIEW_PAGES = 1;
    private static final int NUM_CREDITS_PAGES = 1;

    @Test
    public void readConfig() {
        final TMDBConfig actualConfig = new TMDBConfigReader().readConfig();
        final TMDBConfig expectedConfig = new TMDBConfig(API_KEY, NUM_MOVIES_PAGES, NUM_REVIEW_PAGES, NUM_CREDITS_PAGES);
        assertNotNull(actualConfig);
        assertEquals(expectedConfig.toString(), actualConfig.toString());
    }
}
