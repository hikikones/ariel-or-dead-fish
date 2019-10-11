package exercise9.controller;

import javafx.scene.Node;
import exercise9.model.SceneManager;
import javafx.util.Duration;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class WelcomeScreenController
{
    public static String chosenLevel;
    @FXML
    private RadioButton yesToggle;
    @FXML
    private RadioButton dannyLevel;
    @FXML
    private RadioButton erlendLevel;
    @FXML
    private ToggleGroup levelToggle;
    @FXML
    private VBox root;
    
    @FXML
    private void playButton() {
        this.startGame();
    }
    
    @FXML
    public void initialize() {
        SceneManager.fadeIn((Node)this.root, Duration.seconds(1.0));
        this.dannyLevel.setUserData((Object)"levelDanny.txt");
        this.erlendLevel.setUserData((Object)"levelErlend.txt");
    }
    
    private void startGame() {
        this.root.setDisable(true);
        if (this.yesToggle.isSelected()) {
            GameScreenController.darkMode = true;
        }
        else {
            GameScreenController.darkMode = false;
        }
        WelcomeScreenController.chosenLevel = this.levelToggle.getSelectedToggle().getUserData().toString();
        SceneManager.fadeOut(SceneManager.GAME, Duration.seconds(1.0));
    }
}