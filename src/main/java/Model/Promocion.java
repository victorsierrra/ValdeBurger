package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Promocion {
    private String _idPromocion, _nombrePromocion;
    private double _descuento;
    public Promocion (String pIdPromocion, String pNombrePromocion, double pDescuento){
        this._idPromocion=pIdPromocion;
        this._nombrePromocion=pNombrePromocion;
        this._descuento=pDescuento;
    }
    public Promocion(){}
    public String getIdPromocion(){
        return _idPromocion;
    }
    public void setIdPromocion(String value){
        this._idPromocion=value;
    }
    public String getNombrePromocion(){
        return _nombrePromocion;
    }
    public void setNombrePromocion(String value){
        this._nombrePromocion=value;
    }
    public double getDescuento(){
        return _descuento;
    }
    public void setDescuento(double value){
        this._descuento=value;
    }
    public static String fromArrayToJson(ArrayList<Promocion> promociones){
        String resp = "[";
        for (Promocion promocion : promociones) {
            resp+= "{" +
                    "'id_Promocion':'" + promocion.getIdPromocion() + "', "
                    + "'NombrePromocion':'" + promocion.getNombrePromocion() +
                    "'Descuento':'" + promocion.getDescuento()+ "}";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }
    public static String toArrayJSon(ArrayList<Promocion> promociones) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(promociones);
    }
}
