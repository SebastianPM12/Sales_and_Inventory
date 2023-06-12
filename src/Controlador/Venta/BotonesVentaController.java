/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.DTO.Venta.Producto;
import Modelo.Dao.Venta.CabeceraDeVentaDao;
import Modelo.Dao.Venta.ProductoDao;
import Vista.Ventas.VentanaAgregarVentas;
import Vista.Ventas.VentanaBuscarVentas;
import Vista.Ventas.VentanaComision;
import Vista.Ventas.VentanaListarVentas;
import Vista.Ventas.VentanaOpcionesVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SEBAS
 */
public class BotonesVentaController implements ActionListener {

    VentanaOpcionesVentas ventana;

    public BotonesVentaController(VentanaOpcionesVentas ventana) {
        this.ventana = ventana;
        this.ventana.buttonAgregar.addActionListener(this);
        this.ventana.buttonBuscar.addActionListener(this);
        this.ventana.buttonListar.addActionListener(this);
        this.ventana.buttonComision.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventana.buttonAgregar) {
            VentanaAgregarVentas vav = new VentanaAgregarVentas();
            vav.setVisible(true);
            ventana.setVisible(false);
        } else if (e.getSource() == ventana.buttonBuscar) {
            VentanaBuscarVentas vbv = new VentanaBuscarVentas();
            vbv.setVisible(true);
            ventana.setVisible(false);
        } else if (e.getSource() == ventana.buttonComision) {
            VentanaComision vnc = new VentanaComision();
            vnc.setVisible(true);
            ventana.setVisible(false);
        } else if (e.getSource() == ventana.buttonListar) {
            VentanaListarVentas vlv = new VentanaListarVentas();
            vlv.setVisible(true);
            ventana.setVisible(false);
        }
    }

}
