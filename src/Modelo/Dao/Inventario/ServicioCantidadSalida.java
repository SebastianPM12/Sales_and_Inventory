/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Inventario;

import Controlador.Inventario.CantidadSalidaDao;


/**
 *
 * @author SEBAS
 */
public class ServicioCantidadSalida {
    CantidadSalidaDao cdao = new CantidadSalidaDao();
    public int getCount() {
        return cdao.countData ("select count(*) as cantidad from cabecerasalida");
    }
}
