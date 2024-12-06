package sio.veliko;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {
    @javafx.fxml.FXML
     private WebView wvMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        WebEngine webEngine = wvMap.getEngine();

        URL htmlUrl = getClass().getResource("/HTML/map.html");
        if (htmlUrl != null) {
            webEngine.load(htmlUrl.toExternalForm());
        } else {
            System.err.println("Erreur : Fichier HTML introuvable !");
        }
    }





}
