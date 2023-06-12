/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Inventario;


/**
 *
 * @author SEBAS
 */
public class ServicioCantidadEntrada {
    CantidadEntradaDao cdao = new CantidadEntradaDao();
    public int getCount() {
        return cdao.countData ("select count(*) as cantidad from cabeceraentrada");
    }
}
