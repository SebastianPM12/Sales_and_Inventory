/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

import Modelo.DTO.Conexxion;
import Modelo.DTO.Venta.DetalleDeVenta;
import Modelo.DTO.Venta.Producto;
import Modelo.Interfaces.Crud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author SEBAS
 */
public class DetalleDeVentaDao implements Crud<DetalleDeVenta, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(DetalleDeVenta clase) {
        String sql = "Insert into detalleVentas(OrdenDeVenta,Codigo,Descripcion,Cantidad,Abono,AbonoP,PorCobrar,PrecioUnitario,PrecioTotal) values (?,?,?,?,?,?,?,?,?)";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getIdCabezeraDeVenta());
            p1.setString(2, clase.getProducto().getCodigo());
            p1.setString(3, clase.getProducto().getDescripcion());
            p1.setInt(4, clase.getCantidad());
            p1.setDouble(5, clase.getAbono());
            p1.setString(6, clase.getAbonoP());
            p1.setDouble(7, clase.getPorCobrar());
            p1.setDouble(8, clase.getProducto().getPrecioUnitario());
            p1.setDouble(9, clase.getPrecioTotal());
            p1.executeUpdate();
        } catch (Exception e) {
            System.out.println("eRROR Detalle");

            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DetalleDeVenta read(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(DetalleDeVenta clase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List readDetalles(String ordenDeVenta) {
        List<DetalleDeVenta> dvl = new ArrayList<>();
        String sql = "select * from detalleventas where OrdenDeVenta =?;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, ordenDeVenta);
            rs = p1.executeQuery();
            Producto p = new Producto();
            ProductoDao pdao = new ProductoDao();
            while (rs.next()) {
                DetalleDeVenta ddv = new DetalleDeVenta();
                ddv.setIdCabezeraDeVenta(rs.getString("OrdenDeVenta"));
                String idp = (rs.getString("Codigo"));
                p = pdao.read(idp);
                ddv.setProducto(p);
                ddv.setCantidad(rs.getInt("Cantidad"));
                ddv.setAbono(rs.getDouble("Abono"));
                ddv.setAbonoP(rs.getString("AbonoP"));
                ddv.setPorCobrar(rs.getDouble("PorCobrar"));
                ddv.setPrecioTotal(rs.getDouble("PrecioTotal"));
                dvl.add(ddv);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return dvl;
    }

}
