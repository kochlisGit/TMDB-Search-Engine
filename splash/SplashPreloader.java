package splash;

import controllers.SplashController;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SplashPreloader extends Preloader
{
    private static final String FXML_FILENAME = "splash.fxml";

    private Stage primaryStage;
    private SplashController splashController;

    @Override
    public void start(Stage initStage)
    {
        this.primaryStage = initStage;
        System.out.println(getClass().getResource(FXML_FILENAME));
        FXMLLoader loader = new FXMLLoader( getClass().getResource(FXML_FILENAME) );
        Parent root = null;

        try {
            root = loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        initStage.setScene(scene);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.show();

        splashController = loader.getController();
        splashController.fadeInWindow();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification preloaderNotification) {
        if (preloaderNotification instanceof ProgressNotification)
        {
            double progress = ((ProgressNotification) preloaderNotification).getProgress();
            splashController.showProgressText("Loading: " + (int) progress + "%");
            splashController.showProgressFlow(progress);

            if (progress == 100)
                splashController.fadeOutWindow();
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification changeNotification) {
        if (changeNotification.getType() == StateChangeNotification.Type.BEFORE_START)
            primaryStage.hide();
    }
}
