package indexes.writers;

import entities.Review;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.util.List;

public class ReviewWriter extends EntityWriter<Review>
{
    public static final String MID = "mid";
    public static final String AUTHOR = "author";
    public static final String CONTENT = "content";
    public static final String POST_DATE = "post_date";

    @Override
    protected void writeEntitiesToIndex(IndexWriter writer, List<Review> entityList)
    {
        entityList.forEach(review ->
        {
            Document doc = new Document();
            doc.add( new StringField(MID, review.getMid(), Field.Store.YES) );
            doc.add( new StringField(AUTHOR, review.getAuthor(), Field.Store.YES) );
            doc.add( new TextField(CONTENT, review.getContent(), Field.Store.YES) );
            doc.add( new SortedDocValuesField( POST_DATE, new BytesRef( review.getPostDate() ) ) );
            doc.add( new StoredField( POST_DATE, review.getPostDate() ) );

            try {
                writer.addDocument(doc);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
