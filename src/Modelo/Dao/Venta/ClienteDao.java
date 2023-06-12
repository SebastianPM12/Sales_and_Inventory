/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.DTO.Venta.Cliente;
import Modelo.DTO.Conexxion;
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
public class ClienteDao implements Crud<Cliente, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(Cliente clase) {
        String sql = ("Insert into clientes(DniRuc,Nombres,Apellidos,Celular) values(?,?,?,?)");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getDniRuc());
            p1.setString(2, clase.getNombres());
            p1.setString(3, clase.getApellidos());
            p1.setInt(4, clase.getCelular());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "CLIENTE REGISTRADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;

    }

    @Override
    public List read() {
        List<Cliente> cnl = new ArrayList<>();
        String sql = "select * from clientes";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            while (rs.next()) {
                Cliente cnt = new Cliente();
                cnt.setDniRuc(rs.getString("DniRuc"));
                cnt.setNombres(rs.getString("Nombres"));
                cnt.setApellidos(rs.getString("Apellidos"));
                cnt.setCelular(rs.getInt("Celular"));
                cnl.add(cnt);
            }
        } catch (Exception e) {

        }
        return cnl;
    }

    @Override
    public Cliente read(String id) {
        Cliente c = new Cliente();
        String sql = "select * from clientes where DniRuc = ?";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            while (rs.next()) {
                c.setDniRuc(rs.getString("DniRuc"));
                c.setNombres(rs.getString("Nombres"));
                c.setApellidos(rs.getString("Apellidos"));
                c.setCelular(rs.getInt("Celular"));
            }
        } catch (Exception e) {
        }

        return c;
    }

    @Override
    public boolean update(Cliente clase) {
        String sql = ("UPDATE clientes set Nombres= ?,Apellidos=?, Celular=? where DniRuc =?");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getNombres());
            p1.setString(2, clase.getApellidos());
            p1.setInt(3, clase.getCelular());
            p1.setString(4, clase.getDniRuc());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "CLIENTE ACTUALIZADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;

    }

    @Override
    public boolean delete(String id) {

        String sql = ("delete from clientes where DniRuc=?");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "CLIENTE ELIMINADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;

    }

}
