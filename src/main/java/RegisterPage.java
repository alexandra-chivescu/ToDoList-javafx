import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Statement;

public class RegisterPage {
    public void build(Stage s) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(50, 0, 0, 0));
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        Label labelName = new Label("Nume:");
        TextField textFieldName = new TextField();
        gridPane.add(labelName, 0, 0);
        gridPane.add(textFieldName, 1, 0);

        Label labelEmail = new Label("Email:");
        TextField textFieldEmail = new TextField();
        gridPane.add(labelEmail, 0, 1);
        gridPane.add(textFieldEmail, 1, 1);

        Label labelPassword = new Label("Password:");
        TextField textFieldPassword = new TextField();
        gridPane.add(labelPassword, 0, 2);
        gridPane.add(textFieldPassword, 1, 2);

        Button buttonSubmit = new Button("Submit");
        Button buttonBack = new Button("Back");

        buttonBack.setOnMouseClicked(e -> {
            LoginPage loginPage = new LoginPage();
            loginPage.build(s);
        });

        buttonSubmit.setOnMouseClicked(e -> {
            try {
                Statement st = Storage.connection.createStatement();
                String query = String.format("INSERT INTO user VALUES(null, '%s', '%s', '%s')",
                textFieldName.getText(),
                textFieldEmail.getText(),
                textFieldPassword.getText()
                );
                st.executeUpdate(query);
                LoginPage loginPage = new LoginPage();
                loginPage.build(s);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        gridPane.add(buttonBack, 0, 3);
        gridPane.add(buttonSubmit, 1, 3);
        Scene scene = new Scene(gridPane, 600, 600);
        s.setScene(scene);
        s.show();

    }
}
