/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Modelo.DTO.Inventario.CabeceraEntrada;
import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.Dao.Inventario.CabeceraEntradaDao;
import Modelo.Dao.Venta.CabeceraDeVentaDao;
import Vista.Inventario.VentanaListarEntradas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEBAS
 */
public class ListaEntradasController implements ActionListener {

    VentanaListarEntradas vista;
    CabeceraEntrada clase;
    CabeceraEntradaDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public ListaEntradasController(VentanaListarEntradas vista, CabeceraEntrada clase, CabeceraEntradaDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista.buttonBuscarVenta.addActionListener(this);
        this.vista.buttonReiniciar.addActionListener(this);
        limpiarTabla();
        ListarEntradas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.buttonBuscarVenta) {
            ListarEntradaID();
        } else if (e.getSource() == vista.buttonReiniciar) {
            limpiarTabla();
            ListarEntradas();
        }
    }

    public void ListarEntradas() {
        List<CabeceraEntrada> listCdV = new ArrayList<>();
        listCdV = dao.read();
        modelo = (DefaultTableModel) vista.tableListaEntrada.getModel();
        Object[] ob = new Object[14];
        for (int i = 0; i < listCdV.size(); i++) {
            for (int j = 0; j < listCdV.get(i).getDetalleEntradas().size(); j++) {
                ob[0] = listCdV.get(i).getNumeroDocumentoEntrada();
                ob[1] = listCdV.get(i).getProveedor().getIdProveedor();
                ob[2] = listCdV.get(i).getPersonalInventario().getIdPersonal();
                ob[3] = listCdV.get(i).getTipo();
                ob[4] = listCdV.get(i).getFechaEmision();
                ob[5] = listCdV.get(i).getDetalleEntradas().get(j).getInsumo().getCodigoInsumo();
                ob[6] = listCdV.get(i).getDetalleEntradas().get(j).getInsumo().getDescripcion();
                ob[7] = listCdV.get(i).getDetalleEntradas().get(j).getInsumo().getUnidad();
                ob[8] = listCdV.get(i).getDetalleEntradas().get(j).getCantidad();
                ob[9] = listCdV.get(i).getDetalleEntradas().get(j).getInsumo().getCosto();
                ob[10] = listCdV.get(i).getDetalleEntradas().get(j).getValorTotal();
                ob[11] = listCdV.get(i).getValorTotal();
                ob[12] = listCdV.get(i).getIgv();
                ob[13] = listCdV.get(i).getPrecioTotal();
                modelo.addRow(ob);
            }
        }
        vista.tableListaEntrada.setModel(modelo);

    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void ListarEntradaID() {
        if (vista.fieldKeyVenta.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "NO HA PUESTO NADA QUE BUSCAR", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            clase = dao.read(vista.fieldKeyVenta.getText());
            if (clase.getNumeroDocumentoEntrada() == null) {
                JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO SU ENTRADA. INTENTE DE NUEVO", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                limpiarTabla();
                modelo = (DefaultTableModel) vista.tableListaEntrada.getModel();
                Object[] ob = new Object[14];
                for (int i = 0; i < clase.getDetalleEntradas().size(); i++) {
                    ob[0] = clase.getNumeroDocumentoEntrada();
                    ob[1] = clase.getProveedor().getIdProveedor();
                    ob[2] = clase.getPersonalInventario().getIdPersonal();
                    ob[3] = clase.getTipo();
                    ob[4] = clase.getFechaEmision();
                    ob[5] = clase.getDetalleEntradas().get(i).getInsumo().getCodigoInsumo();
                    ob[6] = clase.getDetalleEntradas().get(i).getInsumo().getDescripcion();
                    ob[7] = clase.getDetalleEntradas().get(i).getInsumo().getUnidad();
                    ob[8] = clase.getDetalleEntradas().get(i).getCantidad();
                    ob[9] = clase.getDetalleEntradas().get(i).getInsumo().getCosto();
                    ob[10] = clase.getDetalleEntradas().get(i).getValorTotal();
                    ob[11] = clase.getValorTotal();
                    ob[12] = clase.getIgv();
                    ob[13] = clase.getPrecioTotal();
                    modelo.addRow(ob);
                }
                vista.tableListaEntrada.setModel(modelo);

            }
        }
    }

}
