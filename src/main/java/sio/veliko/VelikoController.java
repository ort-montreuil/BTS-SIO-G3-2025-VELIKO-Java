package sio.veliko;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.veliko.Controller.UserController;
import sio.veliko.Tools.DataSourceProvider;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VelikoController implements Initializable {

    DataSourceProvider cnx;

    @FXML
    private ImageView imgLogo;
    @FXML
    private TextField txtLogin;
    @FXML
    private Button btnConnexion;
    private UserController userController;
    @FXML
    private AnchorPane apConnexion;
    @FXML
    private Label hvNavbar;
    @FXML
    private PasswordField txtMotdepasse;


    @FXML
    public void btnConnexionClicked(Event event) {
        if(txtLogin.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de connexion");
            alert.setContentText("Saisir votre email");
            alert.showAndWait();
        }

        else if (txtMotdepasse.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de connexion");
            alert.setContentText("Saisir votre mot de passe");
            alert.showAndWait();
        }
        else
        {
            try {
                if (userController.verifierIdentifiants(txtLogin.getText(), txtMotdepasse.getText())) {
                    FXMLLoader fxmlLoader = new FXMLLoader(VelikoApplication.class.getResource("sommaire-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Hello");
                    stage.setScene(scene);
                    stage.show();
                    ((Stage) btnConnexion.getScene().getWindow()).close();
                    System.out.println("c'est bon");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Erreur de connexion");
                    alert.setContentText("Pseudo ou mot de passe incorrect");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cnx = new DataSourceProvider();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        userController = new UserController();


    }
}