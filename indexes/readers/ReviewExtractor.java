package indexes.readers;

import entities.Review;
import indexes.writers.ReviewWriter;
import org.apache.lucene.document.Document;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewExtractor implements EntityExtractor<Review>
{
    @Override
    public List<Review> extractEntities(List<Document> docList) {
        return docList.stream()
                .map( doc -> {
                    Review review = new Review();
                    review.setMid( Integer.parseInt( doc.get(ReviewWriter.MID) ) );
                    review.setAuthor( doc.get(ReviewWriter.AUTHOR) );
                    review.setContent( doc.get(ReviewWriter.CONTENT) );
                    review.setPostDate( doc.get(ReviewWriter.POST_DATE) );
                    return review;
                })
                .collect( Collectors.toList() );
    }
}
