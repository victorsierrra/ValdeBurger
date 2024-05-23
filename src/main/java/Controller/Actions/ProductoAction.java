package Controller.Actions;

import Model.Empleados;
import Model.EmpleadosDao;
import Model.Producto;
import Model.ProductoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ProductoAction  implements IAction{
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
        ProductoDao productoDao = new ProductoDao();  // Create an instance
        ArrayList<Producto> productos = productoDao.findAll(null);  // Call the instance method
        return Producto.toArrayJSon(productos);
    }
}

