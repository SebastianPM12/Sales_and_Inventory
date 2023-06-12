/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

import Modelo.DTO.Venta.Asesor;
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
public class AsesorDao implements Crud<Asesor, Integer> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(Asesor clase) {
        String sql = ("Insert into asesores (DniAsesor,Nombres,Apellidos,Celular) values(?,?,?,?);");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);

            p1.setInt(1, clase.getDni());
            p1.setString(2, clase.getNombres());
            p1.setString(3, clase.getApellidos());
            p1.setInt(4, clase.getCelular());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "ASESOR REGISTRADO");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        List<Asesor> asl = new ArrayList<>();
        String sql = "select * from asesores";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            while (rs.next()) {
                Asesor as = new Asesor();
                as.setDni(rs.getInt("DniAsesor"));
                as.setNombres(rs.getString("Nombres"));
                as.setApellidos(rs.getString("Apellidos"));
                as.setCelular(rs.getInt("Celular"));
                asl.add(as);
            }
        } catch (Exception e) {

        }
        return asl;
    }

    @Override
    public Asesor read(Integer id) {
        Asesor a = new Asesor();
        String sql = "select * from asesores where DniAsesor = ?";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setInt(1, id);
            rs = p1.executeQuery();
            while (rs.next()) {
                a.setDni(rs.getInt("DniAsesor"));
                a.setNombres(rs.getString("Nombres"));
                a.setApellidos(rs.getString("Apellidos"));
                a.setCelular(rs.getInt("Celular"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "EL VENDEDOR NO ESTA REGISTRADO");

        }

        return a;
    }

    @Override
    public boolean update(Asesor clase) {
        String sql = ("UPDATE asesores set Nombres= ?,Apellidos=?, Celular=? where DniAsesor =?");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getNombres());
            p1.setString(2, clase.getApellidos());
            p1.setInt(3, clase.getCelular());
            p1.setInt(4, clase.getDni());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "ASESOR ACTUALIZADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = ("delete from asesores where DniAsesor=?");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setInt(1, id);
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "ASESOR ELIMINADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

}
