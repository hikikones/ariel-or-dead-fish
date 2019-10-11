package exercise9;

import javafx.collections.FXCollections;
import exercise9.model.SceneManager;
import javafx.stage.Stage;
import exercise9.model.Person;
import javafx.collections.ObservableList;
import javafx.application.Application;

public class App extends Application
{
    public static ObservableList<Person> peopleMysteryIsland;
    public static ObservableList<Person> peopleMazeHell;
    
    public void start(final Stage primaryStage) {
        SceneManager.bind(primaryStage);
        primaryStage.setTitle("Simple JavaFX Tile Game");
        SceneManager.show(SceneManager.START);
        primaryStage.show();
    }
    
    public static void main(final String[] args) {
        launch(args);
    }
    
    static {
        App.peopleMysteryIsland = (ObservableList<Person>)FXCollections.observableArrayList(new Person[] { new Person("Isabella", 156), new Person("Ethan", 244), new Person("Michael", 301) });
        App.peopleMazeHell = (ObservableList<Person>)FXCollections.observableArrayList(new Person[] { new Person("Isabella", 1337), new Person("Ethan", 2048), new Person("Michael", 3981) });
    }
}







































// package exercise9;

// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// import java.io.IOException;

// /**
//  * JavaFX App
//  */
// public class App extends Application {

//     private static Scene scene;

//     @Override
//     public void start(Stage stage) throws IOException {
//         scene = new Scene(loadFXML("primary"));
//         stage.setScene(scene);
//         stage.show();
//     }

//     static void setRoot(String fxml) throws IOException {
//         scene.setRoot(loadFXML(fxml));
//     }

//     private static Parent loadFXML(String fxml) throws IOException {
//         FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//         return fxmlLoader.load();
//     }

//     public static void main(String[] args) {
//         launch();
//     }

// }