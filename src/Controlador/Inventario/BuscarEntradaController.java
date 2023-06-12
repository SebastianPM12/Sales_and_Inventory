/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Modelo.DTO.Inventario.CabeceraEntrada;
import Modelo.DTO.Inventario.CabeceraSalida;
import Modelo.Dao.Inventario.CabeceraEntradaDao;
import Modelo.Dao.Inventario.CabeceraSalidaDao;
import Vista.Inventario.VentanaBuscarEntrada;
import Vista.Inventario.VentanaBuscarSalida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEBAS
 */
public class BuscarEntradaController implements ActionListener {

    VentanaBuscarEntrada vista;
    CabeceraEntrada clase;
    CabeceraEntradaDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public BuscarEntradaController(VentanaBuscarEntrada vista, CabeceraEntrada clase, CabeceraEntradaDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.buttonReiniciar.addActionListener(this);
        this.vista.buttonGuardarEntrada.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonBuscar) {
            mostrarEntrada();
        } else if (e.getSource() == vista.buttonReiniciar) {
            limpiarTodo();

        }
    }

    void limpiarTodo() {
        vista.fieldKey.setText("");
        vista.fieldIgv.setText("");
        vista.fieldTipoEntrada.setText("");
        vista.fieldTOPG.setText("");
        vista.fieldFechaDeRegistro.setText("");
        vista.fieldProveedor.setText("");
        vista.FieldCodigo.setText("");
        vista.fieldNumeroDocumento.setText("");
        vista.fieldTotalEntrada.setText("");
        vista.fieldKey.requestFocus();
        limpiarTabla();
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void mostrarEntrada() {
        if (vista.fieldKey.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + "EL CODIGO DE VENTA NO SE ENCUENTRA", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            clase = dao.read(vista.fieldKey.getText());
            if (clase.getNumeroDocumentoEntrada()!= null) {
                limpiarTodo();
                vista.fieldNumeroDocumento.setText(clase.getNumeroDocumentoEntrada());
                vista.fieldTipoEntrada.setText(clase.getTipo());
                vista.fieldFechaDeRegistro.setText(clase.getFechaEmision() + "");
                vista.fieldProveedor.setText(clase.getProveedor().getIdProveedor()+"");
                vista.fieldTOPG.setText(clase.getValorTotal() + "");
                vista.fieldIgv.setText(clase.getIgv() + "");
                vista.fieldTotalEntrada.setText(clase.getPrecioTotal() + "");
                vista.FieldCodigo.setText(clase.getPersonalInventario().getIdPersonal()+"");
                modelo = (DefaultTableModel) vista.tableDetalleEntrada.getModel();
                Object[] ob = new Object[6];
                for (int i = 0; i < clase.getDetalleEntradas().size(); i++) {
                    ob[0] = clase.getDetalleEntradas().get(i).getInsumo().getCodigoInsumo();
                    ob[1] = clase.getDetalleEntradas().get(i).getInsumo().getDescripcion();
                    ob[2] = clase.getDetalleEntradas().get(i).getInsumo().getUnidad();
                    ob[3] = clase.getDetalleEntradas().get(i).getCantidad();
                    ob[4] = clase.getDetalleEntradas().get(i).getInsumo().getCosto();
                    ob[5] = clase.getDetalleEntradas().get(i).getValorTotal();
                    modelo.addRow(ob);
                }
                vista.tableDetalleEntrada.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + "EL CODIGO DE VENTA NO SE ENCUENTRA", "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

}
