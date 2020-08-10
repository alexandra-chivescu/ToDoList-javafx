import javafx.application.Application;
import javafx.stage.Stage;

public class ToDoApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Storage.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginPage loginPage = new LoginPage();
        loginPage.build(primaryStage);
    }
}
