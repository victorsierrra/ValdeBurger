package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DepartamentoDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM DEPARTAMENTOS WHERE 1=1 ";

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
    public ArrayList<Departamentos> findAll(Object bean) {  // Instance method
        ArrayList<Departamentos> departamentos = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Departamentos)bean).getIdDepartamento() != null) {
                    sql += " AND ID_Departamento='" + ((Departamentos)bean).getIdDepartamento() + "'";
                }
                if (((Departamentos)bean).getNombreDepartamento() != null) {
                    sql += " AND Nombre_Departamento='" + ((Departamentos)bean).getNombreDepartamento() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Departamentos departamento = new Departamentos();
                departamento.setIdDepartamento(rs.getString("Id_Departamento"));
                departamento.setNombreDepartamento(rs.getString("Nombre_Departamento"));
                departamentos.add(departamento);
            }

        } catch (Exception ex) {
            departamentos.clear();
        } finally {
            motor.disconnect();
        }
        return departamentos;
    }
}

