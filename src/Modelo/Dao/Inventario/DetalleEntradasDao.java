/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Inventario;

import Modelo.DTO.Conexxion;
import Modelo.DTO.Inventario.DetalleEntradas;
import Modelo.DTO.Inventario.DetalleSalidas;
import Modelo.DTO.Inventario.Insumos;
import Modelo.Interfaces.Crud;
import java.sql.CallableStatement;
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
public class DetalleEntradasDao implements Crud<DetalleEntradas, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    CallableStatement cs;

    ResultSet rs;

    @Override
    public boolean create(DetalleEntradas clase) {
        String sql = "Insert into detalleentrada(NumeroEntrada,CodigoInsumo,Descripcion,Unidad,Cantidad,Valor,ValorTotal) values (?,?,?,?,?,?,?)";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getCabeceraEntrada());
            p1.setString(2, clase.getInsumo().getCodigoInsumo());
            p1.setString(3, clase.getInsumo().getDescripcion());
            p1.setString(4, clase.getInsumo().getUnidad());
            p1.setInt(5, clase.getCantidad());
            p1.setDouble(6, clase.getInsumo().getCosto());
            p1.setDouble(7, clase.getValorTotal());

            String sql1 = "{call agregarEntradas(?,?)}";
            cs = cn.prepareCall(sql1);
            cs.setInt(1, clase.getCantidad());
            cs.setString(2, clase.getInsumo().getCodigoInsumo());
            p1.executeUpdate();
            cs.execute();
        } catch (Exception e) {
            System.out.println("eRROR Detalle Entrada");
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        List<DetalleSalidas> dvh = new ArrayList<>();
        return dvh;
    }

    @Override
    public DetalleEntradas read(String id) {
        DetalleEntradas d = new DetalleEntradas();
        return d;
    }

    @Override
    public boolean update(DetalleEntradas clase) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public List readDetalles(String numeroDeEntrada) {
        List<DetalleEntradas> dvl = new ArrayList<>();
        String sql = "select * from detalleentrada where NumeroEntrada =?;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, numeroDeEntrada);
            rs = p1.executeQuery();
            Insumos i = new Insumos();
            InsumosDao idao = new InsumosDao();
            while (rs.next()) {
                DetalleEntradas ddv = new DetalleEntradas();
                ddv.setCabeceraEntrada(rs.getString("NumeroEntrada"));
                String idp = (rs.getString("CodigoInsumo"));
                i = idao.read(idp);
                ddv.setInsumo(i);
                ddv.setCantidad(rs.getInt("Cantidad"));
                ddv.setValorTotal(rs.getDouble("ValorTotal"));
                dvl.add(ddv);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return dvl;
    }

}
