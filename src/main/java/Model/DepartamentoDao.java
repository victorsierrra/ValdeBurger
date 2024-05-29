package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DepartamentoDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM DEPARTAMENTOS WHERE 1=1 ";
    private static final String SQL_UPDATE = "UPDATE DEPARTAMENTOS SET ";
    private static final String SQL_ADD = "INSERT INTO DEPARTAMENTOS  (ID_DEPARTAMENTO, NOMBRE_DEPARTAMENTO) VALUES (";
    private static final String SQL_DELETE = "DELETE FROM DEPARTAMENTOS WHERE ID_DEPARTAMENTO = '";

    @Override
    public int add(Object bean) {
        MotorSQL motorSQL = new MotorSQL();
        motorSQL.connect();
        String sql = SQL_ADD;
        Departamentos departamentos = (Departamentos) bean;
        sql += "'" + departamentos.getIdDepartamento() + "'";
        sql += ", ";
        sql += "'" + departamentos.getNombreDepartamento() + "'";
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
        System.out.println(SQL_DELETE  + id + "'");
        try {
            // Asegurarse de que el ID está encerrado en comillas simples
            String sql = SQL_DELETE  + id + "'";
            motor.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motor.execute(sql);
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
        Departamentos departamentos = (Departamentos) bean;
        String sql = SQL_UPDATE +
                "NOMBRE_DEPARTAMENTO = '" + departamentos.getNombreDepartamento() +
                "' WHERE ID_DEPARTAMENTO = '" + departamentos.getIdDepartamento() + "'";
        System.out.println(sql);

        int filasModificadas = motorSQL.execute(sql);
        motorSQL.disconnect();

        return filasModificadas;
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

