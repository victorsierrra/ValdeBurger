package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDao implements IDao {
    private static final String SQL_FIND_ALL = "SELECT * FROM CATEGORIA WHERE 1=1 ";
    private final String SQL_DELETE = "DELETE FROM CATEGORIA WHERE ID_CATEGORIA='";

    @Override
    public int add(Object bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer bean) {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Categoria> findAll(Object bean) {  // Instance method
        ArrayList<Categoria> categorias = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Categoria)bean).getIdCategoria() != null) {
                    sql += " AND ID_Categoria='" + ((Categoria)bean).getIdCategoria() + "'";
                }
                if (((Categoria)bean).getNombreCategoria() != null) {
                    sql += " AND NOMBRE='" + ((Categoria)bean).getIdCategoria() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getString("Id_Categoria"));
                categoria.setNombreCategoria(rs.getString("Nombre"));
                categorias.add(categoria);
            }

        } catch (Exception ex) {
            categorias.clear();
        } finally {
            motor.disconnect();
        }
        return categorias;
    }
}