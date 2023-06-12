/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.DTO.Venta.Cliente;
import Modelo.DTO.Venta.Producto;
import Modelo.Dao.Venta.CabeceraDeVentaDao;
import Modelo.Dao.Venta.ClienteDao;
import Modelo.Dao.Venta.ProductoDao;
import Vista.Inicio.VentanaDeInicio;
import Vista.Ventas.VentanaAgregarVentas;
import Vista.Ventas.VentanaBuscarVentas;
import Vista.Ventas.VentanaClientes;
import Vista.Ventas.VentanaComision;
import Vista.Ventas.VentanaListarVentas;
import Vista.Ventas.VentanaMantenimiento;
import Vista.Ventas.VentanaOpcionesVentas;
import Vista.Ventas.VentanaProductos;
import Vista.Ventas.VentanaVentasPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SEBAS
 */
public class VentanaPrincipalController implements ActionListener {

    VentanaVentasPrincipal vista;

    public VentanaPrincipalController(VentanaVentasPrincipal vista) {
        this.vista = vista;
        this.vista.buttonClientes.addActionListener(this);
        this.vista.buttonMantenimientos.addActionListener(this);
        this.vista.buttonProductos.addActionListener(this);
        this.vista.buttonVentas.addActionListener(this);
        this.vista.buttonSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonClientes) {
            VentanaClientes vc = new VentanaClientes();
            vc.setVisible(true);

        } else if (e.getSource() == vista.buttonMantenimientos) {
            VentanaMantenimiento vn = new VentanaMantenimiento();
            vn.setVisible(true);

        } else if (e.getSource() == vista.buttonProductos) {
            VentanaProductos vp = new VentanaProductos();
            vp.setVisible(true);

        } else if (e.getSource() == vista.buttonVentas) {
            VentanaOpcionesVentas vov = new VentanaOpcionesVentas();
            vov.setVisible(true);

        } else if (e.getSource() == vista.buttonSalir) {
            VentanaDeInicio vi = new VentanaDeInicio();
            vista.setVisible(false);
            vi.setVisible(true);
        }
    }

}
