import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ToDoManagerPage {
    public void build(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        TextField toDoTextfield = new TextField();
        Button addButton = new Button("Adauga");
        gridPane.add(toDoTextfield, 0,0);
        gridPane.add(addButton, 1, 0);
        ListView<String> todos = new ListView<>();
        try {
            Statement stGetToDos = Storage.connection.createStatement();
            String query = String.format("SELECT * FROM todo WHERE user_id = %s", Storage.userId);
            ResultSet resultSet = stGetToDos.executeQuery(query);
            while(resultSet.next()) {
                String toDoCurrent = resultSet.getString("description");
                todos.getItems().add(toDoCurrent);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Button delete = new Button("Stergere");
        gridPane.add(todos,0, 1);
        gridPane.add(delete, 1, 1);

        addButton.setOnMouseClicked(e -> {
                try {
                    Statement st = Storage.connection.createStatement();
                    String query = String.format("INSERT INTO todo VALUES(null, %s , '%s')", Storage.userId, toDoTextfield.getText());
                    st.executeUpdate(query);

                    todos.getItems().add(toDoTextfield.getText());
                    toDoTextfield.setText("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        });
        todos.getSelectionModel().getSelectedItems();
        delete.setOnMouseClicked(e -> {
            try {
                Statement st = Storage.connection.createStatement();
                String selected = todos.getSelectionModel().getSelectedItem();
                String query = String.format("DELETE FROM todo WHERE description = '%s'", selected);
                st.executeUpdate(query);
                todos.getItems().remove(selected);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
        Scene scene = new Scene(gridPane, 600,600);
        stage.setScene(scene);
        stage.show();
    }
}
