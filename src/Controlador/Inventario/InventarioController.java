/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Modelo.DTO.Inventario.CabeceraEntrada;
import Modelo.DTO.Inventario.Insumos;
import Modelo.Dao.Inventario.CabeceraEntradaDao;
import Modelo.Dao.Inventario.InsumosDao;
import Vista.Inventario.VentanaInventario;
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
public class InventarioController implements ActionListener {

    VentanaInventario vista;
    Insumos clase;
    InsumosDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public InventarioController(VentanaInventario vista, Insumos clase, InsumosDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista.buttonBuscarInsumo.addActionListener(this);
        this.vista.buttonReiniciar.addActionListener(this);
        limpiarTabla();
        ListarInventario();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.buttonBuscarInsumo) {
            ListarInsumoId();
        } else if (e.getSource() == vista.buttonReiniciar) {
            limpiarTabla();
            ListarInventario();
        }
    }

    public void ListarInventario() {
        List<Insumos> listCdV = new ArrayList<>();
        listCdV = dao.read();
        modelo = (DefaultTableModel) vista.tableListaInventario.getModel();
        Object[] ob = new Object[8];
        for (int i = 0; i < listCdV.size(); i++) {
            ob[0] = listCdV.get(i).getCodigoInsumo();
            ob[1] = listCdV.get(i).getDescripcion();
            ob[2] = listCdV.get(i).getTipo();
            ob[3] = listCdV.get(i).getArea();
            ob[4] = listCdV.get(i).getUnidad();
            ob[5] = listCdV.get(i).getEntradas();
            ob[6] = listCdV.get(i).getSalidas();
            ob[7] = listCdV.get(i).getStock();
            modelo.addRow(ob);

        }
        vista.tableListaInventario.setModel(modelo);

    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void ListarInsumoId() {
        if (vista.fieldKeyProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "NO HA PUESTO NADA QUE BUSCAR", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            clase = dao.read(vista.fieldKeyProducto.getText());
            if (clase.getDescripcion() == null) {
                JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO SU INSUMO. INTENTE DE NUEVO", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                limpiarTabla();
                modelo = (DefaultTableModel) vista.tableListaInventario.getModel();
                Object[] ob = new Object[8];
                ob[0] = clase.getCodigoInsumo();
                ob[1] = clase.getDescripcion();
                ob[2] = clase.getTipo();
                ob[3] = clase.getArea();
                ob[4] = clase.getUnidad();
                ob[5] = clase.getEntradas();
                ob[6] = clase.getSalidas();
                ob[7] = clase.getStock();
                modelo.addRow(ob);
                vista.tableListaInventario.setModel(modelo);

            }
        }
    }

}
