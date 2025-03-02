package sio.veliko.Services;

import sio.veliko.Repositories.GraphiqueRepository;

import java.sql.SQLException;
import java.util.HashMap;

public class GraphiqueService {

    public GraphiqueRepository dashboardRepository;

    public GraphiqueService(){
        dashboardRepository = new GraphiqueRepository();
    }
    public HashMap<String,Integer> getDatasGraphique1() throws SQLException {
        return dashboardRepository.getDatasGraphique1();
    }
}

