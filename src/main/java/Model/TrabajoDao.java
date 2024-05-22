package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TrabajoDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM TRABAJO WHERE 1=1 ";
    private static final String SQL_UPDATE = "UPDATE TRABAJO SET ";
    private static final String SQL_DELETE = "DELETE FROM TRABAJO WHERE ID_TRABAJO = '";
    private static final String SQL_ADD = "INSERT INTO TRABAJO (ID_TRABAJO, ID_DEPARTAMENTO, TITULO_TRABAJO) VALUES (";

    @Override
    public int add(Object bean) {
        MotorSQL motorSQL = new MotorSQL();
        motorSQL.connect();
        String sql = SQL_ADD;
        Trabajo trabajo = (Trabajo) bean;
        sql += "'" + trabajo.getIdTrabajo() + "'";
        sql += ",";
        sql += "'" + trabajo.getDepartamento() + "'";
        sql += ",'";
        sql += trabajo.getTituloTrabajo();
        sql += "')";
        System.out.println(sql);  // Para depuración

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
        try {
            // Formar la consulta SQL
            String sql = SQL_DELETE + id + "'";
            System.out.println("Ejecutando SQL: " + sql); // Imprimir la consulta SQL para depuración

            // Ejecutar la consulta de eliminación
            resp = motor.execute(sql);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
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
        Trabajo trabajo = (Trabajo) bean;
        String sql = SQL_UPDATE +
                "ID_DEPARTAMENTO = '" + trabajo.getDepartamento() + "', " +
                "TITULO_TRABAJO = '" + trabajo.getTituloTrabajo() +
                "' WHERE ID_TRABAJO = '" + trabajo.getIdTrabajo() + "'";
        System.out.println(sql);

        int filasModificadas = motorSQL.execute(sql);
        motorSQL.disconnect();

        return filasModificadas;
    }

    @Override
    public ArrayList<Trabajo> findAll(Object bean) {  // Instance method
        ArrayList<Trabajo> trabajos = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Trabajo)bean).getIdTrabajo() != null) {
                    sql += " AND ID_TRABAJO='" + ((Trabajo)bean).getIdTrabajo() + "'";
                }
                if (((Trabajo)bean).getDepartamento() != null) {
                    sql += " AND ID_DEPARTAMENTO='" + ((Trabajo)bean).getDepartamento() + "'";
                }
                if (((Trabajo)bean).getTituloTrabajo() != null) {
                    sql += " AND TITULO_TRABAJO='" + ((Trabajo)bean).getTituloTrabajo() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Trabajo trabajo = new Trabajo();
                trabajo.setIdTrabajo(rs.getString("Id_Trabajo"));
                trabajo.setTituloTrabajo(rs.getString("Titulo_Trabajo"));
                trabajo.setDepartamento(rs.getString("ID_Departamento"));

                trabajos.add(trabajo);
            }

        } catch (Exception ex) {
            trabajos.clear();
        } finally {
            motor.disconnect();
        }
        return trabajos;
    }
}
