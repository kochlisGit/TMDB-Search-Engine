package database.tmdb.json.parsers;

import com.jsoniter.JsonIterator;
import database.entities.media.details.Credit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CreditParser implements Parser<Credit> {

    @Override
    public List<Credit> parseEntitiesFromString(final List<CompletableFuture<String>> pageList) {
        final List<Credit> creditsList = new ArrayList<>();

        pageList.parallelStream().forEach(page -> {
            try {
                final JsonIterator iter = JsonIterator.parse(page.get());
                Credit credit = new Credit();
                int movieId = 0;
                String role;

                for (String field = iter.readObject(); field != null; field = iter.readObject()) {
                    switch (field) {
                        case "id":
                            movieId = iter.readInt();
                            break;
                        case "cast":
                            role = "Acting";

                            while (iter.readArray()) {
                                for (String castField = iter.readObject(); castField != null; castField = iter.readObject()) {
                                    switch (castField) {
                                        case "known_for_department":
                                            role = iter.readString();
                                            credit = new Credit();
                                            credit.setMediaId(movieId);
                                            break;
                                        case "name":
                                            credit.setName(iter.readString());
                                            break;
                                        case "popularity":
                                            credit.setPopularity(iter.readDouble());
                                            break;
                                        case "character":
                                            credit.setRole(iter.readString());
                                            if ("Acting".equals(role)) {
                                                creditsList.add(credit);
                                            }
                                            break;
                                        default:
                                            iter.skip();
                                    }
                                }
                            }
                            break;
                        case "crew":
                            role = "Director";

                            while (iter.readArray()) {
                                for (String crewField = iter.readObject(); crewField != null; crewField = iter.readObject()) {
                                    switch (crewField) {
                                        case "job":
                                            role = iter.readString();
                                            if ("Director".equals(role)) {
                                                credit.setRole("Director");
                                                creditsList.add(credit);
                                            }
                                            break;
                                        case "name":
                                            credit = new Credit();
                                            credit.setMediaId(movieId);
                                            credit.setName(iter.readString());
                                            break;
                                        case "popularity":
                                            credit.setPopularity(iter.readDouble());
                                            break;
                                        default:
                                            iter.skip();
                                    }
                                }
                            }
                            break;
                        default:
                            iter.skip();
                            break;
                    }
                }
                iter.close();
            }
            catch (InterruptedException | ExecutionException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        return creditsList;
    }
}
