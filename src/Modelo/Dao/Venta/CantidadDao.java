/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

import Modelo.DTO.Conexxion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author SEBAS
 */
public class CantidadDao {

    Connection cn;
    Conexxion cnx = new Conexxion();
    PreparedStatement p1;
    ResultSet rs;

    public int countData(String sql) {
        int cant = 0;
        try {
            cn = cnx.Conectar();
            p1 = cn.prepareStatement(sql);
            rs=p1.executeQuery();
            while (rs.next()) {                
                cant=rs.getInt("cantidad");
            }
        } catch (Exception e) {
        }
        return cant;
    }
}
