package indexes.writers;

import entities.Actor;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;
import java.util.List;

public class ActorWriter extends EntityWriter<Actor>
{
    public static final String MID = "mid";
    public static final String NAME = "name";
    public static final String CHARACTER = "character";

    @Override
    protected void writeEntitiesToIndex(IndexWriter writer, List<Actor> entityList)
    {
        entityList.forEach(actor ->
        {
            if (actor.getCharacter() != null)
            {
                Document doc = new Document();
                doc.add(new StringField(MID, actor.getMid(), Field.Store.YES));
                doc.add(new TextField(NAME, actor.getName(), Field.Store.YES));
                doc.add(new TextField(CHARACTER, actor.getCharacter(), Field.Store.YES));

                try {
                    writer.addDocument(doc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
