package controls;

import indexes.writers.ActorWriter;
import indexes.writers.MovieWriter;

import java.util.HashMap;
import java.util.Map;

public class StringsMap
{
    private static final Map<String, String> genreMap;

    static {
        genreMap = new HashMap<>();
        genreMap.put("Title", MovieWriter.TITLE);
        genreMap.put("Keyword", MovieWriter.OVERVIEW);
        genreMap.put("Actor", ActorWriter.NAME);
        genreMap.put("Popularity", MovieWriter.POPULARITY);
        genreMap.put("Rating", MovieWriter.RATING);
        genreMap.put("Genre", MovieWriter.GENRE);
        genreMap.put("Release Date", MovieWriter.RELEASE_DATE);
        genreMap.put("review", "review");
        genreMap.put("mid", MovieWriter.MID);

    }

    public static String get(String strName) {
        return genreMap.get(strName);
    }
}
