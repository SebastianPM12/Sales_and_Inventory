/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Modelo.DTO.Inventario.Proveedor;
import Modelo.DTO.Venta.Vendedor;
import Modelo.Dao.Inventario.ProveedorDao;
import Vista.Inventario.VentanaProveedores;
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
public class ProveedoresController implements ActionListener {

    String idProveedor;
    VentanaProveedores vista;
    Proveedor clase;
    ProveedorDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public ProveedoresController(VentanaProveedores vista, Proveedor clase, ProveedorDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista.buttonActualizar.addActionListener(this);
        this.vista.buttonAgregar.addActionListener(this);
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.buttonEliminar.addActionListener(this);
        this.vista.buttonNuevo.addActionListener(this);
        this.vista.buttonReiniciar.addActionListener(this);
        this.vista.tablaProveedores.addMouseListener(oyenteRaton);

        listar();
    }

    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.tablaProveedores.getSelectedRow();
            if (fila == -1) {
                vista.buttonAgregar.setEnabled(true);
                vista.fieldRuc.setEditable(true);
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                vista.buttonAgregar.setEnabled(false);
                vista.fieldRuc.setEditable(false);
                idProveedor = vista.tablaProveedores.getValueAt(fila, 0).toString();
                String empresa = vista.tablaProveedores.getValueAt(fila, 1).toString();
                String direccion = vista.tablaProveedores.getValueAt(fila, 2).toString();
                String celular = vista.tablaProveedores.getValueAt(fila, 3).toString();
                vista.fieldRuc.setText(idProveedor);
                vista.fieldNombreEmpresa.setText(empresa);
                vista.fieldDireccion.setText(direccion);
                vista.fieldCelular.setText(celular);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonActualizar) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas Seguro de actualizar?", "Confirmar", dialogButton);
            actualizar();
            limpiarTabla();
            listar();
            nuevo();
        } else if (e.getSource() == vista.buttonAgregar) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas Seguro de agregar?", "Confirmar", dialogButton);
            agregar();
            limpiarTabla();
            listar();
            nuevo();
        } else if (e.getSource() == vista.buttonBuscar) {
            buscar();

        } else if (e.getSource() == vista.buttonEliminar) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas Seguro de eliminar?", "Confirmar", dialogButton);
            eliminar();
            limpiarTabla();
            listar();
            nuevo();

        } else if (e.getSource() == vista.buttonNuevo) {
            nuevo();
            limpiarTabla();
            listar();

        } else if (e.getSource() == vista.buttonReiniciar) {
            vista.fieldKey.setText("");
            limpiarTabla();
            listar();
            nuevo();

        }
    }

    void listar() {
        List<Proveedor> lista = dao.read();
        modelo = (DefaultTableModel) vista.tablaProveedores.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdProveedor();
            ob[1] = lista.get(i).getNombreDeEmpresa();
            ob[2] = lista.get(i).getDireccion();
            ob[3] = lista.get(i).getCelular();
            modelo.addRow(ob);
        }
        vista.tablaProveedores.setModel(modelo);
    }

    void agregar() {

        if (vista.fieldNombreEmpresa.getText().equals("") || vista.fieldDireccion.getText().equals("")
                || vista.fieldCelular.getText().equals("")
                || vista.fieldRuc.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            try {
                String id = vista.fieldRuc.getText();
                String nombres = vista.fieldNombreEmpresa.getText();
                String direccion = vista.fieldDireccion.getText();
                int celular = Integer.parseInt(vista.fieldCelular.getText());
                Proveedor v = new Proveedor();
                v.setIdProveedor(id);
                v.setNombreDeEmpresa(nombres);
                v.setDireccion(direccion);
                v.setCelular(celular);

                dao.create(v);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    void actualizar() {
        int fila = vista.tablaProveedores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vista.fieldNombreEmpresa.getText().equals("") || vista.fieldDireccion.getText().equals("")
                    || vista.fieldCelular.getText().equals("")
                    || vista.fieldRuc.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {
                String id = vista.fieldRuc.getText();
                String nombres = vista.fieldNombreEmpresa.getText();
                String direccion = vista.fieldDireccion.getText();
                int celular = Integer.parseInt(vista.fieldCelular.getText());
                Proveedor v = new Proveedor();
                v.setIdProveedor(id);
                v.setNombreDeEmpresa(nombres);
                v.setDireccion(direccion);
                v.setCelular(celular);
                dao.update(v);
            }
        }
    }

    void eliminar() {
        int fila = vista.tablaProveedores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            dao.delete(idProveedor);
        }
    }

    void nuevo() {
        vista.fieldRuc.setText("");
        vista.fieldNombreEmpresa.setText("");
        vista.fieldDireccion.setText("");
        vista.fieldCelular.setText("");
        vista.fieldRuc.requestFocus();
        vista.buttonAgregar.setEnabled(true);
        vista.fieldRuc.setEditable(true);
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
            JOptionPane.showMessageDialog(null, "DEBE PONER EL ID DEL PROVEEDOR QUE BUSCA");
        } else {
            Proveedor cv = dao.read(idp);
            if (cv.getIdProveedor() != null) {
                limpiarTabla();
                modelo = (DefaultTableModel) vista.tablaProveedores.getModel();
                Object[] ob = new Object[4];
                ob[0] = cv.getIdProveedor();
                ob[1] = cv.getNombreDeEmpresa();
                ob[2] = cv.getDireccion();
                ob[3] = cv.getCelular();
                modelo.addRow(ob);
                vista.tablaProveedores.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "EL VENDEDOR NO ESTA REGISTRADO");

            }

        }

    }

}
