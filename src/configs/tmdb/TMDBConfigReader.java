package configs.tmdb;

import configs.ConfigReader;

public class TMDBConfigReader extends ConfigReader<TMDBConfig> {

    @Override
    public String getConfigName() {
        return "tmdb.json";
    }

    @Override
    public Class<TMDBConfig> getClassType() {
        return TMDBConfig.class;
    }
}
