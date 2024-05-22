package Controller.Actions;


import Model.Promocion;
import Model.PromocionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PromocionAction implements IAction {
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

    private String findAll() {
        PromocionDao promocionDao = new PromocionDao();  // Create an instance
        ArrayList<Promocion> promociones = promocionDao.findAll(null);  // Call the instance method
        return Promocion.toArrayJSon(promociones);
    }
    private String delete(HttpServletRequest request) {
        String id = request.getParameter("ID_PROMOCION");
        if (id != null && !id.isEmpty()) {
            PromocionDao promocionDao = new PromocionDao();
            int result = promocionDao.delete(id);
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
        String idPromocion = request.getParameter("ID_PROMOCION");
        String nombrePromocion = request.getParameter("NOMBRE_PROMOCION");
        String descuentoStr = request.getParameter("DESCUENTO");

        if (idPromocion != null && !idPromocion.isEmpty() &&
                nombrePromocion != null && !nombrePromocion.isEmpty() &&
                descuentoStr != null && !descuentoStr.isEmpty()) {
            try {
                double descuento = Double.parseDouble(descuentoStr);

                Promocion promocion = new Promocion();
                promocion.setIdPromocion(idPromocion);
                promocion.setNombrePromocion(nombrePromocion);
                promocion.setDescuento(descuento);

                PromocionDao promocionDao = new PromocionDao();
                int result = promocionDao.update(promocion);
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
        String idPromocion = request.getParameter("ID_PROMOCION");
        String nombrePromocion = request.getParameter("NOMBRE_PROMOCION");
        String descuentoStr = request.getParameter("DESCUENTO");

        if (idPromocion != null && !idPromocion.isEmpty() &&
                nombrePromocion != null && !nombrePromocion.isEmpty() &&
                descuentoStr != null && !descuentoStr.isEmpty()) {
            try {
                double descuento = Double.parseDouble(descuentoStr);

                Promocion promocion = new Promocion();
                promocion.setIdPromocion(idPromocion);
                promocion.setNombrePromocion(nombrePromocion);
                promocion.setDescuento(descuento);

                PromocionDao promocionDao = new PromocionDao();
                int result = promocionDao.add(promocion);
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
}
