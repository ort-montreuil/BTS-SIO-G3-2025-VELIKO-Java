package sio.veliko;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sio.veliko.Controller.UserController;
import sio.veliko.Models.User;
import sio.veliko.Tools.DataSourceProvider;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestionUserController implements Initializable {

    UserController userController;

    @javafx.fxml.FXML
    private Button btnBloquer;
    @javafx.fxml.FXML
    private TableView<User> tvUser;
    @javafx.fxml.FXML
    private Button btnSupprimer;
    @javafx.fxml.FXML
    private TableColumn<User, Integer> tcId;
    @javafx.fxml.FXML
    private TableColumn<User, String> tcNom;
    @javafx.fxml.FXML
    private TableColumn<User, String> tcPrenom;
    @javafx.fxml.FXML
    private TableColumn<User, String> tcEmail;
    @FXML
    private Button btnModifier;
    @javafx.fxml.FXML
    private TableColumn<User,String> tcBloquer;
    @FXML
    private AnchorPane apUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            DataSourceProvider cnx = new DataSourceProvider();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        userController = new UserController();

        tcId.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        tcNom.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        tcPrenom.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("emailUser"));
        tcBloquer.setCellValueFactory(new PropertyValueFactory<>("isBlocked"));


        try {
            tvUser.setItems(FXCollections.observableArrayList(userController.getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @javafx.fxml.FXML
    public void btnBloquerClicked(Event event) throws SQLException {

        if (tvUser.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner un utilisateur");
            alert.showAndWait();
        }
        else {

            User userSelectionner = tvUser.getSelectionModel().getSelectedItem();
            int idUser = userSelectionner.getIdUser();


            if (userSelectionner.getIsBlocked().equals("1")) {

                userController.debloquerUser(idUser);

                // Afficher une confirmation de réussite
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("L'utilisateur a été débloqué avec succès.");
                successAlert.showAndWait();

                btnBloquer.setText("Bloquer");

            }
            else {

                userController.bloquerUser(idUser);

                // Afficher une confirmation de réussite
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("L'utilisateur a été bloqué avec succès.");
                successAlert.showAndWait();

                btnBloquer.setText("Débloquer");
            }
            tvUser.setItems(FXCollections.observableArrayList(userController.getAll()));
            tvUser.refresh();


        }


    }





    @javafx.fxml.FXML
    public void btnSupprimerClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnModifierClicked(Event event) {
    }

    @Deprecated
    public void btnEnregistrerClicked(Event event) {
    }
}