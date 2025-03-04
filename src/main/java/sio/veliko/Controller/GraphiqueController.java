package sio.veliko.Controller;

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

    public ArrayList<User> getLesMeilleursUsers() throws SQLException {
        return graphiqueService.getLesMeilleursUsers();
    }
}
