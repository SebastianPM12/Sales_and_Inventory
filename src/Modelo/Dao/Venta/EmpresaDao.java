/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

import Modelo.DTO.Venta.Asesor;
import Modelo.DTO.Conexxion;
import Modelo.DTO.Venta.Empresa;
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
public class EmpresaDao implements Crud<Empresa, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(Empresa clase) {
        return false;

    }

    @Override
    public List read() {
        List<Empresa> asl = new ArrayList<>();
        String sql = "select * from empresa";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            while (rs.next()) {
                Empresa emp = new Empresa();
                emp.setRuc(rs.getString("Ruc"));
                emp.setRazonSocial(rs.getString("RazonSocial"));
                emp.setDireccion(rs.getString("Direccion"));
                emp.setTelefono(rs.getInt("Telefono"));
                asl.add(emp);
            }
        } catch (Exception e) {

        }
        return asl;
    }

    @Override
    public Empresa read(String id) {
        Empresa emp = new Empresa();

        String sql = "select * from empresa where ruc=?";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            while (rs.next()) {
                emp.setRuc(rs.getString("Ruc"));
                emp.setRazonSocial(rs.getString("RazonSocial"));
                emp.setDireccion(rs.getString("Direccion"));
                emp.setTelefono(rs.getInt("Telefono"));
            }
        } catch (Exception e) {

        }
        return emp;
    }

    @Override
    public boolean update(Empresa clase) {
        String sql = ("UPDATE empresa set RazonSocial = ?,Direccion=?, Telefono=? where Ruc =?");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getRazonSocial());
            p1.setString(2, clase.getDireccion());
            p1.setInt(3, clase.getTelefono());
            p1.setString(4, clase.getRuc());

            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS DE LA EMPRESA ACTUALIZADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

}
