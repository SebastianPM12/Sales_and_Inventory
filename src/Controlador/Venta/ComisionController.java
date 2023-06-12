/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.Comision;
import Modelo.DTO.Venta.Vendedor;
import Modelo.Dao.Venta.ComisionDao;
import Modelo.Dao.Venta.VendedorDao;
import Vista.Ventas.VentanaComision;
import Vista.Ventas.VentanaUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEBAS
 */
public class ComisionController implements ActionListener {

    VentanaComision vista;
    Comision clase;
    ComisionDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public ComisionController(VentanaComision vista, Comision clase, ComisionDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        limpiarTabla();
        listar();
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.buttonReiniciar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.buttonBuscar) {
            buscar();
        }

        if (e.getSource() == vista.buttonReiniciar) {
            vista.fieldKey.setText("");
            limpiarTabla();
            listar();
            nuevo();
        }
    }

    void listar() {
        List<Comision> lista = dao.read();
        modelo = (DefaultTableModel) vista.tableComisiones.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdComision();
            ob[1] = lista.get(i).getCabeceraDeVenta();
            ob[2] = lista.get(i).getComisionVendedor();
            ob[3] = lista.get(i).getComisionAsesor();
            modelo.addRow(ob);
        }
        vista.tableComisiones.setModel(modelo);
    }

    void nuevo() {
        vista.fieldKey.setText("");
        vista.fieldKey.requestFocus();
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void buscar() {
        String idp = vista.fieldKey.getText();
        if (vista.fieldKey.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE PONER EL ORDEN DE VENTA QUE QUE BUSCA");
        } else {
            clase = dao.read(idp);
            if (clase.getCabeceraDeVenta() != null) {
                limpiarTabla();
                modelo = (DefaultTableModel) vista.tableComisiones.getModel();
                Object[] ob = new Object[4];
                ob[0] = clase.getIdComision();
                ob[1] = clase.getCabeceraDeVenta();
                ob[2] = clase.getComisionVendedor();
                ob[3] = clase.getComisionAsesor();
                modelo.addRow(ob);
                vista.tableComisiones.setModel(modelo);
            } else {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + "ORDEN DE VENTA NO ENCONTRADA", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        }

    }

}
