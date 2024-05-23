package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class DetallePedido {
    private String _idDetallePedido;
    private int _cantidad;
    private Double _precio;
    private Pedidos _pedidos;
    private Producto _producto;

    public DetallePedido(String pIdDetallePedido, int pCantidad, Double pPrecio, Pedidos pPedidos, Producto pProducto) {

        this._idDetallePedido = pIdDetallePedido;
        this._cantidad = pCantidad;
        this._precio = pPrecio;
        this._pedidos = pPedidos;
        this._producto = pProducto;
    }
    public DetallePedido() {}

    public String get_idDetallePedido() {
        return this._idDetallePedido;
    }

    public void set_idDetallePedido(String value) {
        this._idDetallePedido = value;
    }

    public int get_cantidad() {
        return _cantidad;
    }
    public void set_cantidad(int value){
        this._cantidad=value;
    }

    public void setPrecio(Double value){
        this._precio=value;
    }

    public Double getPrecio() {return _precio;}

    public Pedidos get_pedidos(){
        return _pedidos;
    }
    public void set_pedidos(Pedidos value){
        this._pedidos=value;
    }
    public Producto get_producto(){
        return _producto;
    }
    public void set_producto(Producto value){
        this._producto=value;
    }

    @Override
    public String toString(){
        return "DetallePedido{" + "id_DetallePedido=" + _idDetallePedido + ", id_Pedido=" + _pedidos + ", id_Produto=" + _producto +
                ", Cantidad=" + _cantidad + ",  Precio=" + _precio  + '}';
    }
    public static String toArrayJSon(ArrayList<DetallePedido> detallePedidos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(detallePedidos);
    }
}
