package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpleadosDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM EMPLEADOS WHERE 1=1 ";
    private final String SQL_DELETE = "DELETE FROM EMPLEADOS WHERE ID_EMPLEADO=";
    private final String SQL_UPDATE = "UPDATE EMPLEADOS SET ";
    private final String SQL_ADD = "INSERT INTO EMPLEADOS (ID_EMPLEADO, ID_DEPARTAMENTO, ID_TRABAJO, NOMBRE, APELLIDOS, FECHA_NACIMIENTO, TELEFONO, CORREO, CONTRASENA, DNI, FECHA_CONTRATACION, SALARIO) VALUES (";
    @Override
    public int add(Object bean) {
        MotorSQL motorSQL = new MotorSQL();
        motorSQL.connect();
        String sql = SQL_ADD;
        Empleados empleados = (Empleados) bean;
        sql += empleados.getIdEmpleado();
        sql += ",'" + empleados.getDepartamento() + "'";
        sql += ",'" + empleados.getTrabajo() + "'";
        sql += ",'" + empleados.getNombre() + "'";
        sql += ",'" + empleados.getApellido() + "'";
        sql += ",'" + empleados.getFechaNac() + "'";
        sql += "," + empleados.getTelefono();
        sql += ",'" + empleados.getCorreo() + "'";
        sql += ",'" + empleados.getContrasena() + "'";
        sql += ",'" + empleados.getDNI() + "'";
        sql += ",'" + empleados.getFechaCont() + "'";
        sql += "," + empleados.getSalario();
        sql += ")";
        System.out.println(sql);

        int filasModificadas = motorSQL.execute(sql);
        motorSQL.disconnect();
        return filasModificadas;
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(String id) {

        int resp = 0;
        MotorSQL motor = new MotorSQL();
        motor.connect();
        System.out.println(SQL_DELETE  + id );
        try {
            // Asegurarse de que el ID está encerrado en comillas simples
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
        Empleados empleados = (Empleados) bean;
        String sql = SQL_UPDATE +
                "ID_DEPARTAMENTO = '" + empleados.getDepartamento() + "', " +
                "ID_TRABAJO = '" + empleados.getTrabajo() + "', " +
                "NOMBRE = '" + empleados.getNombre() + "', " +
                "APELLIDOS = '" + empleados.getApellido() + "', " +
                "FECHA_NACIMIENTO = '" + empleados.getFechaNac() + "', " +
                "TELEFONO = " + empleados.getTelefono() + ", " +
                "CORREO = '" + empleados.getCorreo() + "', " +
                "CONTRASENA = '" + empleados.getContrasena() + "', " +
                "DNI = '" + empleados.getDNI() + "', " +
                "FECHA_CONTRATACION = '" + empleados.getFechaCont() + "', " +
                "SALARIO = " + empleados.getSalario() +
                " WHERE ID_EMPLEADO = " + empleados.getIdEmpleado();
        System.out.println(sql);

        int filasModificadas = motorSQL.execute(sql);
        motorSQL.disconnect();

        return filasModificadas;
    }

    @Override
    public ArrayList<Empleados> findAll(Object bean) {  // Instance method
        ArrayList<Empleados> empleados = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Empleados)bean).getIdEmpleado() != 0) {
                    sql += " AND ID_EMPLEADO='" + ((Empleados)bean).getIdEmpleado() + "'";
                }
                if (((Empleados)bean).getDepartamento() != null) {
                    sql += " AND ID_DEPARTAMENTO='" + ((Empleados)bean).getDepartamento() + "'";
                }
                if (((Empleados)bean).getTrabajo() != null) {
                    sql += " AND ID_TRABAJO='" + ((Empleados)bean).getTrabajo() + "'";
                }
                if (((Empleados)bean).getNombre() != null) {
                    sql += " AND NOMBRE='" + ((Empleados)bean).getNombre() + "'";
                }
                if (((Empleados)bean).getApellido() != null) {
                    sql += " AND APELLIDOS='" + ((Empleados)bean).getApellido() + "'";
                }
                if (((Empleados)bean).getTelefono() != 0) {
                    sql += " AND TELEFONO='" + ((Empleados)bean).getTelefono() + "'";
                }
                if (((Empleados)bean).getCorreo() != null) {
                    sql += " AND CORREO='" + ((Empleados)bean).getCorreo() + "'";
                }
                if (((Empleados)bean).getContrasena() != null) {
                    sql += " AND CONTRASENA='" + ((Empleados)bean).getContrasena() + "'";
                }
                if (((Empleados)bean).getDNI() != null) {
                    sql += " AND DNI='" + ((Empleados)bean).getDNI() + "'";
                }
                if (((Empleados)bean).getSalario() != 0) {
                    sql += " AND SALARIO='" + ((Empleados)bean).getSalario() + "'";
                }
                if (((Empleados)bean).getFechaNac() != null) {
                    sql += " AND FECHA_NACIMIENTO='" + ((Empleados)bean).getFechaNac() + "'";
                }
                if (((Empleados)bean).getFechaCont() != null) {
                    sql += " AND FECHA_CONTRATACION='" + ((Empleados)bean).getFechaCont() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Empleados empleado = new Empleados();
                empleado.setIdEmpleado(rs.getInt("ID_EMPLEADO"));
                empleado.setNombre(rs.getString("NOMBRE"));
                empleado.setApellido(rs.getString("APELLIDOS"));
                empleado.setCorreo(rs.getString("CORREO"));
                empleado.setDNI(rs.getString("DNI"));
                empleado.setSalario(rs.getInt("SALARIO"));
                empleado.setContrasena(rs.getString("CONTRASENA"));
                empleado.setTelefono(rs.getInt("TELEFONO"));
                empleado.setFechaNac(rs.getString("FECHA_NACIMIENTO"));
                empleado.setFechaCont(rs.getString("FECHA_CONTRATACION"));
                empleado.setTrabajo(rs.getString("ID_TRABAJO"));
                empleado.setDepartamento(rs.getString("ID_Departamento"));


                empleados.add(empleado);
            }

        } catch (Exception ex) {
            empleados.clear();
        } finally {
            motor.disconnect();
        }
        return empleados;
    }
}

