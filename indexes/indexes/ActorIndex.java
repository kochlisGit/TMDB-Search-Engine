package indexes.indexes;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.File;

public class ActorIndex extends Index
{
    private static final String DIRECTORY_PATH = System.getProperty("user.dir") + File.separator + "MovieRadar/index/actors/";

    public ActorIndex() {
        super(DIRECTORY_PATH);
    }

    @Override
    public Analyzer getAnalyzer() {
        return new StandardAnalyzer();
    }
}
