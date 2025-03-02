package sio.veliko;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sio.veliko.Controller.GraphiqueController;
import sio.veliko.Tools.DataSourceProvider;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    DataSourceProvider cnx;
    GraphiqueController graphiqueController;

    HashMap<String,Integer> datasGraphique;
    XYChart.Series<String,Integer> serieGraph1;

    @javafx.fxml.FXML
    private ImageView imgLogo;
    @javafx.fxml.FXML
    private ImageView imgFleche1;
    @javafx.fxml.FXML
    private ImageView imgFleche2;
    @javafx.fxml.FXML
    private BarChart graph1;
    @javafx.fxml.FXML
    private AnchorPane apGraph1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        apGraph1.toFront();
        graphiqueController = new GraphiqueController();
        try {
            datasGraphique =  graphiqueController.getDataGraphique1();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            afficherGraph1();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void  afficherGraph1() throws SQLException {

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
        NumberAxis yAxis = (NumberAxis) graph1.getYAxis();
        yAxis.setLabel("Capacité");

    }
}
