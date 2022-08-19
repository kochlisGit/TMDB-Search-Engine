package database.tmdb.json.parsers;

import database.entities.media.details.Credit;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class CreditParserTest {
    private static final String PAGE_STR =
            "{\"id\":550,\"cast\":[{\"adult\":false,\"gender\":2,\"id\":819,\"known_for_department\":\"Acting\",\"name\":\"Edward Norton\"," +
                    "\"original_name\":\"Edward Norton\",\"popularity\":16.198,\"profile_path\":\"/5XBzD5WuTyVQZeS4VI25z2moMeY.jpg\",\"cast_id\":4," +
                    "\"character\":\"The Narrator\",\"credit_id\":\"52fe4250c3a36847f80149f3\",\"order\":0}," +
                    "{\"adult\":false,\"gender\":2,\"id\":819,\"known_for_department\":\"Helping\",\"name\":\"Edward Norton2\"," +
                    "\"original_name\":\"Edward Norton\",\"popularity\":16.198,\"profile_path\":\"/5XBzD5WuTyVQZeS4VI25z2moMeY.jpg\",\"cast_id\":4," +
                    "\"character\":\"The badass\",\"credit_id\":\"52fe4250c3a36847f80149f3\",\"order\":0}]," +
                    "\"crew\":[{\"adult\":false,\"gender\":2,\"id\":376,\"known_for_department\":\"Production\",\"name\":\"Arnon Milchan\"," +
                    "\"original_name\":\"Arnon Milchan\",\"popularity\":2.2,\"profile_path\":\"/b2hBExX4NnczNAnLuTBF4kmNhZm.jpg\"," +
                    "\"credit_id\":\"55731b8192514111610027d7\",\"department\":\"Production\",\"job\":\"Executive Producer\"}," +
                    "{\"adult\":false,\"gender\":2,\"id\":376,\"known_for_department\":\"Production\",\"name\":\"Dir Milchan\"," +
                    "\"original_name\":\"Arnon Milchan\",\"popularity\":2.2,\"profile_path\":\"/b2hBExX4NnczNAnLuTBF4kmNhZm.jpg\"," +
                    "\"credit_id\":\"55731b8192514111610027d7\",\"department\":\"Production\",\"job\":\"Director\"}]}";
    private final List<CompletableFuture<String>> pageList;
    private final List<Credit> expectedEntityList;

    public CreditParserTest() {
        final CompletableFuture<String> completablePage = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            completablePage.complete(PAGE_STR);
        });
        pageList = List.of(completablePage);

        expectedEntityList = List.of(
                new Credit(550, "Edward Norton", "The Narrator", 16.198),
                new Credit(550, "Dir Milchan", "Director", 2.2)
        );
    }

    @Test
    void parseEntitiesFromString() {
        final List<Credit> actualEntityList = new CreditParser().parseEntitiesFromString(pageList);

        assertEquals(2, actualEntityList.size());
        for (int i = 0; i < 2; i++) {
            assertEquals(expectedEntityList.get(i).toString(), actualEntityList.get(i).toString());
        }
    }
}
