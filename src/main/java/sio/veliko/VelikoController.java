package sio.veliko;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sio.veliko.Tools.DataSourceProvider;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VelikoController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private TextField txtLogin;
    @FXML
    private Label txtErreur;
    @FXML
     private TextField txtMotDePasse;
    @FXML
    private Button btnConnexion;


    @FXML
    public void btnConnexionClicked(Event event) {
        if(txtLogin.getText().isEmpty())
        {
            txtErreur.setText("Saisir votre login");
        }

        else if (txtMotDePasse.getText().isEmpty())
        {
            txtErreur.setText("saisir votre mdp");
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(VelikoApplication.class.getResource("sommaire-view.fxml"));
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
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}