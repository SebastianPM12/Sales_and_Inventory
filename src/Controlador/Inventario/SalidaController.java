/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Vista.Inventario.VentanaAgregarSalida;
import Vista.Inventario.VentanaBuscarSalida;
import Vista.Inventario.VentanaListarSalidas;
import Vista.Inventario.VentanaSalidas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SEBAS
 */
public class SalidaController implements ActionListener {

    VentanaSalidas vista;

    public SalidaController(VentanaSalidas vista) {
        this.vista = vista;
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.buttonListar.addActionListener(this);
        this.vista.buttonNuevo.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonBuscar) {
            VentanaBuscarSalida vs = new VentanaBuscarSalida();
            vs.setVisible(true);
            vista.setVisible(false);

        } else if (e.getSource() == vista.buttonListar) {
            VentanaListarSalidas vls = new VentanaListarSalidas();
            vls.setVisible(true);
            vista.setVisible(false);

        } else if (e.getSource() == vista.buttonNuevo) {
            VentanaAgregarSalida vls = new VentanaAgregarSalida();
            vls.setVisible(true);
            vista.setVisible(false);
        }
    }

}
