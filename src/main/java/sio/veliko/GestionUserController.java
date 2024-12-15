package sio.veliko;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sio.veliko.Controller.UserController;
import sio.veliko.Models.User;
import sio.veliko.Tools.DataSourceProvider;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
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
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label hbNavbar;

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
    public void btnSupprimerClicked(Event event) throws SQLException {

            // Vérification si un utilisateur est sélectionné
            if (tvUser.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner un utilisateur.");
                alert.showAndWait();
                return; // Quitte la méthode si aucun utilisateur n'est sélectionné
            }

            // Récupération de l'utilisateur sélectionné
            int idUser = tvUser.getSelectionModel().getSelectedItem().getIdUser();

            // Création d'une alerte de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText("Supprimer l'utilisateur");
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

            // Affichage de l'alerte et gestion de la réponse de l'utilisateur
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Si l'utilisateur confirme la suppression
                userController.supprimerUser(idUser);
                tvUser.getItems().remove(tvUser.getSelectionModel().getSelectedItem());
                // Affiche une alerte de succès après suppression
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("L'utilisateur a été supprimé avec succès.");
                 successAlert.showAndWait();

            } else {
                // Si l'utilisateur annule l'action
                Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION);
                cancelAlert.setTitle("Annulation");
                cancelAlert.setHeaderText(null);
                cancelAlert.setContentText("La suppression a été annulée.");
                cancelAlert.showAndWait();
            }
        }



    @javafx.fxml.FXML
    public void btnModifierClicked(Event event) {
    }

}