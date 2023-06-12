/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Inventario;

import Modelo.DTO.Conexxion;
import Modelo.DTO.Inventario.Proveedor;
import Modelo.DTO.Venta.Vendedor;
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
public class ProveedorDao implements Crud<Proveedor, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(Proveedor clase) {
        String sql = ("Insert into proveedor (IdProveedor ,NombreEmpresa,Direccion,Celular) values(?,?,?,?);");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getIdProveedor());
            p1.setString(2, clase.getNombreDeEmpresa());
            p1.setString(3, clase.getDireccion());
            p1.setInt(4, clase.getCelular());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "PROVEEDOR REGISTRADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        List<Proveedor> vnl = new ArrayList<>();
        String sql = "select * from proveedor;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            while (rs.next()) {
                Proveedor vn = new Proveedor();
                vn.setIdProveedor(rs.getString("IdProveedor"));
                vn.setNombreDeEmpresa(rs.getString("NombreEmpresa"));
                vn.setDireccion(rs.getString("Direccion"));
                vn.setCelular(rs.getInt("Celular"));
                vnl.add(vn);
            }
        } catch (Exception e) {

        }
        return vnl;

    }

    @Override
    public Proveedor read(String id) {
        Proveedor vn = new Proveedor();
        String sql = "select * from proveedor where IdProveedor = ?;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            while (rs.next()) {
                vn.setIdProveedor(rs.getString("IdProveedor"));
                vn.setNombreDeEmpresa(rs.getString("NombreEmpresa"));
                vn.setDireccion(rs.getString("Direccion"));
                vn.setCelular(rs.getInt("Celular"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return vn;
    }

    @Override
    public boolean update(Proveedor clase) {
        String sql = ("UPDATE proveedor set NombreEmpresa=?,Direccion=?,Celular=? where IdProveedor = ?;");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getNombreDeEmpresa());
            p1.setString(2, clase.getDireccion());
            p1.setInt(3, clase.getCelular());
            p1.setString(4, clase.getIdProveedor());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "PROVEEDOR ACTUALIZADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;

    }

    @Override
    public boolean delete(String id) {
          String sql = ("delete from proveedor where IdProveedor=?;");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "PROVEEDOR ELIMINADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;

    }

}
