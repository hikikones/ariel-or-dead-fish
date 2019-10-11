package exercise9.controller;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import java.util.stream.IntStream;
import javafx.scene.Node;
import exercise9.App;
import exercise9.model.SoundManager;
import javafx.util.Duration;
import exercise9.model.SceneManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import exercise9.model.Player;
import exercise9.model.Person;
import javafx.collections.ObservableList;

public class WinnerScreenController
{
    private ObservableList<Person> people;
    private Player player;
    @FXML
    private VBox root;
    @FXML
    private Text steps;
    @FXML
    private Text addPersonDescription;
    @FXML
    private HBox enterNameBox;
    @FXML
    private TextField enterNameField;
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn leastStepsColumn;
    
    @FXML
    private void addButton() {
        if (!this.enterNameField.getText().isEmpty()) {
            this.addPerson();
        }
    }
    
    @FXML
    private void addKeyListener(final KeyEvent event) {
        if (event.getCode().equals((Object)KeyCode.ENTER) && !this.enterNameField.getText().isEmpty()) {
            this.addPerson();
        }
    }
    
    @FXML
    private void yesButton() {
        this.root.setDisable(true);
        SceneManager.fadeOut(SceneManager.START, Duration.seconds(1.0));
        SoundManager.WINNERMUSIC.stop();
    }
    
    @FXML
    private void noButton() {
        SoundManager.play(SoundManager.AWW);
        try {
            Thread.sleep(1400L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    
    @FXML
    public void initialize() {
        this.player = GameScreenController.player;
        final String chosenLevel = WelcomeScreenController.chosenLevel;
        switch (chosenLevel) {
            case "levelDanny.txt": {
                this.people = App.peopleMysteryIsland;
                break;
            }
            case "levelErlend.txt": {
                this.people = App.peopleMazeHell;
                break;
            }
        }
        SceneManager.fadeIn((Node)this.root, Duration.seconds(1.0));
        this.steps.setText(Integer.toString(this.player.getFinalScore()));
        this.tableView.setItems((ObservableList)this.people);
        this.leastStepsColumn.setSortType(TableColumn.SortType.ASCENDING);
        //this.tableView.getSortOrder().add((Object)this.leastStepsColumn);
        this.tableView.getSortOrder().add((TableColumn<Person, ?>) this.leastStepsColumn);
        this.tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    private void addPerson() {
        final String name = this.enterNameField.getText();
        final int index = IntStream.range(0, this.people.size()).filter(i -> name.toLowerCase().equals(((Person)this.people.get(i)).getName().toLowerCase())).findFirst().orElse(-1);
        if (index != -1) {
            final Person person = (Person)this.people.get(index);
            if (person.getAmountOfSteps() > this.player.getFinalScore()) {
                person.setAmountOfSteps(this.player.getFinalScore());
                //this.addPersonDescription.setText(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, name));
                this.addPersonDescription.setText(name);
                this.addPersonDescription.setFill((Paint)Color.BLACK);
            }
            else {
                this.addPersonDescription.setFill((Paint)Color.RED);
                //this.addPersonDescription.setText((person.getAmountOfSteps() == this.player.getFinalScore()) ? invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, name) : invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, name));
                this.addPersonDescription.setText((person.getAmountOfSteps() == this.player.getFinalScore()) ?  name : name);
                SoundManager.play(SoundManager.MEEPO);
            }
        }
        else {
            this.people.add(new Person(this.enterNameField.getText(), this.player.getFinalScore()));
            //this.addPersonDescription.setText(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, name));
            this.addPersonDescription.setText(name);
            this.addPersonDescription.setFill((Paint)Color.BLACK);
        }
        if (!this.addPersonDescription.getFill().equals(Color.RED)) {
            SoundManager.play(SoundManager.BUBBLECLAP);
            this.enterNameBox.setDisable(true);
            this.tableView.refresh();
            this.tableView.sort();
            this.tableView.setFocusTraversable(false);
        }
    }
}