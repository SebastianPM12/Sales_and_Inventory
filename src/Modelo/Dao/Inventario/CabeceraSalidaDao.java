/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Inventario;

import Modelo.DTO.Conexxion;
import Modelo.DTO.Inventario.CabeceraEntrada;
import Modelo.DTO.Inventario.CabeceraSalida;
import Modelo.DTO.Inventario.DetalleEntradas;
import Modelo.DTO.Inventario.DetalleSalidas;
import Modelo.DTO.Inventario.PersonalInventario;
import Modelo.DTO.Venta.Asesor;
import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.DTO.Venta.Cliente;
import Modelo.DTO.Venta.Comision;
import Modelo.DTO.Venta.Empresa;
import Modelo.DTO.Venta.Vendedor;
import Modelo.Dao.Venta.AsesorDao;
import Modelo.Dao.Venta.CabeceraDeVentaDao;
import Modelo.Dao.Venta.ClienteDao;
import Modelo.Dao.Venta.ComisionDao;
import Modelo.Dao.Venta.DetalleDeVentaDao;
import Modelo.Dao.Venta.EmpresaDao;
import Modelo.Dao.Venta.VendedorDao;
import Modelo.Interfaces.Crud;
import java.sql.CallableStatement;
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
public class CabeceraSalidaDao implements Crud<CabeceraSalida, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1 ;
    ResultSet rs = null;

    @Override
    public boolean create(CabeceraSalida clase) {
        String sql = "";
        sql = "Insert into cabecerasalida(NumeroSalida,OrdenDeVenta,IdPersonal,FechaEmision,ValorTotal,Igv,PrecioTotal) values (?,?,?,?,?,?,?)";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getNumeroDocumentoSalida());
            p1.setString(2, clase.getOrdenDeVenta());
            p1.setString(3, clase.getPersonalInventario().getIdPersonal());
            p1.setDate(4, new Date(clase.getFechaEmision().getTime()));
            p1.setDouble(5, clase.getValorTotal());
            p1.setDouble(6, clase.getIgv());
            p1.setDouble(7, clase.getPrecioTotal());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "SALIDA REGISTRADA");
        } catch (SQLException e) {
            System.out.println("eRROR salida");
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        List<CabeceraSalida> cbe = new ArrayList<>();
        String sql = "select * from cabecerasalida";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            PersonalInventariodao pid = new PersonalInventariodao();
            DetalleSalidasDao dldao = new DetalleSalidasDao();
            while (rs.next()) {
                CabeceraSalida cds = new CabeceraSalida();
                PersonalInventario pi = new PersonalInventario();
                cds.setNumeroDocumentoSalida(rs.getString("NumeroSalida"));
                cds.setOrdenDeVenta(rs.getString("OrdenDeVenta"));
                String idIn = rs.getString("IdPersonal");
                pi = pid.read(idIn);
                cds.setPersonalInventario(pi);
                cds.setFechaEmision(new Date(rs.getDate("FechaEmision").getTime()));
                cds.setDetalleSalidas(dldao.readDetalles(cds.getNumeroDocumentoSalida()));
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
    public CabeceraSalida read(String id) {
        CabeceraSalida cds = new CabeceraSalida();
        String sql = "select * from cabecerasalida where NumeroSalida=?;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            PersonalInventariodao pid = new PersonalInventariodao();
            DetalleSalidasDao dldao = new DetalleSalidasDao();
            while (rs.next()) {
                PersonalInventario pi = new PersonalInventario();
                cds.setNumeroDocumentoSalida(rs.getString("NumeroSalida"));
                cds.setOrdenDeVenta(rs.getString("OrdenDeVenta"));
                String idIn = rs.getString("IdPersonal");
                pi = pid.read(idIn);
                cds.setPersonalInventario(pi);
                cds.setFechaEmision(new Date(rs.getDate("FechaEmision").getTime()));
                cds.setDetalleSalidas(dldao.readDetalles(cds.getNumeroDocumentoSalida()));
                cds.setValorTotal(rs.getDouble("ValorTotal"));
                cds.setIgv(rs.getDouble("Igv"));
                cds.setPrecioTotal(rs.getDouble("PrecioTotal"));
            }
        } catch (Exception e) {
            System.out.println("GA");
        }
        return cds;
    }

    @Override
    public boolean update(CabeceraSalida clase) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

}
