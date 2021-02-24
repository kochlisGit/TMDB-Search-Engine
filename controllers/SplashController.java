package controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

public class SplashController
{
    @FXML private AnchorPane rootPane;
    @FXML private Label progressLabel;
    @FXML private ProgressBar progressBar;

    public void fadeInWindow() {
        new FadeIn(rootPane).setSpeed(0.5).play();
    }
    public void fadeOutWindow() {
        new FadeOut(rootPane).setSpeed(0.5).play();
    }

    public void showProgressText(String progressText) {
        progressLabel.setText(progressText);
    }
    public void showProgressFlow(double progress) {
        progressBar.setProgress(progress/100);
    }
}
