package indexes.readers;

import entities.Actor;
import indexes.writers.ActorWriter;
import org.apache.lucene.document.Document;

import java.util.List;
import java.util.stream.Collectors;

public class ActorExtractor implements EntityExtractor<Actor>
{
    @Override
    public List<Actor> extractEntities(List<Document> docList) {
        return docList.stream()
                .map( doc -> {
                    Actor actor = new Actor();
                    actor.setMid( Integer.parseInt( doc.get(ActorWriter.MID) ) );
                    actor.setName( doc.get(ActorWriter.NAME) );
                    actor.setCharacter( doc.get(ActorWriter.CHARACTER) );
                    return actor;
                })
                .collect( Collectors.toList() );
    }
}
