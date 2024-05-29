package Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
public class Departamentos {
    private String _idDepartamento, _nombreDepartamento;
    public Departamentos(String pidDepartamento, String pnombreDepartamento){
        this._idDepartamento = pidDepartamento;
        this._nombreDepartamento = pnombreDepartamento;
    }
    public Departamentos(){}
    // GETTERS & SETTERS

    public String getIdDepartamento(){
        return _idDepartamento;
    }
    public void setIdDepartamento(String pidDepartamento){
        this._idDepartamento=pidDepartamento;
    }
    public String getNombreDepartamento(){
        return _nombreDepartamento;
    }
    public void setNombreDepartamento(String pnombreDepartamento){
        this._nombreDepartamento=pnombreDepartamento;
    }

    @Override
    public String toString(){
        return "Departamentos{" + "id_Departamento=" + _idDepartamento + ", NombreDepartamento=" + _nombreDepartamento + '}';
    }

    public static String fromArrayToJson(ArrayList<Departamentos> departamentos){
        String resp = "[";
        for (Departamentos departamento : departamentos) {
            resp+= "{" +
                    "'id_Departamento':'" + departamento.getIdDepartamento() + "', "
                    + "'Nombre_Departamento':'" + departamento.getNombreDepartamento() + "}";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }
    public static String toArrayJSon(ArrayList<Departamentos> departamentos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(departamentos);
    }
}

