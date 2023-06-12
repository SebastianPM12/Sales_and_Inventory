/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.Dao.Venta.CabeceraDeVentaDao;
import Vista.Ventas.VentanaListarVentas;
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
public class VentanaListarController implements ActionListener {

    VentanaListarVentas vista;
    CabeceraDeVenta cdv;
    CabeceraDeVentaDao cdao;
    DefaultTableModel modelo = new DefaultTableModel();

    public VentanaListarController(VentanaListarVentas vista, CabeceraDeVenta cdv, CabeceraDeVentaDao cdao) {
        this.vista = vista;
        this.cdv = cdv;
        this.cdao = cdao;
        this.vista.buttonBuscarVenta.addActionListener(this);
        limpiarTabla();
        ListarVentas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == vista.buttonBuscarVenta) {
            limpiarTabla();
            ListarVentasID();

        } else if (e.getSource() == vista.buttonReiniciar) {
            limpiarTabla();
            ListarVentas();
        }
    }

    public void ListarVentas() {
        List<CabeceraDeVenta> listCdV = new ArrayList<>();
        listCdV = cdao.read();
        modelo = (DefaultTableModel) vista.tableListaVenta.getModel();
        Object[] ob = new Object[15];
        for (int i = 0; i < listCdV.size(); i++) {
            for (int j = 0; j < listCdV.get(i).getDeatallesDeVenta().size(); j++) {
                ob[0] = listCdV.get(i).getOrdendeventa();
                ob[1] = listCdV.get(i).getCliente().getDniRuc();
                ob[2] = listCdV.get(i).getCliente().getNombres();
                ob[3] = listCdV.get(i).getFechaEmision();
                ob[4] = listCdV.get(i).getAsesor().getDni();
                ob[5] = listCdV.get(i).getVendedor().getIdVendedor();
                ob[6] = listCdV.get(i).getDeatallesDeVenta().get(j).getProducto().getCodigo();
                ob[7] = listCdV.get(i).getDeatallesDeVenta().get(j).getProducto().getDescripcion();
                ob[8] = listCdV.get(i).getDeatallesDeVenta().get(j).getCantidad();
                ob[9] = listCdV.get(i).getDeatallesDeVenta().get(j).getAbono();
                ob[10] = listCdV.get(i).getDeatallesDeVenta().get(j).getAbonoP();
                ob[11] = listCdV.get(i).getDeatallesDeVenta().get(j).getPorCobrar();
                ob[12] = listCdV.get(i).getValorTotal();
                ob[13] = listCdV.get(i).getIgv();
                ob[14] = listCdV.get(i).getPrecioTotal();
                modelo.addRow(ob);
            }
        }
        vista.tableListaVenta.setModel(modelo);

    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void ListarVentasID() {
        if (vista.fieldKeyVenta.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "NO HA PUESTO NADA QUE BUSCAR", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            cdv = cdao.read(vista.fieldKeyVenta.getText());
            if (cdv.getOrdendeventa() == null) {
                JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO LA VENTA, BUSQUE UNO QUE EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {

                modelo = (DefaultTableModel) vista.tableListaVenta.getModel();
                Object[] ob = new Object[15];
                for (int i = 0; i < cdv.getDeatallesDeVenta().size(); i++) {
                    ob[0] = cdv.getOrdendeventa();
                    ob[1] = cdv.getCliente().getDniRuc();
                    ob[2] = cdv.getCliente().getNombres();
                    ob[3] = cdv.getFechaEmision();
                    ob[4] = cdv.getAsesor().getDni();
                    ob[5] = cdv.getVendedor().getIdVendedor();
                    ob[6] = cdv.getDeatallesDeVenta().get(i).getProducto().getCodigo();
                    ob[7] = cdv.getDeatallesDeVenta().get(i).getProducto().getDescripcion();
                    ob[8] = cdv.getDeatallesDeVenta().get(i).getCantidad();
                    ob[9] = cdv.getDeatallesDeVenta().get(i).getAbono();
                    ob[10] = cdv.getDeatallesDeVenta().get(i).getAbonoP();
                    ob[11] = cdv.getDeatallesDeVenta().get(i).getPorCobrar();
                    ob[12] = cdv.getValorTotal();
                    ob[13] = cdv.getIgv();
                    ob[14] = cdv.getPrecioTotal();
                    modelo.addRow(ob);
                }
                vista.tableListaVenta.setModel(modelo);
            }
        }
    }

}
