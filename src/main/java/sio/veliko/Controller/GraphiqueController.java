package sio.veliko.Controller;

import sio.veliko.Services.GraphiqueService;

import java.sql.SQLException;
import java.util.HashMap;

public class GraphiqueController {

    private GraphiqueService graphiqueService;

    public GraphiqueController(){
        graphiqueService = new GraphiqueService();
    }
    public HashMap<String,Integer>getDataGraphique1() throws SQLException {
        return graphiqueService.getDatasGraphique1();
    }
}
