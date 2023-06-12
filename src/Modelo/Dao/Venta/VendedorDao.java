/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

import Modelo.DTO.Venta.Asesor;
import Modelo.DTO.Conexxion;
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
public class VendedorDao implements Crud<Vendedor, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(Vendedor clase) {
        String sql = ("Insert into Vendedores (IdVendedor ,Nombres,Apellidos,Celular,Posicion,Dni) values(?,?,?,?,?,?);");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getIdVendedor());
            p1.setString(2, clase.getNombres());
            p1.setString(3, clase.getApellidos());
            p1.setInt(4, clase.getCelular());
            p1.setString(5, clase.getPosicion());
            p1.setInt(6, clase.getDni());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "VENDEDOR REGISTRADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;

    }

    @Override
    public List read() {
        List<Vendedor> vnl = new ArrayList<>();
        String sql = "select * from Vendedores;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            while (rs.next()) {
                Vendedor vn = new Vendedor();
                vn.setIdVendedor(rs.getString("IdVendedor"));
                vn.setNombres(rs.getString("Nombres"));
                vn.setApellidos(rs.getString("Apellidos"));
                vn.setCelular(rs.getInt("Celular"));
                vn.setPosicion(rs.getString("Posicion"));
                vn.setDni(rs.getInt("Dni"));
                vnl.add(vn);
            }
        } catch (Exception e) {

        }
        return vnl;
    }

    @Override
    public Vendedor read(String id) {
        Vendedor vn = new Vendedor();
        String sql = "select * from Vendedores where IdVendedor = ?;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            while (rs.next()) {
                vn.setIdVendedor(rs.getString("IdVendedor"));
                vn.setNombres(rs.getString("Nombres"));
                vn.setApellidos(rs.getString("Apellidos"));
                vn.setCelular(rs.getInt("Celular"));
                vn.setPosicion(rs.getString("Posicion"));
                vn.setDni(rs.getInt("Dni"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return vn;
    }

    @Override
    public boolean update(Vendedor clase) {
        String sql = ("UPDATE Vendedores set Nombres= ?,Apellidos=?, Celular=?, Posicion=? where IdVendedor=?;");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getNombres());
            p1.setString(2, clase.getApellidos());
            p1.setInt(3, clase.getCelular());
            p1.setString(4, clase.getPosicion());
            p1.setString(5, clase.getIdVendedor());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "VENDEDOR ACTUALIZADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = ("delete from Vendedores where IdVendedor=?;");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "VENDEDOR ELIMINADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    public Vendedor checkVendedores(String id, int dni, String posicion) {
        Vendedor vn = new Vendedor();
        String sql = "select * from Vendedores where IdVendedor = ? and Dni=? and Posicion=?;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            p1.setInt(2, dni);
            p1.setString(3, posicion);
            rs = p1.executeQuery();
            while (rs.next()) {
                vn.setIdVendedor(rs.getString("IdVendedor"));
                vn.setNombres(rs.getString("Nombres"));
                vn.setApellidos(rs.getString("Apellidos"));
                vn.setCelular(rs.getInt("Celular"));
                vn.setPosicion(rs.getString("Posicion"));
                vn.setDni(rs.getInt("Dni"));
            }
        } catch (Exception e) {
        }
        return vn;
    }
}
