package parsers;

import com.jsoniter.JsonIterator;
import entities.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewParser implements EntityParser<Review>
{
    @Override
    public List<Review> parseEntities(List<String> pageList)
    {
        List<Review> reviewList = new ArrayList<>();

        pageList.forEach(page -> {
            JsonIterator iter = JsonIterator.parse(page);
            Review review = new Review();
            int mid = 0;

            try {
                for ( String field = iter.readObject(); field != null; field = iter.readObject() )
                    switch (field)
                    {
                        case "id":
                            mid =  iter.readInt();
                            break;
                        case "results":
                            while ( iter.readArray() )
                                for ( String reviewField = iter.readObject(); reviewField != null; reviewField = iter.readObject() )
                                    switch (reviewField)
                                    {
                                        case "author":
                                            review = new Review();
                                            review.setMid(mid);
                                            review.setAuthor( iter.readString() );
                                            break;
                                        case "content":
                                            review.setContent( iter.readString() );
                                            break;
                                        case "created_at":
                                            review.setPostDate( iter.readString().split("T")[0] );
                                            reviewList.add(review);
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

        return reviewList;
    }
}
