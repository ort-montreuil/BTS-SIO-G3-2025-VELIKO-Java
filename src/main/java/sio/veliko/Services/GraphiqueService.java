package sio.veliko.Services;

import sio.veliko.Models.Reservation;
import sio.veliko.Models.User;
import sio.veliko.Repositories.GraphiqueRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphiqueService {

    public GraphiqueRepository graphiqueRepository;

    public GraphiqueService(){
        graphiqueRepository = new GraphiqueRepository();
    }
    public HashMap<String,Integer> getDatasGraphique1() throws SQLException {
        return graphiqueRepository.getDatasGraphique1();
    }

    public HashMap<String,Integer> getUserLesPlusActifs()throws SQLException {
        return graphiqueRepository.getUserLesPlusActifs();
    }
    public int nbTotalStations() throws SQLException {
        return graphiqueRepository.nbTotalStations();
    }
    public int nbTotalCapacite() throws SQLException {
        return graphiqueRepository.nbTotalCapacite();
    }
    public HashMap<String,Integer> getDataGraph2() throws SQLException {
        return graphiqueRepository.getDataGraph2();
    }
    public int nbTotalMecanique() throws SQLException {
        return graphiqueRepository.nbTotalMecanique();
    }
    public int nbTotalElectrique() throws SQLException {
        return graphiqueRepository.nbTotalElectrique();
    }
    public HashMap<String, Integer> getDataGraph3() {
        return graphiqueRepository.getDatasGraph3();
    }
    public ArrayList<Reservation> getNbResa() throws SQLException {
        return graphiqueRepository.getNbResa();
    }
    public int getNbTotalReservations() {
        return graphiqueRepository.getNbTotalReservations();
    }

    public int getLesUser() {
        return graphiqueRepository.getLesUser();
    }

    public ArrayList<User> getUsersMemeStationDep() throws SQLException {
        return graphiqueRepository.getUsersMemeStationDep();
    }

}

