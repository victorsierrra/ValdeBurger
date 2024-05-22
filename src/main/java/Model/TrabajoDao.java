package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TrabajoDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM TRABAJO WHERE 1=1 ";

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
