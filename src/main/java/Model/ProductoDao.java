package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductoDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM PRODUCTO WHERE 1=1 ORDER BY ID_CATEGORIA";
    private static final String SQL_DELETE = "DELETE FROM PRODUCTO WHERE ID_PRODUCTO = '";
    private static final String SQL_UPDATE = "UPDATE PRODUCTO SET ";
    private final String SQL_ADD = "INSERT INTO PRODUCTO (ID_PRODUCTO, ID_CATEGORIA, NOMBRE, DESCRIPCION, INGREDIENTES, PRECIO, RUTA_IMAGEN, RUTA_IMAGEN_ALERGIAS1, RUTA_IMAGEN_ALERGIAS2, RUTA_IMAGEN_ALERGIAS3) VALUES (";

    @Override
    public int add(Object bean) {
        MotorSQL motorSQL = new MotorSQL();
        motorSQL.connect();
        String sql = SQL_ADD;

        Producto producto = (Producto) bean;
        sql += "'" +producto.getIdProducto() + "', ";
        sql+= "'" + producto.getCategoria() + "', ";
        sql += "'" + producto.getNombre() + "', ";
        sql += "'" + producto.getDescripcion() + "', ";
        sql+= "'" + producto.getIngredientes() + "', ";
        sql += producto.getPrecio() + ", ";
        sql += "'" + producto.getRuta_Imagen() + "', ";
        sql += "'" + producto.getRuta_Imagen_Alergias1() + "', ";
        sql += "'" + producto.getRuta_Imagen_Alergias2() + "', ";
        sql += "'" + producto.getRuta_Imagen_Alergias3() + "' ";
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
    public int delete(String id ) {
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
        Producto producto = (Producto) bean;
        String sql = SQL_UPDATE;
        sql+= "ID_CATEGORIA = '" + producto.getCategoria() + "', ";
        sql += "NOMBRE='" + producto.getNombre() + "', ";
        sql += "DESCRIPCION ='" + producto.getDescripcion() + "', ";
        sql+= "INGREDIENTES ='" + producto.getIngredientes() + "', ";
        sql += "PRECIO = " + producto.getPrecio() + ", ";
        sql += "RUTA_IMAGEN='" + producto.getRuta_Imagen() + "', ";
        sql += "RUTA_IMAGEN_ALERGIAS1= '" + producto.getRuta_Imagen_Alergias1() + "', ";
        sql += "RUTA_IMAGEN_ALERGIAS2= '" + producto.getRuta_Imagen_Alergias2() + "', ";
        sql += "RUTA_IMAGEN_ALERGIAS3= '" + producto.getRuta_Imagen_Alergias3() + "' ";
        sql += "WHERE ID_PRODUCTO ='" + producto.getIdProducto()+"'";
        System.out.println(sql);

        int filasModificadas = motorSQL.execute(sql);
        motorSQL.disconnect();

        return filasModificadas;
    }

    @Override
    public ArrayList<Producto> findAll(Object bean) {  // Instance method
        ArrayList<Producto> productos = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Producto)bean).getIdProducto() != null) {
                    sql += " AND ID_PRODUCTO='" + ((Producto)bean).getIdProducto() + "'";
                }
                if (((Producto)bean).getCategoria() != null) {
                    sql += " AND ID_CATEGORIA='" + ((Producto)bean).getCategoria() + "'";
                }
                if (((Producto)bean).getNombre() != null) {
                    sql += " AND NOMBRE='" + ((Producto)bean).getNombre() + "'";
                }
                if (((Producto)bean).getDescripcion() != null) {
                    sql += " AND DESCRIPCION='" + ((Producto)bean).getDescripcion() + "'";
                }
                if (((Producto)bean).getIngredientes() != null) {
                    sql += " AND INGREDIENTES='" + ((Producto)bean).getIngredientes() + "'";
                }
                if (((Producto)bean).getPrecio() != 0) {
                    sql += " AND PRECIO='" + ((Producto)bean).getPrecio() + "'";
                }
                if (((Producto)bean).getRuta_Imagen() != null) {
                    sql += " AND RUTA_IMAGEN='" + ((Producto)bean).getRuta_Imagen() + "'";
                }
                if (((Producto)bean).getRuta_Imagen_Alergias1() != null) {
                    sql += " AND RUTA_IMAGEN_ALERGIAS1='" + ((Producto)bean).getRuta_Imagen_Alergias1() + "'";
                }
                if (((Producto)bean).getRuta_Imagen_Alergias2() != null) {
                    sql += " AND RUTA_IMAGEN_ALERGIAS2='" + ((Producto) bean).getRuta_Imagen_Alergias2() + "'";
                }
                if (((Producto)bean).getRuta_Imagen_Alergias3() != null) {
                    sql += " AND RUTA_IMAGEN_ALERGIAS3='" + ((Producto) bean).getRuta_Imagen_Alergias3() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getString("ID_PRODUCTO"));
                producto.setNombre(rs.getString("NOMBRE"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setIngredientes(rs.getString("INGREDIENTES"));
                producto.setPrecio(rs.getDouble("PRECIO"));
                producto.set_Ruta_Imagen(rs.getString("RUTA_IMAGEN"));
                producto.set_Ruta_Imagen_Alergias1(rs.getString("RUTA_IMAGEN_ALERGIAS1"));
                producto.set_Ruta_Imagen_Alergias2(rs.getString("RUTA_IMAGEN_ALERGIAS2"));
                producto.set_Ruta_Imagen_Alergias3(rs.getString("RUTA_IMAGEN_ALERGIAS3"));
                producto.setCategoria(rs.getString("ID_CATEGORIA"));

                productos.add(producto);
            }

        } catch (Exception ex) {
            productos.clear();
        } finally {
            motor.disconnect();
        }
        return productos;
    }
}

