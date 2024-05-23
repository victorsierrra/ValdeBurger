package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;

public class Clientes {
        private String _idCliente;
        private String _nombre;
        private String _apellidos;
        private String _fecha_nacimiento;
        private String _correo;
        private String _contrasena;

        public Clientes(String pIdCliente, String pNombre, String pApellidos, String pFecha_Nacimiento, String pCorreo, String pContrasena) {

            this._idCliente = pIdCliente;
            this._nombre = pNombre;
            this._apellidos = pApellidos;
            this._fecha_nacimiento = pFecha_Nacimiento;
            this._correo = pCorreo;
            this._contrasena = pContrasena;
        }
        public Clientes() {}

        public String get_idCliente() {
            return this._idCliente;
        }

        public void set_idCliente(String value) {
            this._idCliente = value;
        }

        public String getNombre() {
            return _nombre;
        }

        public void setNombre(String value){
            this._nombre=value;
        }

        public void setApellidos(String value){
            this._apellidos=value;
        }


        public String getApellidos() {
            return _apellidos;
        }


        public void setFecha_Nacimiento(String value){
            this._fecha_nacimiento=value;
        }


        public String getFecha_Nacimiento() {
            return _fecha_nacimiento;
        }

        public String getCorreo() {
            return _correo;
        }

        public void setCorreo(String value){
            this._correo=value;
        }

        public String getContrsena() {
            return _contrasena;
        }

        public void setContrasena(String value){
            this._contrasena=value;
        }

        @Override
        public String toString(){
            return "Clientes{" + "id_Cliente=" + _idCliente + ", Nombre=" + _nombre + ", Apellidos=" + _apellidos +
                    ", Fecha_Nacimiento=" + _fecha_nacimiento + ", Correo=" + _correo + ", Contrasena=" + _contrasena  + '}';
        }
        public static String toArrayJSon(ArrayList<Model.Clientes> clientes) {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = builder.create();

            return gson.toJson(clientes);
        }
    }
