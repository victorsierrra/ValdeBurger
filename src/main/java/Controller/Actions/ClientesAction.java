package Controller.Actions;

import Controller.Controller;
import Model.Clientes;
import Model.ClientesDao;
import Model.MotorSQL;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ClientesAction implements IAction{
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
                break;/*
            case "add":
                strReturn = add(request);
                break;*/
            case "DELETE":
                strReturn = delete(request);
                break;
            case "LOGIN":
                strReturn = verificarCliente(request);
                break;
            case "ADD":
                Clientes c = gson.fromJson(parser.parse(Controller.getBody(request)), Clientes.class);
                return AddUsuario(c);
            case "UPDATE":
                Clientes z = gson.fromJson(parser.parse(Controller.getBody(request)), Clientes.class);
                return update(z);
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }
    private String AddUsuario(Clientes c) {
        ClientesDao clientesDao = new ClientesDao();
        Integer iUsuario = clientesDao.add(c);
        return iUsuario.toString();
    }
    private String update(Clientes c) {
        ClientesDao clientesDao = new ClientesDao();
        Integer iUsuario = clientesDao.update(c);
        return iUsuario.toString();

        /*
        //String idCliente = request.getParameter("ID_CLIENTE");
        String nombreCliente = request.getParameter("NOMBRE");
        String apellidosCliente = request.getParameter("APELLIDOS");
        String fechaNacimiento = request.getParameter("FECHA_NACIMIENTO");
        String correo = request.getParameter("CORREO");
        String contraseña = request.getParameter("CONTRASENA");


        if (//idCliente != null && !idCliente.isEmpty() &&
                nombreCliente != null && !nombreCliente.isEmpty() &&
                apellidosCliente != null && !apellidosCliente.isEmpty() &&
                fechaNacimiento != null && !fechaNacimiento.isEmpty() &&
                correo != null && !correo.isEmpty() &&
                contraseña != null && !contraseña.isEmpty()) {
            try {


                Clientes clientes = new Clientes();

               // clientes.set_idCliente(idCliente);
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
        }*/
    }
    private String add(HttpServletRequest request) {
        //String idCliente = request.getParameter("ID_CLIENTE");
        String nombreCliente = request.getParameter("NOMBRE");
        String apellidosCliente = request.getParameter("APELLIDOS");
        String fechaNacimiento = request.getParameter("FECHA_NACIMIENTO");
        String correo = request.getParameter("CORREO");
        String contraseña = request.getParameter("CONTRASENA");


        if (//idCliente != null && !idCliente.isEmpty() &&
                nombreCliente != null && !nombreCliente.isEmpty() &&
                apellidosCliente != null && !apellidosCliente.isEmpty() &&
                fechaNacimiento != null && !fechaNacimiento.isEmpty() &&
                correo != null && !correo.isEmpty() &&
                contraseña != null && !contraseña.isEmpty()) {
            try {


                Clientes clientes = new Clientes();

                //clientes.set_idCliente(idCliente);
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
    public String verificarCliente(HttpServletRequest request) {
        ClientesDao clientesDao = new ClientesDao();
        String correo = request.getParameter("CORREO");
        String contrasena = request.getParameter("CONTRASENA");

        if (correo != null && !correo.isEmpty() && contrasena != null && !contrasena.isEmpty()) {
            int idCliente = clientesDao.checkCorreoContra(correo, contrasena);
            String stridCliente = String.valueOf(idCliente);
            if (idCliente != 0) {
                return stridCliente;
            } else {
                return "E-Mail o contraseña no válidos";
            }
        } else {
            return "Correo y contraseña son obligatorios.";
        }
    }
}