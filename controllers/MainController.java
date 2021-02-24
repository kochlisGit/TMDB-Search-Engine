package controllers;

import animatefx.animation.FadeIn;
import controls.FieldBoxDecorator;
import controls.FieldItem;
import indexes.indexes.IndexManager;
import indexes.readers.EntitySearcherManager;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.glyphfont.Glyph;

public class MainController {
    @FXML private BorderPane rootPane;
    @FXML private ComboBox<FieldItem> fieldBox;
    @FXML private CustomTextField searchField;

    private IndexManager indexManager;
    private EntitySearcherManager searcherManager;

    private double mouseX, mouseY;

    @FXML public void initialize() {
        indexManager = new IndexManager();
        searcherManager = new EntitySearcherManager(indexManager);

        fadeInWindow();
        new FieldBoxDecorator(fieldBox).decorate();
        new SearchBar(searchField, fieldBox, searcherManager);
    }

    private void search()
    {
        String searchKeyword = searchField.getText().toLowerCase();
        String searchField = fieldBox.getSelectionModel().getSelectedItem().getFieldName();
    }

    private void maximizeWindow(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        if ( !primaryStage.isFullScreen() ) {
            primaryStage.setFullScreenExitHint("");
            primaryStage.setFullScreen(true);
        } else
            primaryStage.setFullScreen(false);
    }
    private void fadeInWindow() {
        new FadeIn(rootPane).setSpeed(0.5).play();
    }

    @FXML public void titleBarBtnHover(MouseEvent mouseEvent) {
        Glyph glyph = (Glyph) mouseEvent.getSource();
        Color glyphColor = (Color) glyph.getTextFill();
        glyph.setEffect(new DropShadow(15, glyphColor));
    }
    @FXML public void searchBtnHover(MouseEvent mouseEvent) {
        Glyph glyph = (Glyph) mouseEvent.getSource();
        Color glyphColor = (Color) glyph.getTextFill();
        glyph.setEffect( new DropShadow(15, glyphColor) );

        ScaleTransition scaling = new ScaleTransition(Duration.millis(500), glyph);
        scaling.setByX(1.1);
        scaling.setByY(1.1);
        scaling.setCycleCount(2);
        scaling.setAutoReverse(true);
        scaling.playFromStart();

        glyph.setCursor(Cursor.HAND);
    }
    @FXML public void nodeHoverReset(MouseEvent mouseEvent) {
        ( (Node) mouseEvent.getSource() ).setEffect(null);
    }

    @FXML public void titleBarMousePressed(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        mouseX = primaryStage.getX() - mouseEvent.getScreenX();
        mouseY = primaryStage.getY() - mouseEvent.getScreenY();
    }
    @FXML public void titleBarMouseDragged(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setX(mouseEvent.getScreenX() + mouseX);
        primaryStage.setY(mouseEvent.getScreenY() + mouseY);
    }
    @FXML public void titleBarMouseClicked(MouseEvent mouseEvent) {
        if ( mouseEvent.getButton().equals(MouseButton.PRIMARY) )
            if (mouseEvent.getClickCount() == 2)
                maximizeWindow(mouseEvent);
    }

    @FXML public void minBtnClicked(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }
    @FXML public void maxBtnClicked(MouseEvent mouseEvent) {
        maximizeWindow(mouseEvent);
    }
    @FXML public void exitBtnClicked(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    @FXML public void searchKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER)
            return;
    }
    @FXML public void searchBtnClicked(MouseEvent mouseEvent) {
        return;
    }
}
