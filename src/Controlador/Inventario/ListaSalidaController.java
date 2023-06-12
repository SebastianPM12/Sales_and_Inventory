/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Modelo.DTO.Inventario.CabeceraEntrada;
import Modelo.DTO.Inventario.CabeceraSalida;
import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.Dao.Inventario.CabeceraSalidaDao;
import Vista.Inventario.VentanaListarSalidas;
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
public class ListaSalidaController implements ActionListener {

    VentanaListarSalidas vista;
    CabeceraSalida clase;
    CabeceraSalidaDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public ListaSalidaController(VentanaListarSalidas vista, CabeceraSalida clase, CabeceraSalidaDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista.buttonBuscarSalida.addActionListener(this);
        this.vista.buttonReiniciar.addActionListener(this);
        limpiarTabla();
        ListarVentas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonBuscarSalida) {
            ListarSalidaID();

        } else if (e.getSource() == vista.buttonReiniciar) {
            limpiarTabla();
            ListarVentas();
        }
    }

    public void ListarVentas() {
        List<CabeceraSalida> listCdV = new ArrayList<>();
        listCdV = dao.read();
        modelo = (DefaultTableModel) vista.tableListaSalidas.getModel();
        Object[] ob = new Object[13];
        for (int i = 0; i < listCdV.size(); i++) {
            for (int j = 0; j < listCdV.get(i).getDetalleSalidas().size(); j++) {
                ob[0] = listCdV.get(i).getNumeroDocumentoSalida();
                ob[1] = listCdV.get(i).getOrdenDeVenta();
                ob[2] = listCdV.get(i).getPersonalInventario().getIdPersonal();
                ob[3] = listCdV.get(i).getFechaEmision();
                ob[4] = listCdV.get(i).getDetalleSalidas().get(j).getInsumo().getCodigoInsumo();
                ob[5] = listCdV.get(i).getDetalleSalidas().get(j).getInsumo().getDescripcion();
                ob[6] = listCdV.get(i).getDetalleSalidas().get(j).getInsumo().getUnidad();
                ob[7] = listCdV.get(i).getDetalleSalidas().get(j).getCantidad();
                ob[8] = listCdV.get(i).getDetalleSalidas().get(j).getInsumo().getCosto();
                ob[9] = listCdV.get(i).getDetalleSalidas().get(j).getValorTotal();
                ob[10] = listCdV.get(i).getValorTotal();
                ob[11] = listCdV.get(i).getIgv();
                ob[12] = listCdV.get(i).getPrecioTotal();
                modelo.addRow(ob);
            }
        }
        vista.tableListaSalidas.setModel(modelo);

    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void ListarSalidaID() {
        if (vista.fieldKeySalida.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "NO HA PUESTO NADA QUE BUSCAR", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            clase = dao.read(vista.fieldKeySalida.getText());
            if (clase.getNumeroDocumentoSalida() == null) {
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO LA SALIDA, BUSQUE DE NUEVO", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                limpiarTabla();
                modelo = (DefaultTableModel) vista.tableListaSalidas.getModel();
                Object[] ob = new Object[13];
                for (int i = 0; i < clase.getDetalleSalidas().size(); i++) {
                    ob[0] = clase.getNumeroDocumentoSalida();
                    ob[1] = clase.getOrdenDeVenta();
                    ob[2] = clase.getPersonalInventario().getIdPersonal();
                    ob[3] = clase.getFechaEmision();
                    ob[4] = clase.getDetalleSalidas().get(i).getInsumo().getCodigoInsumo();
                    ob[5] = clase.getDetalleSalidas().get(i).getInsumo().getDescripcion();
                    ob[6] = clase.getDetalleSalidas().get(i).getInsumo().getUnidad();
                    ob[7] = clase.getDetalleSalidas().get(i).getCantidad();
                    ob[8] = clase.getDetalleSalidas().get(i).getInsumo().getCosto();
                    ob[9] = clase.getDetalleSalidas().get(i).getValorTotal();
                    ob[10] = clase.getValorTotal();
                    ob[11] = clase.getIgv();
                    ob[12] = clase.getPrecioTotal();
                    modelo.addRow(ob);
                }
                vista.tableListaSalidas.setModel(modelo);
            }
        }
    }

}
