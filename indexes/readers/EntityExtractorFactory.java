package indexes.readers;

import indexes.writers.ActorWriter;
import indexes.writers.MovieWriter;

public class EntityExtractorFactory
{
    public static EntityExtractor getExtractor(String extractorType) {
        switch (extractorType)
        {
            case MovieWriter.TITLE:
                return new MovieExtractor();
            case "review":
                return new ReviewExtractor();
            case ActorWriter.NAME:
                return new ActorExtractor();
            default:
                return null;
        }
    }
}
