package parsers;

import com.jsoniter.JsonIterator;
import entities.Actor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActorParser implements EntityParser<Actor>
{
    @Override
    public List<Actor> parseEntities(List<String> pageList)
    {
        List<Actor> actorList = new ArrayList<>();

        pageList.forEach(page -> {
            JsonIterator iter = JsonIterator.parse(page);
            Actor actor = new Actor();
            int mid = 0;
            String role = null;

            try {
                for (String field = iter.readObject(); field != null; field = iter.readObject())
                    switch (field) {
                        case "id":
                            mid = iter.readInt();
                            break;
                        case "cast":
                            while (iter.readArray())
                                for (String actorField = iter.readObject(); actorField != null; actorField = iter.readObject())
                                    switch (actorField) {
                                        case "known_for_department":
                                            role = iter.readString();
                                            if ("Acting".equals(role)) {
                                                actor = new Actor();
                                                actor.setMid(mid);
                                            }
                                            break;
                                        case "name":
                                            actor.setName(iter.readString());
                                            break;
                                        case "character":
                                            actor.setCharacter(iter.readString());
                                            if ("Acting".equals(role))
                                                actorList.add(actor);
                                            break;
                                        default:
                                            iter.skip();
                                    }
                            break;
                        default:
                            iter.skip();
                    }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });

        return actorList;
    }
}
