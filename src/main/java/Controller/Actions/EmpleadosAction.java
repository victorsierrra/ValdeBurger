package Controller.Actions;

import Model.Empleados;
import Model.EmpleadosDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class EmpleadosAction implements IAction{
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
        EmpleadosDao empleadosDao = new EmpleadosDao();  // Create an instance
        ArrayList<Empleados> empleados = empleadosDao.findAll(null);  // Call the instance method
        return Empleados.toArrayJSon(empleados);
    }
}
