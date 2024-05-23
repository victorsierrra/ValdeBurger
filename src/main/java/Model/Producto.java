//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Iterator;

public class Producto {
    private String _idProducto;
    private Categoria _categoria;
    private String _nombre;
    private String _descripcion;
    private String _ingredientes;
    private double _precio;
    private String _ruta_imagen;
    private String _ruta_imagen_alergias1;
    private String _ruta_imagen_alergias2;
    private String _ruta_imagen_alergias3;

    public Producto(String pIdProducto, String pNombre, String pDescripcion, String pIngredientes, int pPrecio, String pRuta_Imagen, String pRuta_Imagen_Alergias1,
                    String pRuta_Imagen_Alergias2, String pRuta_Imagen_Alergias3, Categoria pCategoria) {

        this._idProducto = pIdProducto;
        this._nombre = pNombre;
        this._descripcion = pDescripcion;
        this._ingredientes = pIngredientes;
        this._precio = pPrecio;
        this._ruta_imagen = pRuta_Imagen;
        this._ruta_imagen_alergias1 = pRuta_Imagen_Alergias1;
        this._ruta_imagen_alergias2 = pRuta_Imagen_Alergias2;
        this._ruta_imagen_alergias3 = pRuta_Imagen_Alergias3;
        this._categoria = pCategoria;

    }
    public Producto() {}

        public String getIdProducto() {
            return this._idProducto;
        }

        public void setIdProducto(String value) {
            this._idProducto = value;
        }

        public String getNombre() {
            return _nombre;
        }

        public void setNombre(String value){
        this._nombre=value;
    }

        public void setDescripcion(String value){
        this._descripcion=value;
    }


        public String getDescripcion() {
        return _descripcion;
        }


        public void setIngredientes(String value){
        this._ingredientes=value;
    }


         public String getIngredientes() {
        return _ingredientes;
    }

        public double getPrecio() {
            return _precio;
        }

        public void setPrecio(double value){
        this._precio=value;
    }

        public String getRuta_Imagen() {
            return _ruta_imagen;
        }

        public void set_Ruta_Imagen(String value){
        this._ruta_imagen=value;
    }

        public String getRuta_Imagen_Alergias1() {
            return _ruta_imagen_alergias1;
        }

        public void set_Ruta_Imagen_Alergias1(String value){
        this._ruta_imagen_alergias1=value;
        }

        public String getRuta_Imagen_Alergias2() {
        return _ruta_imagen_alergias2;
        }

        public void set_Ruta_Imagen_Alergias2(String value){
        this._ruta_imagen_alergias2=value;
        }

        public String getRuta_Imagen_Alergias3() {
        return _ruta_imagen_alergias3;
        }

        public void set_Ruta_Imagen_Alergias3(String value){
        this._ruta_imagen_alergias3=value;
        }

        public Categoria getCategoria() {return _categoria;}

        public void setCategoria(Categoria value){
        this._categoria=value;
    }




    @Override
    public String toString(){
        return "Producto{" + "id_Producto=" + _idProducto + ", Nombre=" + _nombre + ", Descripcion=" + _descripcion +
                ", Ingredientes=" + _ingredientes + ", Precio=" + _precio + ", Ruta_Imagen=" + _ruta_imagen + ", Ruta_Imagen_Alergias1=" + _ruta_imagen_alergias1 +
                ", Ruta_Imagen_Alergias2=" + _ruta_imagen_alergias2 + ", Ruta_Imagen_Alergias3=" + _ruta_imagen_alergias3 + ", ID_Categoria=" + _categoria + '}';
    }
    public static String toArrayJSon(ArrayList<Producto> producto) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(producto);
    }
}

