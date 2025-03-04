package sio.veliko.Services;

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

    public ArrayList<User> getLesMeilleursUsers() throws SQLException {
        return graphiqueRepository.getLesMeilleursUsers();
    }
}

