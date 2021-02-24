package indexes.readers;

import indexes.writers.ActorWriter;
import indexes.writers.MovieWriter;

public class DocumentRetrieverFactory
{
    public static DocumentRetriever getRetriever(String retrieverType, EntitySearcher searcher)
    {
        switch (retrieverType)
        {
            case MovieWriter.TITLE:
                return new TitleRetriever(searcher);
            case MovieWriter.OVERVIEW:
                return new KeywordRetriever(searcher);
            case MovieWriter.GENRE:
                return new GenreRetriever(searcher);
            case MovieWriter.MID:
                return new IdRetriever(searcher);
            case ActorWriter.NAME:
                return new ActorRetriever(searcher);
            default:
                return null;
        }
    }
}
