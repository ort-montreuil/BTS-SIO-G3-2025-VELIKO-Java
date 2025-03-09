package sio.veliko.Repositories;

import sio.veliko.Models.Reservation;
import sio.veliko.Models.User;
import sio.veliko.Tools.DataSourceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphiqueRepository {

    private Connection connection;

    public GraphiqueRepository() {
        this.connection = DataSourceProvider.getCnx();
    }

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


    public ArrayList<User> getLesMeilleursUsers() throws SQLException {
        ArrayList<User> lesMeilleurs = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("call lesMeilleursClients");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            lesMeilleurs.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
        }
        preparedStatement.close();
        resultSet.close();

        return lesMeilleurs;
    }

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
    public HashMap<String, Integer> getNbReservations() {
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

    // nb stations
    public int getLesStations() {
        int nbStations = 0;
        try (PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM station");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                nbStations = rs.getInt(1);
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

    // date_resa par date
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

    // nb user actif
    public HashMap<String, Integer> getUserPlusActif() {
        HashMap<String, Integer> datas = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT reservation.id, user.email, reservation.date_reservation, reservation.type_velo, " +
                        "reservation.station_id_depart, reservation.station_id_arrivee " +
                        "FROM reservation JOIN user ON reservation.id_user = user.id limit 5");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Modify accordingly if you need user-specific stats
                datas.put(resultSet.getString("email"), resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }




}
