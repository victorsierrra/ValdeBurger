package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpleadosDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM EMPLEADOS WHERE 1=1 ";

    @Override
    public int add(Object bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(String bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Object bean) {
        throw new UnsupportedOperationException("Not supported yet.");
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
                empleado.setFechaNac(rs.getDate("FECHA_NACIMIENTO"));
                empleado.setFechaCont(rs.getDate("FECHA_CONTRATACION"));
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

