package Controller.Actions;

import Model.Categoria;
import Model.CategoriaDao;
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
        String id = request.getParameter("ID_DEPARTAMENTO");
        if (id != null && !id.isEmpty()) {
            DepartamentoDao departamentoDao = new DepartamentoDao();
            int result = departamentoDao.delete(id);
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
        String idDepartamento = request.getParameter("ID_DEPARTAMENTO");
        String nombreDepartamento = request.getParameter("NOMBRE_DEPARTAMENTO");

        if (idDepartamento != null && !idDepartamento.isEmpty() && nombreDepartamento != null && !nombreDepartamento.isEmpty()) {
            try {

                Departamentos departamentos = new Departamentos();
                departamentos.setIdDepartamento(idDepartamento);
                departamentos.setNombreDepartamento(nombreDepartamento);

                DepartamentoDao departamentoDao = new DepartamentoDao();
                int result = departamentoDao.update(departamentos);
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
        String idDepartamento = request.getParameter("ID_DEPARTAMENTO");
        String nombreDepartamento = request.getParameter("NOMBRE_DEPARTAMENTO");

        if (idDepartamento != null && !idDepartamento.isEmpty() && nombreDepartamento != null && !nombreDepartamento.isEmpty()) {
            try {
                Departamentos departamentos = new Departamentos();
                departamentos.setIdDepartamento(idDepartamento);
                departamentos.setNombreDepartamento(nombreDepartamento);

                DepartamentoDao departamentoDao = new DepartamentoDao();
                int result = departamentoDao.add(departamentos);
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
        DepartamentoDao departamentoDao = new DepartamentoDao();  // Create an instance
        ArrayList<Departamentos> departamentos = departamentoDao.findAll(null);  // Call the instance method
        return Departamentos.toArrayJSon(departamentos);
    }
}
