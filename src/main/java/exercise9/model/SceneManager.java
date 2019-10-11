package exercise9.model;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import exercise9.App;
import javafx.scene.Parent;
import javafx.stage.Stage;

public enum SceneManager
{
    START("WelcomeScreen"), 
    GAME("GameScreen"), 
    WIN("WinnerScreen");
    
    private final String fxml;
    private static Stage stage;
    
    private SceneManager(final String fxml) {
        //this.fxml = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, fxml);
        this.fxml = fxml + ".fxml";
    }
    
    private String getFxml() {
        return this.fxml;
    }
    
    public static void bind(final Stage window) {
        SceneManager.stage = window;
    }
    
    public static void show(final SceneManager sceneSelect) {
        SoundManager.GAMEMUSIC.stop();
        try {
            final Parent root = (Parent)FXMLLoader.load(App.class.getResource(sceneSelect.getFxml()));
            Scene scene = SceneManager.stage.getScene();
            if (scene == null) {
                scene = new Scene(root, 600.0, 600.0);
                SceneManager.stage.setScene(scene);
            }
            else {
                SceneManager.stage.getScene().setRoot(root);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void fadeOut(final SceneManager sceneSelect, final Duration duration) {
        final Node root = (Node)SceneManager.stage.getScene().getRoot();
        final FadeTransition fadeTransition = new FadeTransition(duration, root);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(event -> show(sceneSelect));
        fadeTransition.play();
    }
    
    public static void fadeOut(final Node node, final Duration duration) {
        final FadeTransition fadeTransition = new FadeTransition(duration, node);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }
    
    public static void fadeIn(final Node rootNode, final Duration duration) {
        rootNode.setOpacity(0.0);
        final FadeTransition fadeTransition = new FadeTransition(duration, rootNode);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    
    public static Stage getStage() {
        return SceneManager.stage;
    }
}