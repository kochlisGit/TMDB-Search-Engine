package indexes.indexes;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.File;

public class ReviewIndex extends Index
{
    private static final String DIRECTORY_PATH = System.getProperty("user.dir") + File.separator + "MovieRadar/index/reviews/";

    public ReviewIndex() {
        super(DIRECTORY_PATH);
    }

    public Analyzer getAnalyzer() {
        return new StandardAnalyzer();
    }
}
