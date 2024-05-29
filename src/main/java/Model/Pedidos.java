package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;

public class Pedidos {
    private String _idPedidos;
    private String _fecha_pedido;
    private String _direccion_entrega;
    private Double _precio_total;
    private int _idEmpleados, _idClientes;

    public Pedidos(String pIdPedidos, String pFecha_Pedido, String pDireccion_Entrega, Double pPrecio_Total, int pEmpleados, int pClientes) {

        this._idPedidos = pIdPedidos;
        this._fecha_pedido = pFecha_Pedido;
        this._direccion_entrega = pDireccion_Entrega;
        this._precio_total = pPrecio_Total;
        this._idEmpleados = pEmpleados;
        this._idClientes = pClientes;
    }
    public Pedidos() {}

    public String get_idPedidos() {
        return this._idPedidos;
    }

    public void set_idPedidos(String value) {
        this._idPedidos = value;
    }

    public String getFecha_Pedido() {
        return _fecha_pedido;
    }

    public void setFecha_Pedido(String value){
        this._fecha_pedido=value;
    }

    public void setDireccion_Entrega(String value){
        this._direccion_entrega=value;
    }


    public String getDireccion_Entrega() {
        return _direccion_entrega;
    }


    public void setPrecio_Total(Double value){
        this._precio_total=value;
    }


    public Double getPrecio_Total() {
        return _precio_total;
    }

    public int getEmpleados(){
        return _idEmpleados;
    }
    public void setEmpleados(int value){
        this._idEmpleados=value;
    }
    public int getClientes(){
        return _idClientes;
    }
    public void setClientes(int value){
        this._idClientes=value;
    }

    @Override
    public String toString(){
        return "Pedidos{" + "id_Pedidos=" + _idPedidos + ", id_Empleados=" + _idEmpleados + ", id_Clientes=" + _idClientes +
                ", Fecha_Pedido=" + _fecha_pedido + ", Direccion_Entrega=" + _direccion_entrega + ", Precio_Total=" + _precio_total  + '}';
    }
    public static String toArrayJSon(ArrayList<Pedidos> pedidos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(pedidos);
    }
}


