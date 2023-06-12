/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Modelo.DTO.Inventario.CabeceraSalida;
import Modelo.DTO.Venta.Comision;
import Modelo.Dao.Inventario.CabeceraSalidaDao;
import Modelo.Dao.Venta.ComisionDao;
import Vista.Inventario.VentanaBuscarSalida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEBAS
 */
public class BuscarSalidaController implements ActionListener {

    VentanaBuscarSalida vista;
    CabeceraSalida clase;
    CabeceraSalidaDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public BuscarSalidaController(VentanaBuscarSalida vista, CabeceraSalida clase, CabeceraSalidaDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.buttonReiniciar.addActionListener(this);
        this.vista.buttonGuardarSalida.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonBuscar) {
            mostrarSalida();
        } else if (e.getSource() == vista.buttonReiniciar) {
            limpiarTodo();

        }
    }

    void limpiarTodo() {
        vista.fieldKey.setText("");
        vista.fieldIgv.setText("");
        vista.fieldOrdenDeVenta.setText("");
        vista.fieldTOPG.setText("");
        vista.fieldTotalSalida.setText("");
        vista.fieldFechaRegistro.setText("");
        vista.fieldCodigoGestor.setText("");
        vista.fieldNumeroDocumentoSalida.setText("");
        vista.fieldKey.requestFocus();
        limpiarTabla();
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void mostrarSalida() {
        if (vista.fieldKey.getText().equals(" ")) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + "EL CODIGO DE VENTA NO SE ENCUENTRA", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            clase = dao.read(vista.fieldKey.getText());
            if (clase.getNumeroDocumentoSalida()!= null) {
                limpiarTodo();
                vista.fieldNumeroDocumentoSalida.setText(clase.getNumeroDocumentoSalida());
                vista.fieldOrdenDeVenta.setText(clase.getOrdenDeVenta());
                vista.fieldFechaRegistro.setText(clase.getFechaEmision() + "");
                vista.fieldCodigoGestor.setText(clase.getPersonalInventario().getIdPersonal());
                vista.fieldTOPG.setText(clase.getValorTotal() + "");
                vista.fieldIgv.setText(clase.getIgv() + "");
                vista.fieldTotalSalida.setText(clase.getPrecioTotal() + "");
                modelo = (DefaultTableModel) vista.tableSalida.getModel();
                Object[] ob = new Object[6];
                for (int i = 0; i < clase.getDetalleSalidas().size(); i++) {
                    ob[0] = clase.getDetalleSalidas().get(i).getInsumo().getCodigoInsumo();
                    ob[1] = clase.getDetalleSalidas().get(i).getInsumo().getDescripcion();
                    ob[2] = clase.getDetalleSalidas().get(i).getInsumo().getUnidad();
                    ob[3] = clase.getDetalleSalidas().get(i).getCantidad();
                    ob[4] = clase.getDetalleSalidas().get(i).getInsumo().getCosto();
                    ob[5] = clase.getDetalleSalidas().get(i).getValorTotal();
                    modelo.addRow(ob);
                }
                vista.tableSalida.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + "EL CODIGO DE VENTA NO SE ENCUENTRA", "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        }
    }
}
