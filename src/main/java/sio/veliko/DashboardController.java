package sio.veliko;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sio.veliko.Controller.GraphiqueController;
import sio.veliko.Tools.DataSourceProvider;

import java.lang.classfile.Label;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    DataSourceProvider cnx;
    GraphiqueController graphiqueController;

    HashMap<String,Integer> datasGraphique;
    XYChart.Series<String,Integer> serieGraph1;

    private int currentPage = 0;
    private AnchorPane[] pages;

    @javafx.fxml.FXML
    private ImageView imgLogo;
    @javafx.fxml.FXML
    private BarChart graph1;
    @javafx.fxml.FXML
    private AnchorPane apGraph1;
    @javafx.fxml.FXML
    private TableColumn tcPrenomMeilleursUsers;
    @javafx.fxml.FXML
    private TableColumn tcNomMeilleursUsers;
    @javafx.fxml.FXML
    private AnchorPane apTv;
    @javafx.fxml.FXML
    private TableView tvMeilleursUsers;
    @javafx.fxml.FXML
    private TableColumn tcNbReservations;
    @javafx.fxml.FXML
    private TableView tv2;
    @javafx.fxml.FXML
    private AnchorPane ap3;
    @javafx.fxml.FXML
    private Button btnDroite;
    @javafx.fxml.FXML
    private Button btnGauche;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        graphiqueController = new GraphiqueController();
        try {
            datasGraphique =  graphiqueController.getDataGraphique1();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            afficherStat1();
            afficherStat2();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        pages = new AnchorPane[]{apGraph1, apTv,ap3};

        // Afficher la première page
        showPage(0);

        // Ajouter les événements aux boutons
        btnDroite.setOnAction(e -> changePage(1));
        btnGauche.setOnAction(e -> changePage(-1));

    }

    private void changePage(int direction) {
        int newPage = currentPage + direction;
        if (newPage >= 0 && newPage < pages.length) {
            showPage(newPage);
        }
    }

    private void showPage(int pageIndex) {
        currentPage = pageIndex;

        // Afficher la page actuelle et masquer l'autre
        for (int i = 0; i < pages.length; i++) {
            pages[i].setVisible(i == currentPage);
        }

    }

    public void  afficherStat1() throws SQLException {

        graph1.getData().clear();
        serieGraph1 = new XYChart.Series<>();
        serieGraph1.setName("Nom des stations");
        for(String valeur : graphiqueController.getDataGraphique1().keySet())
        {
            serieGraph1.getData().add(new XYChart.Data<>(valeur,graphiqueController.getDataGraphique1().get(valeur)));
        }
        graph1.getData().add(serieGraph1);

        for (XYChart.Data<String,Integer> entry : serieGraph1.getData()) {
            Tooltip t = new Tooltip(entry.getYValue() + " : " + entry.getXValue());
            t.setStyle("-fx-background-color:#3D9ADA");
            Tooltip.install(entry.getNode(), t);
        }

    }

    public void afficherStat2() throws SQLException {

        tcNomMeilleursUsers.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        tcPrenomMeilleursUsers.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
        tcNbReservations.setCellValueFactory(new PropertyValueFactory<>("nbResa"));
        tvMeilleursUsers.setItems(FXCollections.observableArrayList(graphiqueController.getLesMeilleursUsers()));
    }
}
