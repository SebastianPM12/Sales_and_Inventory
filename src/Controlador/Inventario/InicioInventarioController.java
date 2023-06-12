/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Vista.Inicio.VentanaDeInicio;
import Vista.Inventario.VentanaConfiguracion;
import Vista.Inventario.VentanaEntradas;
import Vista.Inventario.VentanaInventario;
import Vista.Inventario.VentanaIventarioPrincipal;
import Vista.Inventario.VentanaProveedores;
import Vista.Inventario.VentanaSalidas;
import Vista.Inventario.VentanaSuministros;
import Vista.Ventas.VentanaMantenimiento;
import Vista.Ventas.VentanaOpcionesVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SEBAS
 */
public class InicioInventarioController implements ActionListener {

    VentanaIventarioPrincipal vista;

    public InicioInventarioController(VentanaIventarioPrincipal vista) {
        this.vista = vista;
        this.vista.buttonEntradas.addActionListener(this);
        this.vista.buttonInventario.addActionListener(this);
        this.vista.buttonMantenimiento.addActionListener(this);
        this.vista.buttonProveedores.addActionListener(this);
        this.vista.buttonSalida.addActionListener(this);
        this.vista.buttonSuministros.addActionListener(this);
        this.vista.buttonVentas.addActionListener(this);
        this.vista.buttonSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonEntradas) {
            VentanaEntradas ve = new VentanaEntradas();
            ve.setVisible(true);

        } else if (e.getSource() == vista.buttonInventario) {
            VentanaInventario vin = new VentanaInventario();
            vin.setVisible(true);
            
        } else if (e.getSource() == vista.buttonMantenimiento) {
            VentanaConfiguracion vcf = new VentanaConfiguracion();
            vcf.setVisible(true);
          
        } else if (e.getSource() == vista.buttonProveedores) {
            VentanaProveedores vp = new VentanaProveedores();
            vp.setVisible(true);
            
        } else if (e.getSource() == vista.buttonSalida) {
            VentanaSalidas vs = new VentanaSalidas();
            vs.setVisible(true);
            
        } else if (e.getSource() == vista.buttonSuministros) {
            VentanaSuministros vsn = new VentanaSuministros();
            vsn.setVisible(true);
            
        } else if (e.getSource() == vista.buttonVentas) {
            VentanaOpcionesVentas vop = new VentanaOpcionesVentas();
            vop.setVisible(true);
        } else if (e.getSource() == vista.buttonSalir) {
            VentanaDeInicio vi = new VentanaDeInicio();
            vista.setVisible(false);
            vi.setVisible(true);

        }
    }

}
