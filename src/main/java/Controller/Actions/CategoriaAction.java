package Controller.Actions;

import Model.Categoria;
import Model.CategoriaDao;
import Model.Promocion;
import Model.PromocionDao;

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
            case "ADD":
                strReturn = add(request);
                break;
            case "UPDATE":
                strReturn = update(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }
    private String update(HttpServletRequest request){
        String idCategoria = request.getParameter("ID_CATEGORIA");
        String nombreCategoria = request.getParameter("NOMBRE");

        if (idCategoria != null && !idCategoria.isEmpty() && nombreCategoria != null && !nombreCategoria.isEmpty()) {
            try {

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(idCategoria);
                categoria.setNombreCategoria(nombreCategoria);

                CategoriaDao categoriaDao = new CategoriaDao();
                int result = categoriaDao.update(categoria);
                if (result > 0) {
                    return "Actualiazado con éxito.";
                } else {
                    return "No se pudo actualizar.";
                }
            } catch (NumberFormatException e) {
                return "ID o descuento no válidos.";
            }
        } else {
            return "Todos los campos son obligatorios.";
        }
    }
    private String add(HttpServletRequest request) {
        String idCategoria = request.getParameter("ID_CATEGORIA");
        String nombreCategoria = request.getParameter("NOMBRE");

        if (idCategoria != null && !idCategoria.isEmpty() &&
                nombreCategoria != null && !nombreCategoria.isEmpty()) {
            try {

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(idCategoria);
                categoria.setNombreCategoria(nombreCategoria);

                CategoriaDao categoriaDao = new CategoriaDao();
                int result = categoriaDao.add(categoria);
                if (result > 0) {
                    return "Añadido con éxito.";
                } else {
                    return "No se pudo añadir.";
                }
            } catch (NumberFormatException e) {
                return "ID o descuento no válidos.";
            }
        } else {
            return "Todos los campos son obligatorios.";
        }
    }

    private String findAll() {
        CategoriaDao categoriaDao = new CategoriaDao();  // Create an instance
        ArrayList<Categoria> categorias = categoriaDao.findAll(null);  // Call the instance method
        return Categoria.toArrayJSon(categorias);
    }
    private String delete(HttpServletRequest request) {
        String id = request.getParameter("ID_CATEGORIA");
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
