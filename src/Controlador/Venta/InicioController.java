/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.Vendedor;
import Modelo.Dao.Venta.VendedorDao;
import Vista.Inicio.VentanaDeInicio;
import Vista.Ventas.VentanaVentasPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

/**
 *
 * @author SEBAS
 */
public class InicioController implements ActionListener {

    VentanaDeInicio vista;
    Vendedor clase;
    VendedorDao dao;
    VentanaVentasPrincipal vista2;
    int count=0;

    public InicioController(VentanaDeInicio vista, Vendedor clase, VendedorDao dao, VentanaVentasPrincipal vista2) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista2 = vista2;
        this.vista.buttonEntrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.buttonEntrar) {
            buscar();
        }

    }

    void nuevo() {
        vista.fieldContraseña.setText("");
        vista.fieldId.setText("");
        vista.cmbPosicion.setSelectedIndex(0);
        vista.fieldId.requestFocus();
    }

    void buscar() {

        if ((vista.fieldContraseña.getText().equals(""))
                || (vista.fieldId.getText().equals(""))
                || (vista.cmbPosicion.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS CAMPOS");
        } else {
            try {
                int contraseña = Integer.parseInt(vista.fieldContraseña.getText());
                String id = vista.fieldId.getText();
                String posicion = vista.cmbPosicion.getSelectedItem().toString();
                Vendedor p = dao.checkVendedores(id, contraseña, posicion);
                if (p.getPosicion() != null && p.getIdVendedor() != null && p.getDni() != 0) {
                    if (p.getPosicion().equals("VENDEDOR")) {
                        vista2.buttonMantenimientos.setEnabled(false);
                    } else if (p.getPosicion().equals("JEFE DE AREA")) {
                        vista2.buttonMantenimientos.setEnabled(true);
                    }
                    JOptionPane.showMessageDialog(null, "BIENVENIDO " + p.getNombres() +" " + p.getApellidos());
                    vista2.labelNombreUsuario.setText(p.getNombres() + " " + p.getApellidos());
                    vista2.setVisible(true);
                    vista.setVisible(false);
                    LocalDateTime fecha1 = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
                    String fecha2 = formato.format(fecha1);
                    vista2.labelFechaHora.setText(fecha2);

                } else {
                    JOptionPane.showMessageDialog(null, "INGRESO FALLIDO \n"
                            + "RECUERDE QUE: \n"
                            + "SU CONTRASEÑA ES SU DNI \n"
                            + "SU USUARIO ES SU ID");
                    nuevo();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "HUBO UN ERROR, VUELVA A INTENTARLO"
                );
                nuevo();
            }
        }

    }

}
