/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

import Modelo.DTO.Venta.Comision;
import Modelo.DTO.Conexxion;
import Modelo.DTO.Venta.Vendedor;
import Modelo.Interfaces.Crud;
import java.sql.Connection;
import java.sql.Date;
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
public class ComisionDao implements Crud<Comision, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(Comision clase) {
        String sql = "Insert into comision(OrdenDeVenta,ComisionVendedor,ComisionAsesor) values (?,?,?)";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getCabeceraDeVenta());
            p1.setDouble(2, clase.getComisionVendedor());
            p1.setDouble(3, clase.getComisionAsesor());
            p1.executeUpdate();
//            JOptionPane.showMessageDialog(null, "COMISION REGISTRADO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        List<Comision> vnl = new ArrayList<>();
        String sql = "select * from comision";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            while (rs.next()) {
                Comision vn = new Comision();
                vn.setIdComision(rs.getInt("IdComision"));
                vn.setCabeceraDeVenta(rs.getString("OrdenDeVenta"));
                vn.setComisionVendedor(rs.getDouble("ComisionVendedor"));
                vn.setComisionAsesor(rs.getDouble("ComisionAsesor"));
                vnl.add(vn);
            }
        } catch (Exception e) {

        }
        return vnl;
    }

    @Override
    public Comision read(String id) {
        Comision cm = new Comision();
        String sql = "select * from comision where OrdenDeVenta=?";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            while (rs.next()) {
                cm.setIdComision(rs.getInt("IdComision"));
                cm.setCabeceraDeVenta(rs.getString("OrdenDeVenta"));
                cm.setComisionVendedor(rs.getDouble("ComisionVendedor"));
                cm.setComisionAsesor(rs.getDouble("ComisionAsesor"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return cm;
    }

    @Override
    public boolean update(Comision clase) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

}
