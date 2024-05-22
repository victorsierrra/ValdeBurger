package Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import java.util.ArrayList;
public class Empleados {
    private String _nombre, _apellidos, _DNI, _correo, _contrasena, _idDepartamento, _idTrabajo;
    private int _idEmpleado,_telefono, _salario;
    private Date _fechaNac, _fechaCont;
    public Empleados(int pIdEmpleado, String pNombre, String pApellidos, int pTelefono,
                     String pCorreo, String pDNI, String pContrasena, int pSalario, String pTrabajo,
                     String pDepartamentos, Date pFechaNac, Date pFechaCont){
        this._idEmpleado=pIdEmpleado;
        this._nombre=pNombre;
        this._apellidos=pApellidos;
        this._telefono=pTelefono;
        this._correo=pCorreo;
        this._contrasena=pContrasena;
        this._DNI=pDNI;
        this._salario=pSalario;
        this._idTrabajo=pTrabajo;
        this._idDepartamento=pDepartamentos;
        this._fechaNac=pFechaNac;
        this._fechaCont=pFechaCont;
    }
    public Empleados(){}
    public int getIdEmpleado(){
        return _idEmpleado;
    }
    public void setIdEmpleado(int value){
        this._idEmpleado=value;
    }
    public String getNombre(){
        return _nombre;
    }
    public void setNombre(String value){
        this._nombre=value;
    }
    public String getApellido(){
        return _apellidos;
    }
    public void setApellido(String value){
        this._apellidos=value;
    }
    public int getTelefono(){
        return _telefono;
    }
    public void setTelefono(int value){
        this._telefono=value;
    }
    public String getCorreo(){
        return _correo;
    }
    public void setCorreo(String value){
        this._correo=value;
    }
    public String getContrasena(){
        return _contrasena;
    }
    public void setDNI(String value){
        this._DNI=value;
    }
    public void setContrasena(String value){
        this._contrasena=value;
    }
    public String getDNI(){
        return _DNI;
    }
    public int getSalario(){
        return _salario;
    }
    public void setSalario(int value){
        this._salario=value;
    }
    public String getTrabajo(){
        return _idTrabajo;
    }
    public void setTrabajo(String value){
        this._idTrabajo=value;
    }
    public String getDepartamento(){
        return _idDepartamento;
    }
    public void setDepartamento(String value){
        this._idDepartamento=value;
    }
    public Date getFechaNac(){
        return _fechaNac;
    }
    public void setFechaNac(Date value){
        this._fechaNac=value;
    }
    public Date getFechaCont(){
        return _fechaCont;
    }
    public void setFechaCont(Date value){
        this._fechaCont=value;
    }

    @Override
    public String toString(){
        return "Empleado{" + "id_Empleado=" + _idEmpleado +  ", ID_Departamento=" + _idDepartamento + ", ID_Trabajo=" + _idTrabajo +
                ", Nombre=" + _nombre + ", Apellidos=" + _apellidos + ", Telefono=" + _telefono + ", Correo=" + _correo +
                ", Contraseña=" + _contrasena + ", Salario=" + _salario + ", DNI=" + _DNI +
                ", Fecha de Contratacion=" + _fechaCont +
                ", Fecha de Nacimiento=" + _fechaNac + '}';
    }
    public static String toArrayJSon(ArrayList<Empleados> empleados) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(empleados);
    }
}
