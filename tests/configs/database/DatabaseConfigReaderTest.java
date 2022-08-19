package configs.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConfigReaderTest {
    private static final boolean CLEANUP = true;
    private static final int HITS_PER_PAGE = 100;
    private static final int NUM_SUGGESTIONS = 10;

    @Test
    public void readConfig() {
        final DatabaseConfig actualConfig = new DatabaseConfigReader().readConfig();
        final DatabaseConfig expectedConfig = new DatabaseConfig(CLEANUP, HITS_PER_PAGE, NUM_SUGGESTIONS);
        assertNotNull(actualConfig);
        assertEquals(expectedConfig.toString(), actualConfig.toString());
    }
}
