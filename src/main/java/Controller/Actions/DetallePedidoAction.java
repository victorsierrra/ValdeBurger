package Controller.Actions;

import Model.DetallePedido;
import Model.DetallePedidoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class DetallePedidoAction implements IAction{
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
        DetallePedidoDao detallePedidoDao = new DetallePedidoDao();  // Create an instance
        ArrayList<DetallePedido> detallePedido = detallePedidoDao.findAll(null);  // Call the instance method
        return DetallePedido.toArrayJSon(detallePedido);
    }
}
