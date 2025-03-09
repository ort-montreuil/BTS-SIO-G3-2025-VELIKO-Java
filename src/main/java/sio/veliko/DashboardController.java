package sio.veliko;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sio.veliko.Controller.GraphiqueController;
import sio.veliko.Tools.DataSourceProvider;

import java.io.IOException;
import java.lang.classfile.Label;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    DataSourceProvider cnx;
    GraphiqueController graphiqueController;

    HashMap<String,Integer> datasGraphique;
    XYChart.Series<String,Integer> serieGraph1;
    XYChart.Series<String,Integer> serieGraph2;


    private int currentPage = 0;
    private AnchorPane[] pages;

    @javafx.fxml.FXML
    private ImageView imgLogo;
    @javafx.fxml.FXML
    private BarChart graph1;
    @javafx.fxml.FXML
    private Button btnDroite;
    @javafx.fxml.FXML
    private Button btnGauche;
    @javafx.fxml.FXML
    private TextField txtTotalEmplacements;
    @javafx.fxml.FXML
    private TextField txtnbReservations;
    @javafx.fxml.FXML
    private PieChart graph2;
    @javafx.fxml.FXML
    private TextField txtMecanique;
    @javafx.fxml.FXML
    private TextField txtelectrique;
    @javafx.fxml.FXML
    private TextField txtNbUser;
    @javafx.fxml.FXML
    private TableColumn tcDate;
    @javafx.fxml.FXML
    private TableView tvReservations;
    @javafx.fxml.FXML
    private TableColumn tcNbResa;
    @javafx.fxml.FXML
    private AnchorPane apStat2;
    @javafx.fxml.FXML
    private AnchorPane apStat1;
    @javafx.fxml.FXML
    private AnchorPane apStat5;
    @javafx.fxml.FXML
    private AnchorPane apStat4;
    @javafx.fxml.FXML
    private AnchorPane apStat3;
    @javafx.fxml.FXML
    private AnchorPane apStat6;
    @javafx.fxml.FXML
    private TextField txtNbReservation;
    @javafx.fxml.FXML
    private TableColumn tcNom;
    @javafx.fxml.FXML
    private TableColumn tcPrenom;
    @javafx.fxml.FXML
    private TableColumn tcStationDep;
    @javafx.fxml.FXML
    private TableView tvUsers;
    @javafx.fxml.FXML
    private TableColumn tcNbReserv;
    @javafx.fxml.FXML
    private BarChart graph3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        graphiqueController = new GraphiqueController();
        try {
            datasGraphique =  graphiqueController.getDataGraphique1();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            afficherStat1(); //station
            afficherStat2(); //user
            afficherStat3(); //station
            afficherStat4(); //reservation
            afficherStat5(); //user
            afficherStat6(); //reservation
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        pages = new AnchorPane[]{apStat1,apStat3,apStat2,apStat5,apStat4,apStat6};

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

    // les stations avec le plus grands emplacements (station)
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

    //les users avec le plus de reservations (users)
    public void afficherStat2() throws SQLException {

        graph3.getData().clear();
        serieGraph2 = new XYChart.Series<>();
        serieGraph2.setName("Nom des utilisateurs");
        for(String valeur : graphiqueController.getUserLesPlusActifs().keySet())
        {
            serieGraph2.getData().add(new XYChart.Data<>(valeur,graphiqueController.getUserLesPlusActifs().get(valeur)));
        }
        graph3.getData().add(serieGraph2);

        for (XYChart.Data<String,Integer> entry : serieGraph2.getData()) {
            Tooltip t = new Tooltip(entry.getYValue() + " : " + entry.getXValue());
            t.setStyle("-fx-background-color:#3D9ADA");
            Tooltip.install(entry.getNode(), t);
        }
    }

    // le nb total de stations et de capacité (station)
    public void  afficherStat3() throws SQLException {

       txtnbReservations.setText(String.valueOf(graphiqueController.nbTotalStations()));
       txtTotalEmplacements.setText(String.valueOf(graphiqueController.nbTotalCapacite()));

    }

    // le type de velo le plus utilisé avec leur nb total respective (reservation)
    public void afficherStat4() throws SQLException {

        graph2.getData().clear();

        ObservableList<PieChart.Data> dataGraph2 = FXCollections.observableArrayList();
        datasGraphique = graphiqueController.getDataGraph2();

        for(String nomTypeVelo : datasGraphique.keySet()){
            graph2.getData().add(new PieChart.Data(nomTypeVelo,datasGraphique.get(nomTypeVelo)));
        }
        for (PieChart.Data entry : graph2.getData()) {
            Tooltip t = new Tooltip(entry.getPieValue()+ " : "+entry.getName());
            t.setStyle("-fx-background-color:#3D9ADA");
            Tooltip.install(entry.getNode(), t);
        }

        txtMecanique.setText(String.valueOf(graphiqueController.nbTotalMecanique()));
        txtelectrique.setText(String.valueOf(graphiqueController.nbTotalElectrique()));

    }

    //les users qui ont reserver plusieurs fois dans la meme station (user)
    public void afficherStat5() throws SQLException {

        tcNom.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        tcPrenom.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
        tcStationDep.setCellValueFactory(new PropertyValueFactory<>("stationDepart"));
        tcNbReserv.setCellValueFactory(new PropertyValueFactory<>("nbResa"));
        tvUsers.setItems(FXCollections.observableArrayList(graphiqueController.getUsersMemeStationDep()));


    }

    // nb de reservations par jour avec le nb de user total et nb de stations total (reservation)
    public  void afficherStat6(){
        // Population graph2
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date_resa"));
        tcNbResa.setCellValueFactory(new PropertyValueFactory<>("nbResa"));
        try {
            tvReservations.setItems(FXCollections.observableArrayList(graphiqueController.getNbResa()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // txtNbUser et txtNbStation
        txtNbReservation.setText(String.valueOf(graphiqueController.getNbTotalReservations()));
        txtNbUser.setText(String.valueOf(graphiqueController.getLesUser()));

    }



}
