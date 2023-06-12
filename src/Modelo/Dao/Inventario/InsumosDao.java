/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Inventario;

import Modelo.DTO.Conexxion;
import Modelo.DTO.Inventario.Insumos;
import Modelo.DTO.Inventario.Proveedor;
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
public class InsumosDao implements Crud<Insumos, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(Insumos clase) {
        String sql = ("Insert into insumos (CodigoInsumo ,Costo,Idproveedor,Descripcion,Tipo,Area,Unidad,Ingresos,Salidas,Stock) values(?,?,?,?,?,?,?,?,?,?);");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getCodigoInsumo());
            p1.setDouble(2, clase.getCosto());
            p1.setString(3, clase.getProveedor().getIdProveedor());
            p1.setString(4, clase.getDescripcion());
            p1.setString(5, clase.getTipo());
            p1.setString(6, clase.getArea());
            p1.setString(7, clase.getUnidad());
            p1.setInt(8, clase.getEntradas());
            p1.setInt(9, clase.getSalidas());
            p1.setInt(10, clase.getStock());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "INSUMO REGISTRADO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        List<Insumos> pcl = new ArrayList<>();
        String sql = "select * from insumos;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            ProveedorDao pdao = new ProveedorDao();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                Insumos in = new Insumos();
                in.setCodigoInsumo(rs.getString("CodigoInsumo"));
                in.setCosto(rs.getDouble("Costo"));
                String idp = rs.getString("Idproveedor");
                p = pdao.read(idp);
                in.setProveedor(p);
                in.setDescripcion(rs.getString("Descripcion"));
                in.setTipo(rs.getString("Tipo"));
                in.setArea(rs.getString("Area"));
                in.setUnidad(rs.getString("Unidad"));
                in.setEntradas(rs.getInt("Ingresos"));
                in.setSalidas(rs.getInt("Salidas"));
                in.setStock(rs.getInt("Stock"));
                pcl.add(in);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return pcl;
    }

    @Override
    public Insumos read(String id) {
        Insumos in = new Insumos();
        String sql = "select * from insumos where CodigoInsumo = ?;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            ProveedorDao pdao = new ProveedorDao();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                in.setCodigoInsumo(rs.getString("CodigoInsumo"));
                in.setCosto(rs.getDouble("Costo"));
                String idp = rs.getString("Idproveedor");
                p = pdao.read(idp);
                in.setProveedor(p);
                in.setDescripcion(rs.getString("Descripcion"));
                in.setTipo(rs.getString("Tipo"));
                in.setArea(rs.getString("Area"));
                in.setUnidad(rs.getString("Unidad"));
                in.setEntradas(rs.getInt("Ingresos"));
                in.setSalidas(rs.getInt("Salidas"));
                in.setStock(rs.getInt("Stock"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return in;
    }

    @Override
    public boolean update(Insumos clase) {
        String sql = ("UPDATE insumos set Costo=?,Idproveedor=?,Descripcion=?,Tipo=?,Area=?,Unidad=?,Ingresos=?,Salidas=?,Stock=? where CodigoInsumo=?;");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setDouble(1, clase.getCosto());
            p1.setString(2, clase.getProveedor().getIdProveedor());
            p1.setString(3, clase.getDescripcion());
            p1.setString(4, clase.getTipo());
            p1.setString(5, clase.getArea());
            p1.setString(6, clase.getUnidad());
            p1.setInt(7, clase.getEntradas());
            p1.setInt(8, clase.getSalidas());
            p1.setInt(9, clase.getStock());
            p1.setString(10, clase.getCodigoInsumo());

            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "INSUMO ACTUALIZADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = ("delete from insumos where CodigoInsumo=?;");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "INSUMO ELIMINADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    public List listInventario() {
        List<Insumos> pcl = new ArrayList<>();
        String sql = "select * from insumos;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            while (rs.next()) {
                Insumos in = new Insumos();
                in.setCodigoInsumo(rs.getString("CodigoInsumo"));
                in.setCosto(rs.getDouble("Costo"));
                in.getProveedor().setIdProveedor(rs.getString("Idproveedor"));
                in.setDescripcion(rs.getString("Descripcion"));
                in.setTipo(rs.getString("Tipo"));
                in.setArea(rs.getString("Area"));
                in.setUnidad(rs.getString("Unidad"));
                in.setEntradas(rs.getInt("Ingresos"));
                in.setSalidas(rs.getInt("Salidas"));
                in.setStock(rs.getInt("Stock"));

                pcl.add(in);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return pcl;
    }

}
