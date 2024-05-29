package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientesDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM  CLIENTES WHERE 1=1 ";
    private final String SQL_DELETE = "DELETE FROM CLIENTES WHERE ID_CLIENTE = ";
    private final String SQL_UPDATE = "UPDATE CLIENTES SET ";
    private final String SQL_LOGIN = " SELECT ID_CLIENTE FROM CLIENTES WHERE CORREO = '";
    private final String SQL_ADD = "INSERT INTO CLIENTES ( NOMBRE, APELLIDOS, FECHA_NACIMIENTO, CORREO, CONTRASENA) VALUES (";

    @Override
    public int add(Object bean) {
        MotorSQL motorSQL = new MotorSQL();
        motorSQL.connect();
        String sql = SQL_ADD;

        Clientes clientes = (Clientes) bean;
        //sql += clientes.get_idCliente();
        sql += "'" + clientes.getNombre() + "'";
        sql += ",'" + clientes.getApellidos() + "'";
        sql += ",'" + clientes.getFecha_Nacimiento() + "'";
        sql += ",'" + clientes.getCorreo() + "'";
        sql += ",'" + clientes.getContrsena() + "'";
        sql += ")";
        System.out.println(sql);
        int filasModificadas = motorSQL.execute(sql);
        motorSQL.disconnect();
        return filasModificadas;
    }

    @Override
    public int delete(Integer e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(String id) {
        int resp = 0;
        MotorSQL motor = new MotorSQL();
        motor.connect();
        System.out.println(SQL_DELETE  + id );
        try {
            String sql = SQL_DELETE  + id;
            motor.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = Integer.parseInt(String.valueOf(motor.execute(sql)));
            motor.execute("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp == 0) {
            System.out.println("Borrado con éxito.");
        } else {
            System.out.println("No se pudo borrar.");
        }
        return resp;
    }

    @Override
    public int update(Object bean) {
        MotorSQL motorSQL = new MotorSQL();
        motorSQL.connect();
        Clientes clientes = (Clientes) bean;
        String sql = SQL_UPDATE +
                "NOMBRE = '" + clientes.getNombre() + "', " +
                "APELLIDOS = '" + clientes.getApellidos() + "', " +
                "FECHA_NACIMIENTO = '" + clientes.getFecha_Nacimiento() + "', " +
                "CORREO = '" + clientes.getCorreo() + "', " +
                "CONTRASENA = '" + clientes.getContrsena() + "' " +
                " WHERE ID_CLIENTE = " + clientes.get_idCliente();
        System.out.println(sql);

        int filasModificadas = motorSQL.execute(sql);
        motorSQL.disconnect();

        return filasModificadas;
    }


    public int checkCorreoContra(String correo, String contrasena) {
        int count = 0;
        MotorSQL motor = new MotorSQL();
        motor.connect();
        String sql = SQL_LOGIN + correo + "' AND contrasena = '" + contrasena + "'";
        System.out.println(sql);
        try {
            ResultSet resultSet = motor.executeQuery(sql);
            if (resultSet.next()) {
                count = resultSet.getInt("ID_CLIENTE");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } finally {
            motor.disconnect();
        }
        return count;
    }
    @Override
    public ArrayList<Clientes> findAll(Object bean) {  // Instance method
        ArrayList<Clientes> cliente = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Clientes)bean).get_idCliente() != null) {
                        sql += " AND ID_CLIENTE='" + ((Clientes)bean).get_idCliente() + "'";
                }
                if (((Clientes)bean).getNombre() != null) {
                    sql += " AND NOMBRE='" + ((Clientes)bean).getNombre() + "'";
                }
                if (((Clientes)bean).getApellidos() != null) {
                        sql += " AND APELLIDOS='" + ((Clientes)bean).getApellidos() + "'";
                }
                if (((Clientes)bean).getFecha_Nacimiento() != null) {
                    sql += " AND FECHA_NACIMIENTO='" + ((Clientes)bean).getFecha_Nacimiento() + "'";
                }
                if (((Clientes)bean).getCorreo() != null) {
                    sql += " AND CORREO='" + ((Clientes)bean).getCorreo() + "'";
                }
                if (((Clientes)bean).getContrsena() != null) {
                    sql += " AND CONTRASENA='" + ((Clientes)bean).getContrsena() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Clientes clientes = new Clientes();
                clientes.set_idCliente(rs.getString("ID_CLIENTE"));
                clientes.setNombre(rs.getString("NOMBRE"));
                clientes.setApellidos(rs.getString("APELLIDOS"));
                clientes.setFecha_Nacimiento(rs.getString("FECHA_NACIMIENTO"));
                clientes.setCorreo(rs.getString("CORREO"));
                clientes.setContrasena(rs.getString("CONTRASENA"));

                cliente.add(clientes);
            }

        } catch (Exception ex) {
            cliente.clear();
        } finally {
            motor.disconnect();
        }
        return cliente;
    }
}

