package Controller.Actions;

import Controller.Controller;
import Model.*;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PedidosAction implements IAction{
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
                Pedidos e = gson.fromJson(parser.parse(Controller.getBody(request)), Pedidos.class);
                return addPedido(e);
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        PedidosDao pedidosDao = new PedidosDao();  // Create an instance
        ArrayList<Pedidos> pedidos = pedidosDao.findAll(null);  // Call the instance method
        return Pedidos.toArrayJSon(pedidos);
    }
    private String addPedido(Pedidos p) {
        PedidosDao pedidosDao = new PedidosDao();
        Integer iPedidos = pedidosDao.add(p);
        return iPedidos.toString();
    }
}

