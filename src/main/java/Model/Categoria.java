package Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
public class Categoria {
    private String _idCategoria, _nombreCategoria;

    public Categoria(String pidCategoria, String pnombreCategoria){
        this._idCategoria = pidCategoria;
        this._nombreCategoria = pnombreCategoria;
    }
    public Categoria(){}
    // GETTERS & SETTERS

    public String getIdCategoria(){
        return _idCategoria;
    }
    public void setIdCategoria(String pIdCategoria){
        this._idCategoria=pIdCategoria;
    }
    public String getNombreCategoria(){
        return _nombreCategoria;
    }
    public void setNombreCategoria(String pNombreCategoria){
        this._nombreCategoria=pNombreCategoria;
    }

    @Override
    public String toString(){
        return "Categoria{" + "id_Categoria=" + _idCategoria + ", Nombre=" + _nombreCategoria + '}';
    }

    public static String fromArrayToJson(ArrayList<Categoria> categorias){
        String resp = "[";
        for (Categoria categoria : categorias) {
            resp+= "{" +
                    "'id_Categoria':'" + categoria.getIdCategoria() + "', "
                    + "'Nombre':'" + categoria.getNombreCategoria() + "}";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }
    public static String toArrayJSon(ArrayList<Categoria> categorias) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(categorias);
    }
}

