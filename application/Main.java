package application;

import indexes.indexes.IndexManager;
import indexes.operations.IndexBuilder;
import indexes.writers.IndexBuilderProgress;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import splash.SplashPreloader;

import java.io.IOException;

public class Main extends Application
{
    private static final String FXML_FILENAME = "main.fxml";
    private static final double WINDOW_SIZE_SCALE = 0.75;

    @Override
    public void init()
    {
        IndexManager manager = new IndexManager();
        IndexBuilder builder = new IndexBuilder();
        Thread t = new Thread( () -> builder.buildIndex(manager) );
        t.start();

        double progress;
        while ( (progress = IndexBuilderProgress.INSTANCE.getProgress() ) < 100 )
        {
            notifyPreloader(new Preloader.ProgressNotification(progress) );
            try {
                Thread.sleep(250);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            t.join();
            notifyPreloader(new Preloader.ProgressNotification(100) );
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        manager.closeAll();
    }

    @Override
    public void start(Stage primaryStage)
    {
        Parent root = null;

        try {
            root = FXMLLoader.load( getClass().getResource(FXML_FILENAME) );
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        resizeWindow(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    private void resizeWindow(Stage primaryStage) {
        int winWidth = (int) (Screen.getPrimary().getBounds().getWidth() * WINDOW_SIZE_SCALE);
        int winHeight = (int) (Screen.getPrimary().getBounds().getHeight() * WINDOW_SIZE_SCALE);

        primaryStage.setWidth(winWidth);
        primaryStage.setHeight(winHeight);
    }

    public static void main(String[] Args) {
        System.setProperty( "javafx.preloader", SplashPreloader.class.getCanonicalName() );
        launch(Main.class, Args);
    }
}
