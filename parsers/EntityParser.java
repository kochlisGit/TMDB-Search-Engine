package parsers;

import java.util.List;

public interface EntityParser<T> {
    List<T> parseEntities(List<String> pageList);
}
