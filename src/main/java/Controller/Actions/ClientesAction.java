package Controller.Actions;

import Model.Clientes;
import Model.ClientesDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ClientesAction implements IAction{
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
                strReturn = add(request);
                break;
            case "DELETE":
                strReturn = delete(request);
                break;
            case "UPDATE":
                strReturn = update(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }
    private String update(HttpServletRequest request) {
        String idCliente = request.getParameter("ID_CLIENTE");
        String nombreCliente = request.getParameter("NOMBRE");
        String apellidosCliente = request.getParameter("APELLIDOS");
        String fechaNacimiento = request.getParameter("FECHA_NACIMIENTO");
        String correo = request.getParameter("CORREO");
        String contraseña = request.getParameter("CONTRASENA");


        if (idCliente != null && !idCliente.isEmpty() &&
                nombreCliente != null && !nombreCliente.isEmpty() &&
                apellidosCliente != null && !apellidosCliente.isEmpty() &&
                fechaNacimiento != null && !fechaNacimiento.isEmpty() &&
                correo != null && !correo.isEmpty() &&
                contraseña != null && !contraseña.isEmpty()) {
            try {


                Clientes clientes = new Clientes();

                clientes.set_idCliente(idCliente);
                clientes.setNombre(nombreCliente);
                clientes.setApellidos(apellidosCliente);
                clientes.setFecha_Nacimiento(correo);
                clientes.setContrasena(contraseña);

                ClientesDao clientesDao = new ClientesDao();
                int result = clientesDao.update(clientes);

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
    private String add(HttpServletRequest request) {
        String idCliente = request.getParameter("ID_CLIENTE");
        String nombreCliente = request.getParameter("NOMBRE");
        String apellidosCliente = request.getParameter("APELLIDOS");
        String fechaNacimiento = request.getParameter("FECHA_NACIMIENTO");
        String correo = request.getParameter("CORREO");
        String contraseña = request.getParameter("CONTRASENA");


        if (idCliente != null && !idCliente.isEmpty() &&
                nombreCliente != null && !nombreCliente.isEmpty() &&
                apellidosCliente != null && !apellidosCliente.isEmpty() &&
                fechaNacimiento != null && !fechaNacimiento.isEmpty() &&
                correo != null && !correo.isEmpty() &&
                contraseña != null && !contraseña.isEmpty()) {
            try {


                Clientes clientes = new Clientes();

                clientes.set_idCliente(idCliente);
                clientes.setNombre(nombreCliente);
                clientes.setApellidos(apellidosCliente);
                clientes.setFecha_Nacimiento(fechaNacimiento);
                clientes.setCorreo(correo);
                clientes.setContrasena(contraseña);

                ClientesDao clientesDao = new ClientesDao();
                int result = clientesDao.add(clientes);
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
    private String delete(HttpServletRequest request) {
        String id = request.getParameter("ID_CLIENTE");
        if (id != null && !id.isEmpty()) {
            ClientesDao clientesDao = new ClientesDao();
            int result = clientesDao.delete(id);
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
        ClientesDao clientesDao = new ClientesDao();  // Create an instance
        ArrayList<Clientes> clientes = clientesDao.findAll(null);  // Call the instance method
        return Clientes.toArrayJSon(clientes);
    }
}