package Controller.Actions;

import Model.Departamentos;
import Model.DepartamentoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
public class DepartamentoAction implements IAction {
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
        DepartamentoDao departamentoDao = new DepartamentoDao();  // Create an instance
        ArrayList<Departamentos> departamentos = departamentoDao.findAll(null);  // Call the instance method
        return Departamentos.toArrayJSon(departamentos);
    }
}
