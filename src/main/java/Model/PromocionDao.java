package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PromocionDao implements IDao {
    private static final String SQL_FIND_ALL = "SELECT * FROM PROMOCION WHERE 1=1 ";
    private final String SQL_DELETE = "DELETE FROM PROMOCION WHERE ID_PROMOCION='";
    private final String SQL_ADD = "INSERT INTO PROMOCION (ID_PROMOCION, NOMBREPROMOCION, DESCUENTO) VALUES (";
    @Override
    public int add(Object bean) {
        MotorSQL motorSQL = new MotorSQL();
        motorSQL.connect();
        String sql = SQL_ADD;
        Promocion promocion = (Promocion) bean;
        sql += "'" + promocion.getIdPromocion() + "'";
        sql += ",";
        sql += "'" + promocion.getNombrePromocion() + "'";
        sql += ",";
        sql += promocion.getDescuento();
        sql += ")";
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
    public int update(Object bean) {
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
    public ArrayList<Promocion> findAll(Object bean) {  // Instance method
        ArrayList<Promocion> promociones = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Promocion)bean).getIdPromocion() != null) {
                    sql += " AND ID_PROMOCION='" + ((Promocion)bean).getIdPromocion() + "'";
                }
                if (((Promocion)bean).getNombrePromocion() != null) {
                    sql += " AND NOMBREPROMOCION='" + ((Promocion)bean).getNombrePromocion() + "'";
                }
                if (((Promocion)bean).getDescuento() != 0) {
                    sql += " AND DESCUENTO='" + ((Promocion)bean).getDescuento() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("ID_Promocion"));
                promocion.setNombrePromocion(rs.getString("NombrePromocion"));
                promocion.setDescuento(rs.getDouble("Descuento"));
                promociones.add(promocion);
            }

        } catch (Exception ex) {
            promociones.clear();
        } finally {
            motor.disconnect();
        }
        return promociones;
    }
}