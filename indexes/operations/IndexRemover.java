package indexes.operations;

import indexes.indexes.Index;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class IndexRemover implements IndexOperation
{
    @Override
    public boolean executeOperation(Index index) {
        try {
            Files.walk(Paths.get( index.getDirectoryPath() ) )
                    .sorted( Comparator.reverseOrder() )
                    .map(Path::toFile)
                    .forEach(File::delete);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
