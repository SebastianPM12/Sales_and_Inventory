/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Inventario;

import Modelo.DTO.Conexxion;
import Modelo.DTO.Inventario.CabeceraEntrada;
import Modelo.DTO.Inventario.CabeceraSalida;
import Modelo.DTO.Inventario.DetalleEntradas;
import Modelo.DTO.Inventario.PersonalInventario;
import Modelo.DTO.Inventario.Proveedor;
import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.Interfaces.Crud;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author SEBAS
 */
public class CabeceraEntradaDao implements Crud<CabeceraEntrada, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs = null;
    @Override
    public boolean create(CabeceraEntrada clase) {
        String sql="";
        sql = "Insert into cabeceraentrada(NumeroEntrada,IdProveedor,IdPersonal,Tipo,FechaEmision,ValorTotal,Igv,PrecioTotal) values (?,?,?,?,?,?,?,?)";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getNumeroDocumentoEntrada());
            p1.setString(2, clase.getProveedor().getIdProveedor());
            p1.setString(3, clase.getPersonalInventario().getIdPersonal());
            System.out.println(clase.getPersonalInventario().getIdPersonal());
            p1.setString(4, clase.getTipo());
            p1.setDate(5, new Date(clase.getFechaEmision().getTime()));
            p1.setDouble(6, clase.getValorTotal());
            p1.setDouble(7, clase.getIgv());
            p1.setDouble(8, clase.getPrecioTotal());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "ENTRADA REGISTRADA");
        } catch (SQLException e) {
            System.out.println("eRROR ENTRADA");
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        List<CabeceraEntrada> cbe = new ArrayList<>();
        String sql = "select * from cabeceraentrada";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            ProveedorDao pd = new ProveedorDao();
            PersonalInventariodao pid = new PersonalInventariodao();
            DetalleEntradasDao dldao = new DetalleEntradasDao();
            while (rs.next()) {
                CabeceraEntrada cds = new CabeceraEntrada();
                PersonalInventario pi = new PersonalInventario();
                Proveedor p = new Proveedor();
                cds.setNumeroDocumentoEntrada(rs.getString("NumeroEntrada"));
                String idP = rs.getString("IdProveedor");
                p = pd.read(idP);
                cds.setProveedor(p);
                String idIn = rs.getString("IdPersonal");
                pi = pid.read(idIn);
                cds.setPersonalInventario(pi);
                cds.setFechaEmision(new Date(rs.getDate("FechaEmision").getTime()));
                cds.setDetalleEntradas(dldao.readDetalles(cds.getNumeroDocumentoEntrada()));
                cds.setValorTotal(rs.getDouble("ValorTotal"));
                cds.setIgv(rs.getDouble("igv"));
                cds.setPrecioTotal(rs.getDouble("PrecioTotal"));
                cbe.add(cds);
            }
        } catch (SQLException e) {
            System.out.println("ga!!!!!!");
        }
        return cbe;
    }

    @Override
    public CabeceraEntrada read(String id) {
        CabeceraEntrada cds = new CabeceraEntrada();
        String sql = "select * from cabeceraentrada where NumeroEntrada=?";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            ProveedorDao pd = new ProveedorDao();
            PersonalInventariodao pid = new PersonalInventariodao();
            DetalleEntradasDao dldao = new DetalleEntradasDao();
            while (rs.next()) {
                PersonalInventario pi = new PersonalInventario();
                Proveedor p = new Proveedor();
                cds.setNumeroDocumentoEntrada(rs.getString("NumeroEntrada"));
                String idP = rs.getString("IdProveedor");
                p = pd.read(idP);
                cds.setProveedor(p);
                String idIn = rs.getString("IdPersonal");
                pi = pid.read(idIn);
                cds.setTipo(rs.getString("Tipo"));
                cds.setPersonalInventario(pi);
                cds.setFechaEmision(new Date(rs.getDate("FechaEmision").getTime()));
                cds.setDetalleEntradas(dldao.readDetalles(cds.getNumeroDocumentoEntrada()));
                cds.setValorTotal(rs.getDouble("ValorTotal"));
                cds.setIgv(rs.getDouble("igv"));
                cds.setPrecioTotal(rs.getDouble("PrecioTotal"));
            }
        } catch (SQLException e) {
            System.out.println("ga!!!!!!");
        }
        return cds;
    }

    @Override
    public boolean update(CabeceraEntrada clase) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

}
