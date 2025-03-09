package sio.veliko.Repositories;

import sio.veliko.Models.Reservation;
import sio.veliko.Models.User;
import sio.veliko.Tools.DataSourceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphiqueRepository  {

    private Connection connection;

    public GraphiqueRepository() {
        this.connection = DataSourceProvider.getCnx();
    }

    // les stations avec le plus grands emplacements
    public HashMap<String,Integer> getDatasGraphique1() throws SQLException {
        HashMap<String,Integer> datas = new HashMap<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, capacity FROM station ORDER BY capacity DESC LIMIT 5; "
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            datas.put(resultSet.getString("name"), resultSet.getInt("capacity"));
        }
        preparedStatement.close();
        resultSet.close();
        return datas;
    }


    // les users les plus actifs
    public HashMap<String,Integer> getUserLesPlusActifs() throws SQLException {
        HashMap<String,Integer>lesPlusActifs = new HashMap<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT user.email, COUNT(reservation.id) AS nb_reservations FROM reservation JOIN user ON reservation.id_user_id = user.id GROUP BY user.email ORDER BY nb_reservations DESC LIMIT 5; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            lesPlusActifs.put(resultSet.getString(1),resultSet.getInt(2));
        }
        preparedStatement.close();
        resultSet.close();

        return lesPlusActifs;
    }

    // nb total de stations
    public int nbTotalStations() throws SQLException {
        int total=0 ;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT Count(*) as totalStations FROM `station`; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            total = resultSet.getInt("totalStations");
        }
        preparedStatement.close();
        resultSet.close();
        return total;
    }

    // nb total de capacité/emplacements
    public int nbTotalCapacite() throws SQLException {
        int total=0 ;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(capacity) as totalCapacite FROM `station`; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            total = resultSet.getInt("totalCapacite");
        }
        preparedStatement.close();
        resultSet.close();
        return total;
    }

    // type de velos le plus utilisé
    public HashMap<String,Integer> getDataGraph2() throws SQLException {
        HashMap<String,Integer> datas = new HashMap<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT type_velo , COUNT(type_velo) as nb FROM reservation GROUP by type_velo ORDER BY nb DESC; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            datas.put(resultSet.getString("type_velo"),resultSet.getInt("nb"));
        }
        preparedStatement.close();
        resultSet.close();

        return datas;
    }

    // nb total velos mecanique
    public int nbTotalMecanique() throws SQLException {
        int total = 0 ;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(type_velo) as total FROM reservation WHERE type_velo = \"mecanique\"; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            total = resultSet.getInt("total");
        }
        preparedStatement.close();
        resultSet.close();
        return total;
    }

    // nb total velos electrique
    public int nbTotalElectrique() throws SQLException {
        int total = 0 ;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(type_velo) as total FROM reservation WHERE type_velo = \"electrique\"; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            total = resultSet.getInt("total");
        }
        preparedStatement.close();
        resultSet.close();
        return total;
    }

    public HashMap<String, Integer> getDatasGraph3() {
        HashMap<String, Integer> datas = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT s.name, COUNT(r.id) AS nb_reservations FROM station s \n" +
                        "                        JOIN reservation r ON s.station_id = r.id  \n" +
                        "                        GROUP BY s.station_id ORDER BY nb_reservations DESC LIMIT 10")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    datas.put(resultSet.getString("name"), resultSet.getInt("nb_reservations"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    // compter le nb de reservations
    public int getNbTotalReservations() {
        int nbStations = 0;
        try (PreparedStatement ps = connection.prepareStatement("SELECT Count(reservation.id) as totalReservation FROM reservation; ");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                nbStations = rs.getInt("totalReservation");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nbStations;
    }

    // nb user
    public int getLesUser() {
        int nbUser = 0;
        try (PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM user");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                nbUser = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nbUser;
    }

    // nb total de reservations par date
    public ArrayList<Reservation> getNbResa() {
        ArrayList<Reservation> tableauDate = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT date_reservation, COUNT(id) AS total_reservations " +
                        "FROM reservation " +
                        "GROUP BY date_reservation " +
                        "ORDER BY date_reservation DESC");
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                tableauDate.add(new Reservation(rs.getDate("date_reservation"), rs.getInt("total_reservations")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableauDate;
    }

    public ArrayList<User> getUsersMemeStationDep() throws SQLException {
        ArrayList<User> lesUsers = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.nom, u.prenom, r.station_depart, COUNT(r.id) AS nbResa FROM user u JOIN reservation r ON u.id = r.id_user_id GROUP BY u.id, r.station_depart HAVING COUNT(r.id) > 1 ORDER BY nbResa DESC; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            lesUsers.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4)));
        }
        return lesUsers;
    }





}
