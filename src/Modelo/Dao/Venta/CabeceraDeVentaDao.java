/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

import Modelo.DTO.Venta.Asesor;
import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.DTO.Venta.Cliente;
import Modelo.DTO.Venta.Comision;
import Modelo.DTO.Conexxion;
import Modelo.DTO.Venta.Empresa;
import Modelo.DTO.Venta.Vendedor;
import Modelo.Interfaces.Crud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author SEBAS
 */
public class CabeceraDeVentaDao implements Crud<CabeceraDeVenta, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    @Override
    public boolean create(CabeceraDeVenta clase) {
        String sql = "";
        sql = "Insert into cabeceradeventas(OrdenDeVenta,DniRuc,Ruc,NombreCliente,FechaEmision,DniAsesor,IdVendedor,ValorTotal,Igv,PrecioTotal) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getOrdendeventa());
            p1.setString(2, clase.getCliente().getDniRuc());
            p1.setString(3, clase.getEmpresa().getRuc());
            p1.setString(4, clase.getCliente().getNombres());
            p1.setDate(5, new Date(clase.getFechaEmision().getTime()));
            p1.setInt(6,  clase.getAsesor().getDni());
            p1.setString(7, clase.getVendedor().getIdVendedor());
            p1.setDouble(8, clase.getValorTotal());
            p1.setDouble(9, clase.getIgv());
            p1.setDouble(10, clase.getPrecioTotal());
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null, "CABEZERA REGISTRADO");
        } catch (SQLException e) {
            System.out.println("eRROR CABEZERA");
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    @Override
    public List read() {
        List<CabeceraDeVenta> cbv = new ArrayList<>();
        String sql = "select * from cabeceradeventas ";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs = p1.executeQuery();
            ClienteDao cld = new ClienteDao();
            EmpresaDao empd = new EmpresaDao();
            AsesorDao asd = new AsesorDao();
            VendedorDao vdo = new VendedorDao();
            ComisionDao cd = new ComisionDao();
            DetalleDeVentaDao dldao = new DetalleDeVentaDao();
            while (rs.next()) {
                CabeceraDeVenta cdv = new CabeceraDeVenta();
                Cliente c = new Cliente();
                Empresa p = new Empresa();
                Asesor a = new Asesor();
                Vendedor v = new Vendedor();
                Comision cm = new Comision();
                cdv.setOrdendeventa(rs.getString("OrdenDeVenta"));
                String dnirucCliente = rs.getString("DniRuc");
                c = cld.read(dnirucCliente);
                cdv.setCliente(c);
                String rucEmpresa = rs.getString("Ruc");
                p = empd.read(rucEmpresa);
                cdv.setEmpresa(p);
                cdv.setFechaEmision(new Date(rs.getDate("FechaEmision").getTime()));
                int dniAsesor = rs.getInt("DniAsesor");
                a = asd.read(dniAsesor);
                cdv.setAsesor(a);
                String idVendedor = rs.getString("IdVendedor");
                v = vdo.read(idVendedor);
                cdv.setVendedor(v);
                cdv.setDeatallesDeVenta(dldao.readDetalles(cdv.getOrdendeventa()));
                cdv.setValorTotal(rs.getDouble("ValorTotal"));
                cdv.setIgv(rs.getDouble("igv"));
                cdv.setPrecioTotal(rs.getDouble("PrecioTotal"));
                cbv.add(cdv);
            }
        } catch (SQLException e) {
            System.out.println("ga!!!!!!");
        }
        return cbv;
    }

    @Override
    public CabeceraDeVenta read(String id) {
        CabeceraDeVenta cdv = new CabeceraDeVenta();
        String sql = "select * from cabeceradeventas where OrdenDeVenta=?;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, id);
            rs = p1.executeQuery();
            ClienteDao cld = new ClienteDao();
            EmpresaDao empd = new EmpresaDao();
            AsesorDao asd = new AsesorDao();
            VendedorDao vdo = new VendedorDao();
            ComisionDao cd = new ComisionDao();
            DetalleDeVentaDao dldao = new DetalleDeVentaDao();
            while (rs.next()) {
                Cliente c = new Cliente();
                Empresa p = new Empresa();
                Asesor a = new Asesor();
                Vendedor v = new Vendedor();
                Comision cm = new Comision();
                cdv.setOrdendeventa(rs.getString("OrdenDeVenta"));
                String dnirucCliente = rs.getString("DniRuc");
                c = cld.read(dnirucCliente);
                cdv.setCliente(c);
                String rucEmpresa = rs.getString("Ruc");
                p = empd.read(rucEmpresa);
                cdv.setEmpresa(p);
                cdv.setFechaEmision(new Date(rs.getDate("FechaEmision").getTime()));
                int dniAsesor = rs.getInt("DniAsesor");
                a = asd.read(dniAsesor);
                cdv.setAsesor(a);
                String idVendedor = rs.getString("IdVendedor");
                v = vdo.read(idVendedor);
                cdv.setValorTotal(rs.getDouble("ValorTotal"));
                cdv.setIgv(rs.getDouble("Igv"));
                cdv.setPrecioTotal(rs.getDouble("PrecioTotal"));
                cdv.setVendedor(v);
                cdv.setDeatallesDeVenta(dldao.readDetalles(cdv.getOrdendeventa()));
            }
        } catch (Exception e) {
        }
        return cdv;
    }

    @Override
    public boolean update(CabeceraDeVenta clase) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

  
   
}
