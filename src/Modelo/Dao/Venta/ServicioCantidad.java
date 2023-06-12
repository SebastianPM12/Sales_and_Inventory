/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

/**
 *
 * @author SEBAS
 */
public class ServicioCantidad {
    CantidadDao cdao = new CantidadDao();
    public int getCount(){
        return cdao.countData("select count(*) as cantidad from cabeceradeventas");
    }
    
}
