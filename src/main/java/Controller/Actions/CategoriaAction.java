package Controller.Actions;

import Model.Categoria;
import Model.CategoriaDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class CategoriaAction implements IAction {
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
            case "DELETE":
                strReturn = delete(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        CategoriaDao categoriaDao = new CategoriaDao();  // Create an instance
        ArrayList<Categoria> categorias = categoriaDao.findAll(null);  // Call the instance method
        return Categoria.toArrayJSon(categorias);
    }
    private String delete(HttpServletRequest request) {
        String id = request.getParameter("ID_Categoria");
        if (id != null && !id.isEmpty()) {
            CategoriaDao categoriaDao = new CategoriaDao();
            int result = categoriaDao.delete(id);
            if (result == 0) {
                return "Borrado con éxito.";
            } else {
                return "No se pudo borrar.";
            }
        } else {
            return "ID no proporcionado.";
        }
    }
}
