/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Vista.Inventario.VentanaConfiguracion;
import Vista.Inventario.VentanaPersonalnventario;
import Vista.Ventas.VentanaNegocio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SEBAS
 */
public class ConfiguracionController implements ActionListener {

    VentanaConfiguracion vista;

    public ConfiguracionController(VentanaConfiguracion vista) {
        this.vista = vista;
        this.vista.buttonOtros.addActionListener(this);
        this.vista.buttonUsuarios.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonOtros) {
            VentanaNegocio vn = new VentanaNegocio();
            vn.setVisible(true);
            vista.setVisible(false);
        } else if (e.getSource() == vista.buttonUsuarios) {
            VentanaPersonalnventario vpi = new VentanaPersonalnventario();
            vpi.setVisible(true);
            vista.setVisible(false);

        }
    }

}
