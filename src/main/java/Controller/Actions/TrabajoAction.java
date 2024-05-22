package Controller.Actions;


import Model.Trabajo;
import Model.TrabajoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class TrabajoAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn = "";
        switch (action) {
            case "FIND_FIRST":
                // strReturn
                break;
            case "FIND_ALL":

                strReturn = findAll();
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        TrabajoDao trabajoDao = new TrabajoDao();  // Create an instance
        ArrayList<Trabajo> trabajos = trabajoDao.findAll(null);  // Call the instance method
        return Trabajo.toArrayJSon(trabajos);
    }
}
