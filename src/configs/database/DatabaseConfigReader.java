package configs.database;

import configs.ConfigReader;

public class DatabaseConfigReader extends ConfigReader<DatabaseConfig> {

    @Override
    public String getConfigName() {
        return "database.json";
    }

    @Override
    public Class<DatabaseConfig> getClassType() {
        return DatabaseConfig.class;
    }
}
