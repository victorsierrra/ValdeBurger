package Controller.Actions;

import Controller.Controller;
import Model.Empleados;
import Model.EmpleadosDao;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class EmpleadosAction implements IAction{
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
            case "ADD":
                Empleados e = gson.fromJson(parser.parse(Controller.getBody(request)), Empleados.class);
                return addEmpleado(e);
            case "DELETE":
                strReturn = delete(request);
                break;
            case "UPDATE":
                Empleados em = gson.fromJson(parser.parse(Controller.getBody(request)), Empleados.class);
                return updateEmpleado(em);
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }
    private String updateEmpleado(Empleados e) {
        EmpleadosDao empleadosDao = new EmpleadosDao();
        Integer iEmpleado = empleadosDao.update(e);
        return iEmpleado.toString();
    }
    /*
    private String update(HttpServletRequest request) {
        String idEmpleado = request.getParameter("ID_EMPLEADO");
        String idDepartamento = request.getParameter("ID_DEPARTAMENTO");
        String idTrabajo = request.getParameter("ID_TRABAJO");
        String nombreEmpleado = request.getParameter("NOMBRE");
        String apellidosEmpleados = request.getParameter("APELLIDOS");
        String fechaNacimiento = request.getParameter("FECHA_NACIMIENTO");
        String telefono = request.getParameter("TELEFONO");
        String correo = request.getParameter("CORREO");
        String contraseña = request.getParameter("CONTRASENA");
        String DNI = request.getParameter("DNI");
        String fechaContratacion = request.getParameter("FECHA_CONTRATACION");
        String salario = request.getParameter("SALARIO");

        if (idEmpleado != null && !idEmpleado.isEmpty() &&
                idDepartamento != null && !idDepartamento.isEmpty() &&
                idTrabajo != null && !idTrabajo.isEmpty() &&
                nombreEmpleado != null && !nombreEmpleado.isEmpty() &&
                apellidosEmpleados != null && !apellidosEmpleados.isEmpty() &&
                fechaNacimiento != null && !fechaNacimiento.isEmpty() &&
                telefono != null && !telefono.isEmpty() &&
                correo != null && !correo.isEmpty() &&
                contraseña != null && !contraseña.isEmpty() &&
                DNI != null && !DNI.isEmpty() &&
                fechaContratacion != null && !fechaContratacion.isEmpty() &&
                salario != null && !salario.isEmpty()) {
            try {


                Empleados empleados = new Empleados();
                int iIdEmpleado = Integer.parseInt(idEmpleado);
                int iTelefono = Integer.parseInt(telefono);
                int iSalario = Integer.parseInt(salario);

                empleados.setIdEmpleado(iIdEmpleado);
                empleados.setDepartamento(idDepartamento);
                empleados.setTrabajo(idTrabajo);
                empleados.setNombre(nombreEmpleado);
                empleados.setApellido(apellidosEmpleados);
                empleados.setFechaNac(fechaNacimiento);
                empleados.setTelefono(iTelefono);
                empleados.setCorreo(correo);
                empleados.setContrasena(contraseña);
                empleados.setDNI(DNI);
                empleados.setFechaCont(fechaContratacion);
                empleados.setSalario(iSalario);




                EmpleadosDao empleadosDao = new EmpleadosDao();
                int result = empleadosDao.update(empleados);
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
    }*/
    private String addEmpleado(Empleados e) {
        EmpleadosDao empleadosDao = new EmpleadosDao();
        Integer iEmpleado = empleadosDao.add(e);
        return iEmpleado.toString();
    }

    /*

    private String add(HttpServletRequest request) {
        String idEmpleado = request.getParameter("ID_EMPLEADO");
        String idDepartamento = request.getParameter("ID_DEPARTAMENTO");
        String idTrabajo = request.getParameter("ID_TRABAJO");
        String nombreEmpleado = request.getParameter("NOMBRE");
        String apellidosEmpleados = request.getParameter("APELLIDOS");
        String fechaNacimiento = request.getParameter("FECHA_NACIMIENTO");
        String telefono = request.getParameter("TELEFONO");
        String correo = request.getParameter("CORREO");
        String contraseña = request.getParameter("CONTRASENA");
        String DNI = request.getParameter("DNI");
        String fechaContratacion = request.getParameter("FECHA_CONTRATACION");
        String salario = request.getParameter("SALARIO");

        if (idEmpleado != null && !idEmpleado.isEmpty() &&
                idDepartamento != null && !idDepartamento.isEmpty() &&
                idTrabajo != null && !idTrabajo.isEmpty() &&
                nombreEmpleado != null && !nombreEmpleado.isEmpty() &&
                apellidosEmpleados != null && !apellidosEmpleados.isEmpty() &&
                fechaNacimiento != null && !fechaNacimiento.isEmpty() &&
                telefono != null && !telefono.isEmpty() &&
                correo != null && !correo.isEmpty() &&
                contraseña != null && !contraseña.isEmpty() &&
                DNI != null && !DNI.isEmpty() &&
                fechaContratacion != null && !fechaContratacion.isEmpty() &&
                salario != null && !salario.isEmpty()) {
            try {


                Empleados empleados = new Empleados();
                int iIdEmpleado = Integer.parseInt(idEmpleado);
                int iTelefono = Integer.parseInt(telefono);
                int iSalario = Integer.parseInt(salario);

                empleados.setIdEmpleado(iIdEmpleado);
                empleados.setDepartamento(idDepartamento);
                empleados.setTrabajo(idTrabajo);
                empleados.setNombre(nombreEmpleado);
                empleados.setApellido(apellidosEmpleados);
                empleados.setFechaNac(fechaNacimiento);
                empleados.setTelefono(iTelefono);
                empleados.setCorreo(correo);
                empleados.setContrasena(contraseña);
                empleados.setDNI(DNI);
                empleados.setFechaCont(fechaContratacion);
                empleados.setSalario(iSalario);




                EmpleadosDao empleadosDao = new EmpleadosDao();
                int result = empleadosDao.add(empleados);
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
    }*/
    private String delete(HttpServletRequest request) {
        String id = request.getParameter("ID_EMPLEADO");
        if (id != null && !id.isEmpty()) {
            EmpleadosDao empleadosDao = new EmpleadosDao();
            int result = empleadosDao.delete(id);
            if (result == 0) {
                return "Borrado con éxito.";
            } else {
                return "No se pudo borrar.";
            }
        } else {
            return "ID no proporcionado.";
        }
    }

    private String findAll() {
        EmpleadosDao empleadosDao = new EmpleadosDao();  // Create an instance
        ArrayList<Empleados> empleados = empleadosDao.findAll(null);  // Call the instance method
        return Empleados.toArrayJSon(empleados);
    }
}
