package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PedidosDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM PEDIDOS WHERE 1=1 ";

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
    public ArrayList<Pedidos> findAll(Object bean) {  // Instance method
        ArrayList<Pedidos> pedido = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Pedidos) bean).get_idPedidos() != null) {
                    sql += " AND ID_PEDIDO='" + ((Pedidos) bean).get_idPedidos() + "'";
                }
                if (((Pedidos) bean).getEmpleados() != 0) {
                    sql += " AND ID_EMPLEADOS='" + ((Pedidos) bean).getEmpleados() + "'";
                }
                if (((Pedidos) bean).getClientes() != 0) {
                    sql += " AND ID_CLIENTES='" + ((Pedidos) bean).getClientes() + "'";
                }
                if (((Pedidos) bean).getFecha_Pedido() != null) {
                    sql += " AND FECHA_PEDIDOS='" + ((Pedidos) bean).getFecha_Pedido() + "'";
                }
                if (((Pedidos) bean).getDireccion_Entrega() != null) {
                    sql += " AND DIRECCION_ENTREGA='" + ((Pedidos) bean).getDireccion_Entrega() + "'";
                }
                if (((Pedidos) bean).getPrecio_Total() != null) {
                    sql += " AND PRECIO_TOTAL='" + ((Pedidos) bean).getPrecio_Total() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Pedidos pedidos = new Pedidos();
                pedidos.set_idPedidos(rs.getString("ID_PEDIDO"));
                pedidos.setFecha_Pedido(rs.getDate("FECHA_PEDIDO"));
                pedidos.setDireccion_Entrega(rs.getString("DIRECCION_ENTREGA"));
                pedidos.setPrecio_Total(rs.getDouble("PRECIO_TOTAL"));
                Empleados empleado = new Empleados();
                pedidos.setEmpleados(rs.getInt("ID_EMPLEADO"));
                Clientes clientes = new Clientes();
                pedidos.setClientes(rs.getInt("ID_CLIENTE"));
                pedido.add(pedidos);
            }

        } catch (Exception ex) {
            pedido.clear();
        } finally {
            motor.disconnect();
        }
        return pedido;
    }
}


