package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DetallePedidoDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM DETALLEPEDIDO WHERE 1=1 ";
    private final static String SQL_ADD = "INSERT INTO DETALLEPEDIDO ( ID_PEDIDO, ID_PRODUCTO, CANTIDAD, PRECIO) VALUES (";

    @Override
    public int add(Object bean) {
        MotorSQL motorSQL = new MotorSQL();
        motorSQL.connect();
        String sql = SQL_ADD;
        DetallePedido detallePedido = (DetallePedido) bean;
        sql += detallePedido.get_pedidos() + " ";
        sql += ", " + detallePedido.get_producto() + " ";
        sql += ",'" + detallePedido.get_cantidad() + "'";
        sql += ",'" + detallePedido.getPrecio() + "'";
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
    public int delete(String bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Object bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<DetallePedido> findAll(Object bean) {  // Instance method
        ArrayList<DetallePedido> detellePedidos = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((DetallePedido) bean).get_idDetallePedido() != null) {
                    sql += " AND ID_DETALLEPEDIDO='" + ((DetallePedido) bean).get_idDetallePedido() + "'";
                }
                if (((DetallePedido) bean).get_pedidos() != null) {
                    sql += " AND ID_PEDIDO='" + ((DetallePedido) bean).get_pedidos() + "'";
                }
                if (((DetallePedido) bean).get_producto() != null) {
                    sql += " AND ID_PRODUCTO='" + ((DetallePedido) bean).get_producto() + "'";
                }
                if (((DetallePedido) bean).get_cantidad() != 0) {
                    sql += " AND CANTIDAD='" + ((DetallePedido) bean).get_cantidad() + "'";
                }
                if (((DetallePedido) bean).getPrecio() != null) {
                    sql += " AND PRECIO='" + ((DetallePedido) bean).getPrecio() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                DetallePedido detellePedido = new DetallePedido();
                detellePedido.set_idDetallePedido(rs.getString("ID_DETALLEPEDIDO"));
                detellePedido.set_cantidad(rs.getInt("CANTIDAD"));
                detellePedido.setPrecio(rs.getDouble("PRECIO"));
                detellePedido.set_pedidos(rs.getString("ID_PEDIDO"));
                detellePedido.set_producto(rs.getString("ID_PRODUCTO"));

                detellePedidos.add(detellePedido);
            }

        } catch (Exception ex) {
            detellePedidos.clear();
        } finally {
            motor.disconnect();
        }
        return detellePedidos;
    }
}
