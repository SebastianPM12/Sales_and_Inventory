/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Vista.Inventario.VentanaAgregarEntrada;
import Vista.Inventario.VentanaAgregarSalida;
import Vista.Inventario.VentanaBuscarEntrada;
import Vista.Inventario.VentanaBuscarSalida;
import Vista.Inventario.VentanaEntradas;
import Vista.Inventario.VentanaListarEntradas;
import Vista.Inventario.VentanaListarSalidas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SEBAS
 */
public class EntradaController implements ActionListener {

    VentanaEntradas vista;

    public EntradaController(VentanaEntradas vista) {
        this.vista = vista;
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.buttonListar.addActionListener(this);
        this.vista.buttonNuevo.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonBuscar) {
            VentanaBuscarEntrada vs = new VentanaBuscarEntrada();
            vs.setVisible(true);
            vista.setVisible(false);

        } else if (e.getSource() == vista.buttonListar) {
            VentanaListarEntradas vls = new VentanaListarEntradas();
            vls.setVisible(true);
            vista.setVisible(false);

        } else if (e.getSource() == vista.buttonNuevo) {
            VentanaAgregarEntrada vls = new VentanaAgregarEntrada();
            vls.setVisible(true);
            vista.setVisible(false);
        }
    }

}
