/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Inventario;

import Modelo.DTO.Conexxion;
import Modelo.DTO.Inventario.PersonalInventario;
import Modelo.DTO.Venta.Cliente;
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
public class PersonalInventariodao implements Crud<PersonalInventario, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(PersonalInventario clase) {
        String sql = ("Insert into personalinventario(IdPersonal,Nombres,Apellidos,Dni,Celular,Posicion) values(?,?,?,?,?,?)");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getIdPersonal());
            p1.setString(2, clase.getNombres());
            p1.setString(3, clase.getApellidos());
            p1.setInt(4, clase.getDni());
            p1.setInt(5, clase.getCelular());
            p1.setString(6, clase.getPosicion());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "PERSONAL REGISTRADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        List<PersonalInventario> cnl = new ArrayList<>();
        String sql = "select * from personalinventario";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            while (rs.next()) {
                PersonalInventario cnt = new PersonalInventario();
                cnt.setIdPersonal(rs.getString("IdPersonal"));
                cnt.setNombres(rs.getString("Nombres"));
                cnt.setApellidos(rs.getString("Apellidos"));
                cnt.setDni(rs.getInt("Dni"));
                cnt.setCelular(rs.getInt("Celular"));
                cnt.setPosicion(rs.getString("Posicion"));
                cnl.add(cnt);
            }
        } catch (Exception e) {

        }
        return cnl;
    }

    @Override
    public PersonalInventario read(String id) {
        PersonalInventario cnt = new PersonalInventario();
        String sql = "select * from personalinventario where IdPersonal = ?";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            while (rs.next()) {
                cnt.setIdPersonal(rs.getString("IdPersonal"));
                cnt.setNombres(rs.getString("Nombres"));
                cnt.setApellidos(rs.getString("Apellidos"));
                cnt.setDni(rs.getInt("Dni"));
                cnt.setCelular(rs.getInt("Celular"));
                cnt.setPosicion(rs.getString("Posicion"));
            }
        } catch (Exception e) {
        }

        return cnt;

    }

    @Override
    public boolean update(PersonalInventario clase) {
        String sql = ("UPDATE personalinventario set Nombres=?,Apellidos=?,Dni=?,Celular=?,Posicion=? where IdPersonal =?");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getNombres());
            p1.setString(2, clase.getApellidos());
            p1.setInt(3, clase.getDni());
            p1.setInt(4, clase.getCelular());
            p1.setString(5, clase.getPosicion());
            p1.setString(6, clase.getIdPersonal());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "PERSONAL ACTUALIZADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = ("delete from personalinventario where IdPersonal=?");
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "PERSONAL ELIMINADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

}
