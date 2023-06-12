/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.Dao.Venta.CabeceraDeVentaDao;
import Vista.Ventas.VentanaAgregarVentas;
import Vista.Ventas.VentanaBuscarVentas;
import Vista.Ventas.VentanaComision;
import Vista.Ventas.VentanaComisionValores;
import Vista.Ventas.VentanaListarVentas;
import Vista.Ventas.VentanaOpcionesVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SEBAS
 */
public class ComisionPorcentajeController implements ActionListener{
    VentanaComisionValores ventana;

    public ComisionPorcentajeController(VentanaComisionValores ventana) {
        this.ventana = ventana;
    }

   
    

    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
    
    

}
