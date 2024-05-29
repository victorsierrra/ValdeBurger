package Controller.Actions;

import Controller.Controller;

import Model.DetallePedido;
import Model.DetallePedidoDao;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class DetallePedidoAction implements IAction{
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
                DetallePedido d = gson.fromJson(parser.parse(Controller.getBody(request)), DetallePedido.class);
                return addDetalle(d);
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        DetallePedidoDao detallePedidoDao = new DetallePedidoDao();  // Create an instance
        ArrayList<DetallePedido> detallePedido = detallePedidoDao.findAll(null);  // Call the instance method
        return DetallePedido.toArrayJSon(detallePedido);
    }
    private String addDetalle(DetallePedido d) {
        DetallePedidoDao detallePedidoDao = new DetallePedidoDao();
        Integer iDetalle = detallePedidoDao.add(d);
        return iDetalle.toString();
    }
}
