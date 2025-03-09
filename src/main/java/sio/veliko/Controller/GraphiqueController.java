package sio.veliko.Controller;

import sio.veliko.Models.Reservation;
import sio.veliko.Models.User;
import sio.veliko.Services.GraphiqueService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphiqueController {

    private GraphiqueService graphiqueService;

    public GraphiqueController(){
        graphiqueService = new GraphiqueService();
    }
    public HashMap<String,Integer>getDataGraphique1() throws SQLException {
        return graphiqueService.getDatasGraphique1();
    }

    public HashMap<String,Integer> getUserLesPlusActifs()throws SQLException {
        return graphiqueService.getUserLesPlusActifs();
    }
    public int nbTotalStations() throws SQLException {
        return graphiqueService.nbTotalStations();
    }
    public int nbTotalCapacite() throws SQLException {
        return graphiqueService.nbTotalCapacite();
    }
    public HashMap<String,Integer> getDataGraph2() throws SQLException {
        return graphiqueService.getDataGraph2();
    }
    public int nbTotalMecanique() throws SQLException {
        return graphiqueService.nbTotalMecanique();
    }
    public int nbTotalElectrique() throws SQLException {
        return graphiqueService.nbTotalElectrique();
    }

    public HashMap<String, Integer> getDataGraph3() {
        return graphiqueService.getDataGraph3();
    }

    public ArrayList<Reservation>getNbResa() throws SQLException {
        return graphiqueService.getNbResa();
    }

    public int getNbTotalReservations() {
        return graphiqueService.getNbTotalReservations();
    }

    public int getLesUser() {
        return graphiqueService.getLesUser();
    }

    public ArrayList<User> getUsersMemeStationDep() throws SQLException {
        return graphiqueService.getUsersMemeStationDep();
    }

}
