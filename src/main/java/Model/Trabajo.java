package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Trabajo {
    private String _idTrabajo, _titulo_trabajo, _idDepartamento;

    public Trabajo(String pIdTrabajo, String ptituloTrabajo, String pDepartamentos) {
        this._idTrabajo = pIdTrabajo;
        this._titulo_trabajo = ptituloTrabajo;
        this._idDepartamento = pDepartamentos;
    }

    public Trabajo() {
    }

    public String getIdTrabajo() {
        return _idTrabajo;
    }

    public void setIdTrabajo(String pIdTrabajo) {
        this._idTrabajo = pIdTrabajo;
    }

    public String getTituloTrabajo() {
        return _titulo_trabajo;
    }

    public void setTituloTrabajo(String pTituloTrabajo) {
        this._titulo_trabajo = pTituloTrabajo;
    }

    public String getDepartamento() {
        return _idDepartamento;
    }

    public void setDepartamento(String pDepartamentos) {
        this._idDepartamento = pDepartamentos;
    }

    @Override
    public String toString() {
        return "Trabajo{" + "id_Trabajo=" + _idTrabajo + ", ID_Departamento=" + _idDepartamento + ", Titulo_Trabajo=" + _titulo_trabajo + '}';
    }

    public static String fromArrayToJson(ArrayList<Trabajo> trabajos) {
        String resp = "[";
        for (Trabajo trabajo : trabajos) {
            resp += "{" +
                    "'ID_Trabajo':'" + trabajo.getIdTrabajo() + "', "
                    + "'ID_Departamento':'" + trabajo.getDepartamento() + "', "
                    + "'Titulo_Trabajo':'" + trabajo.getTituloTrabajo() + "}";
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Trabajo> trabajos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(trabajos);
    }
}
