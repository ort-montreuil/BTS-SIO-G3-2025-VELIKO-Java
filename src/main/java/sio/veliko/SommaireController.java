package sio.veliko;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.veliko.VelikoApplication;

import java.io.IOException;

public class SommaireController {

    @javafx.fxml.FXML
    private Button btnMap;
    @javafx.fxml.FXML
    private Button btnDashboard;
    @javafx.fxml.FXML
    private Button btnUser;
    @javafx.fxml.FXML
    private AnchorPane apSommaire;
    @javafx.fxml.FXML
    private Label hvNavbar;

    @javafx.fxml.FXML
    public void menuClicked(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnMap) {
            FXMLLoader fxmlLoader = new FXMLLoader(VelikoApplication.class.getResource("map-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Administrateur");
            stage.setScene(scene);
            stage.show();

        }
        if (actionEvent.getSource() == btnUser) {
            FXMLLoader fxmlLoader = new FXMLLoader(VelikoApplication.class.getResource("gestionuser-view.fxml"));
            Scene scene = null;
             try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Administrateur");
            stage.setScene(scene);
            stage.show();

        }
        if (actionEvent.getSource() == btnDashboard) {

        }
    }


}
