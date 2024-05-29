package Controller.Actions;

import Controller.Controller;
import Model.*;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class ProductoAction  implements IAction {
    private Gson gson = new Gson();
    private JsonParser parser = new JsonParser();
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
            case "UPDATE":
                Producto p = gson.fromJson(parser.parse(Controller.getBody(request)), Producto.class);
                return update(p);
            case "ADD":
                Producto x = gson.fromJson(parser.parse(Controller.getBody(request)), Producto.class);
                return AddProducto(x);
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
    private String AddProducto(Producto p) {
        ProductoDao productoDao = new ProductoDao();
        Integer iProducto = productoDao.add(p);
        return iProducto.toString();
    }
    private String update(Producto p) {
        ProductoDao productoDao = new ProductoDao();
        Integer iProducto = productoDao.update(p);
        return iProducto.toString();
    }

    private String delete(HttpServletRequest request) {
        String id = request.getParameter("ID_PRODUCTO");
        if (id != null && !id.isEmpty()) {
            ProductoDao productoDao = new ProductoDao();
            int result = productoDao.delete(id);
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

