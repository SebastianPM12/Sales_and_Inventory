/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

import Modelo.DTO.Conexxion;
import Modelo.DTO.Venta.Producto;
import Modelo.DTO.Venta.Vendedor;
import Modelo.Interfaces.Crud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author SEBAS
 */
public class ProductoDao implements Crud<Producto, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(Producto clase) {
        String sql = ("Insert into productos (Codigo ,PrecioUnitario,Valor,Capacidad,Tipo,Descripcion,Largo,Ancho,Alto) values(?,?,?,?,?,?,?,?,?);");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getCodigo());
            p1.setDouble(2, clase.getPrecioUnitario());
            p1.setDouble(3, clase.getValor());
            p1.setString(4, clase.getCapacidad());
            p1.setString(5, clase.getTipo());
            p1.setString(6, clase.getDescripcion());
            p1.setString(7, clase.getLargo());
            p1.setString(8, clase.getAncho());
            p1.setString(9, clase.getAlto());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "PRODUCTO REGISTRADO");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        List<Producto> pcl = new ArrayList<>();
        String sql = "select * from productos;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setCodigo(rs.getString("Codigo"));
                p.setPrecioUnitario(rs.getDouble("PrecioUnitario"));
                p.setValor(rs.getDouble("Valor"));
                p.setCapacidad(rs.getString("Capacidad"));
                p.setTipo(rs.getString("Tipo"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setLargo(rs.getString("Largo"));
                p.setAncho(rs.getString("Ancho"));
                p.setAlto(rs.getString("Alto"));
                pcl.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return pcl;
    }

    @Override
    public Producto read(String id) {
        Producto p = new Producto();
        String sql = "select * from productos where Codigo = ?;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            while (rs.next()) {
                p.setCodigo(rs.getString("Codigo"));
                p.setPrecioUnitario(rs.getDouble("PrecioUnitario"));
                p.setValor(rs.getDouble("Valor"));
                p.setCapacidad(rs.getString("Capacidad"));
                p.setTipo(rs.getString("Tipo"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setLargo(rs.getString("Largo"));
                p.setAncho(rs.getString("Ancho"));
                p.setAlto(rs.getString("Alto"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return p;
    }

    @Override
    public boolean update(Producto clase) {
        String sql = ("UPDATE productos set PrecioUnitario= ?,Valor=?, Capacidad=?, Tipo=?, Descripcion=?, Largo=?,Ancho=?, Alto=? where Codigo=?;");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setDouble(1, clase.getPrecioUnitario());
            p1.setDouble(2, clase.getValor());
            p1.setString(3, clase.getCapacidad());
            p1.setString(4, clase.getTipo());
            p1.setString(5, clase.getDescripcion());
            p1.setString(6, clase.getLargo());
            p1.setString(7, clase.getAncho());
            p1.setString(8, clase.getLargo());
            p1.setString(9, clase.getCodigo());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "PRODUCTO ACTUALIZADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = ("delete from productos where Codigo=?;");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }
}
