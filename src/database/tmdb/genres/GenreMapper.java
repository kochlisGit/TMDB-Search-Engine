package database.tmdb.genres;

import java.util.HashMap;
import java.util.Map;

public enum GenreMapper {
    MAPPER;

    private final Map<Integer, String> idToNameMap;
    private final Map<String, Integer> nameToIdMap;

    GenreMapper() {
        idToNameMap = new HashMap<>();
        idToNameMap.put(28, "Action");
        idToNameMap.put(12, "Adventure");
        idToNameMap.put(16, "Animation");
        idToNameMap.put(35, "Comedy");
        idToNameMap.put(80, "Crime");
        idToNameMap.put(99, "Documentary");
        idToNameMap.put(18, "Drama");
        idToNameMap.put(10751, "Family");
        idToNameMap.put(14, "Fantasy");
        idToNameMap.put(36, "History");
        idToNameMap.put(27, "Horror");
        idToNameMap.put(10402, "Music");
        idToNameMap.put(9648, "Mystery");
        idToNameMap.put(10749, "Romance");
        idToNameMap.put(878, "Science Fiction");
        idToNameMap.put(10770, "TV Movie");
        idToNameMap.put(53, "Thriller");
        idToNameMap.put(10752, "War");
        idToNameMap.put(37, "Western");

        nameToIdMap = new HashMap<>();
        idToNameMap.forEach((key, value) -> nameToIdMap.put(value, key));
    }

    public String getGenreNameById(final int id) {
        return idToNameMap.get(id);
    }

    public int getGenreIdByName(final String name) {
        return nameToIdMap.get(name);
    }
}
