package Controller.Actions;

import Model.Clientes;
import Model.ClientesDao;
import Model.Pedidos;
import Model.PedidosDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PedidosAction implements IAction{
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
}

