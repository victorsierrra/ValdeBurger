package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PedidosDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM PEDIDOS WHERE 1=1 ORDER BY ID_PEDIDO ";
    private final static String SQL_ADD = "INSERT INTO PEDIDOS ( ID_EMPLEADO, ID_CLIENTE, FECHA_PEDIDO, DIRECCION_ENTREGA, PRECIO_TOTAL) VALUES (";

    @Override
    public int add(Object bean) {
        MotorSQL motorSQL = new MotorSQL();
        motorSQL.connect();
        String sql = SQL_ADD;
        Pedidos pedidos = (Pedidos) bean;
        sql += pedidos.getEmpleados() + " ";
        sql += ", " + pedidos.getClientes() + " ";
        sql += ",'" + pedidos.getFecha_Pedido() + "'";
        sql += ",'" + pedidos.getDireccion_Entrega() + "'";
        sql += ", " + pedidos.getPrecio_Total() ;
        sql += ")";
        System.out.println(sql);
        System.out.println("ID Cliente: " + pedidos.getClientes());
        System.out.println("ID Empleado: " + pedidos.getEmpleados());
        System.out.println("Fecha Pedido: " + pedidos.getFecha_Pedido());
        System.out.println("Dirección Entrega: " + pedidos.getDireccion_Entrega());
        System.out.println("Precio Total: " + pedidos.getPrecio_Total());

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
                    sql += " AND ID_EMPLEADO='" + ((Pedidos) bean).getEmpleados() + "'";
                }
                if (((Pedidos) bean).getClientes() != 0) {
                    sql += " AND ID_CLIENTE='" + ((Pedidos) bean).getClientes() + "'";
                }
                if (((Pedidos) bean).getFecha_Pedido() != null) {
                    sql += " AND FECHA_PEDIDO='" + ((Pedidos) bean).getFecha_Pedido() + "'";
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
                pedidos.setFecha_Pedido(rs.getString("FECHA_PEDIDO"));
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


