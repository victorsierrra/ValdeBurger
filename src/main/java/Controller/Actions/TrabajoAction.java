package Controller.Actions;


import Model.*;

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
            case "UPDATE":
                strReturn = update(request);
                break;
            case "ADD":
                strReturn = add(request);
                break;
            case "DELETE":
                strReturn = delete(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }
    private String delete(HttpServletRequest request) {
        String id = request.getParameter("ID_TRABAJO");
        if (id != null && !id.isEmpty()) {
            TrabajoDao trabajoDao = new TrabajoDao();
            int result = trabajoDao.delete(id);
            if (result == 0) {
                return "Borrado con éxito.";
            } else {
                return "No se pudo borrar.";
            }
        } else {
            return "ID no proporcionado.";
        }
    }
    private String update(HttpServletRequest request){
        String idTrabajo = request.getParameter("ID_TRABAJO");
        String idDepartamento = request.getParameter("ID_DEPARTAMENTO");
        String tituloTrabajo = request.getParameter("TITULO_TRABAJO");

        if (idDepartamento != null && !idDepartamento.isEmpty() && idTrabajo != null && !idTrabajo.isEmpty()
        && tituloTrabajo!=null && !tituloTrabajo.isEmpty()) {
            try {

                Trabajo trabajo = new Trabajo();
                trabajo.setIdTrabajo(idTrabajo);
                trabajo.setDepartamento(idDepartamento);
                trabajo.setTituloTrabajo(tituloTrabajo);

                TrabajoDao trabajoDao = new TrabajoDao();
                int result = trabajoDao.update(trabajo);
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
        String idTrabajo = request.getParameter("ID_TRABAJO");
        String idDepartamento = request.getParameter("ID_DEPARTAMENTO");
        String tituloTrabajo = request.getParameter("TITULO_TRABAJO");

        if (idDepartamento != null && !idDepartamento.isEmpty() && idTrabajo != null && !idTrabajo.isEmpty()
                && tituloTrabajo!=null && !tituloTrabajo.isEmpty()) {
            try {

                Trabajo trabajo = new Trabajo();
                trabajo.setIdTrabajo(idTrabajo);
                trabajo.setDepartamento(idDepartamento);
                trabajo.setTituloTrabajo(tituloTrabajo);

                TrabajoDao trabajoDao = new TrabajoDao();
                int result = trabajoDao.add(trabajo);
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
        TrabajoDao trabajoDao = new TrabajoDao();  // Create an instance
        ArrayList<Trabajo> trabajos = trabajoDao.findAll(null);  // Call the instance method
        return Trabajo.toArrayJSon(trabajos);
    }
}
