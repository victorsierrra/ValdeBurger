package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductoDao implements IDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM PRODUCTO WHERE 1=1 ";

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
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getString("ID_CATEGORIA"));
                producto.setCategoria(categoria);

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

