package configs;

import com.jsoniter.JsonIterator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ConfigReader<T> {
    private static final String CONFIGURATIONS_FILEPATH = "configurations";

    protected abstract String getConfigName();

    protected abstract Class<T> getClassType();

    private Path getConfigPath() {
        return Paths.get(CONFIGURATIONS_FILEPATH, getConfigName());
    }

    public T readConfig() {
        final Path configPath = getConfigPath();
        try {
            final byte[] configBytes = Files.readAllBytes(configPath);
            return JsonIterator.deserialize(configBytes, getClassType());
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
