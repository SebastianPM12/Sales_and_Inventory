/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Inventario;

import Modelo.DTO.Conexxion;
import Modelo.DTO.Inventario.DetalleSalidas;
import Modelo.DTO.Inventario.Insumos;
import Modelo.DTO.Venta.DetalleDeVenta;
import Modelo.DTO.Venta.Producto;
import Modelo.Dao.Venta.ProductoDao;
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
public class DetalleSalidasDao implements Crud<DetalleSalidas, String> {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;
    CallableStatement cs;

    @Override
    public boolean create(DetalleSalidas clase) {
        String sql = "Insert into detallesalidas(NumeroSalida,CodigoInsumo,Descripcion,Unidad,Cantidad,Valor,ValorTotal) values (?,?,?,?,?,?,?)";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, clase.getCabeceraSalida());
            p1.setString(2, clase.getInsumo().getCodigoInsumo());
            p1.setString(3, clase.getInsumo().getDescripcion());
            p1.setString(4, clase.getInsumo().getUnidad());
            p1.setInt(5, clase.getCantidad());
            p1.setDouble(6, clase.getInsumo().getCosto());
            p1.setDouble(7, clase.getValorTotal());

            String sql1 = "{call agregarSalidas(?,?)}";
            cs = cn.prepareCall(sql1);
            cs.setInt(1, clase.getCantidad());
            cs.setString(2, clase.getInsumo().getCodigoInsumo());
            p1.executeUpdate();
            cs.execute();
        } catch (Exception e) {
            System.out.println("eRROR Detalle salida");
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List read() {
        List<DetalleSalidas> dvl = new ArrayList<>();
        return dvl;
    }

    @Override
    public DetalleSalidas read(String id) {
        DetalleSalidas dls = new DetalleSalidas();
        return dls;
    }

    @Override
    public boolean update(DetalleSalidas clase) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public List readDetalles(String ordenDeSalida) {
        List<DetalleSalidas> dvl = new ArrayList<>();
        String sql = "select * from detallesalidas where NumeroSalida =?;";
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            p1.setString(1, ordenDeSalida);
            rs = p1.executeQuery();
            Insumos i = new Insumos();
            InsumosDao idao = new InsumosDao();
            while (rs.next()) {
                DetalleSalidas ddv = new DetalleSalidas();
                ddv.setCabeceraSalida(rs.getString("NumeroSalida"));
                String idp = (rs.getString("CodigoInsumo"));
                i = idao.read(idp);
                ddv.setInsumo(i);
                ddv.setCantidad(rs.getInt("Cantidad"));
                ddv.setValorTotal(rs.getDouble("ValorTotal"));
                dvl.add(ddv);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("GA2");
        }
        return dvl;
    }
}
