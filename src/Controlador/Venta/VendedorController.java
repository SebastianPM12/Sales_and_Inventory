/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.Vendedor;
import Modelo.Dao.Venta.VendedorDao;
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
public class VendedorController implements ActionListener {

    String idVendedor;
    VentanaUsuarios vista;
    Vendedor clase;
    VendedorDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.tablaVendedores.getSelectedRow();
            if (fila == -1) {
                vista.buttonAgregar.setEnabled(true);
                vista.fieldId.setEditable(true);
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                vista.buttonAgregar.setEnabled(false);
                vista.fieldId.setEditable(false);

                idVendedor = vista.tablaVendedores.getValueAt(fila, 0).toString();
                String nombres = vista.tablaVendedores.getValueAt(fila, 1).toString();
                String apellidos = vista.tablaVendedores.getValueAt(fila, 2).toString();
                String celular = vista.tablaVendedores.getValueAt(fila, 3).toString();
                String posicion = vista.tablaVendedores.getValueAt(fila, 4).toString();
                String dni = vista.tablaVendedores.getValueAt(fila, 5).toString();
                vista.fieldId.setText(idVendedor);
                vista.fieldNombres.setText(nombres);
                vista.fieldApellidos.setText(apellidos);
                vista.fieldCelular.setText(celular);
                vista.cmbPosicion.setSelectedItem(posicion);
                vista.fieldDni.setText(dni);
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

    public VendedorController(VentanaUsuarios vista, Vendedor clase, VendedorDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        listar();
        this.vista.buttonAgregar.addActionListener(this);
        this.vista.buttonNuevo.addActionListener(this);
        this.vista.buttonActualizar.addActionListener(this);
        this.vista.buttonEliminar.addActionListener(this);
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.buttonReiniciar.addActionListener(this);
        this.vista.tablaVendedores.addMouseListener(oyenteRaton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonAgregar) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas Seguro de agregar?", "Confirmar", dialogButton);
            if (dialogResult == 0) {
                agregar();
                limpiarTabla();
                listar();
                nuevo();
            }

        }
        if (e.getSource() == vista.buttonNuevo) {
            vista.buttonAgregar.setEnabled(true);

            nuevo();
            limpiarTabla();
            listar();
        }
        if (e.getSource() == vista.buttonActualizar) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas Seguro de modificar?", "Confirmar", dialogButton);
            actualizar();
            limpiarTabla();
            listar();
            nuevo();
        }
        if (e.getSource() == vista.buttonEliminar) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas Seguro de eliminar?", "Confirmar", dialogButton);
            eliminar();
            limpiarTabla();
            listar();
            nuevo();
        }
        if (e.getSource() == vista.buttonBuscar) {
            buscar();
        }

        if (e.getSource() == vista.buttonReiniciar) {
            vista.fieldLlave.setText("");
            limpiarTabla();
            listar();
            nuevo();
        }
    }

    void listar() {
        List<Vendedor> lista = dao.read();
        modelo = (DefaultTableModel) vista.tablaVendedores.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdVendedor();
            ob[1] = lista.get(i).getNombres();
            ob[2] = lista.get(i).getApellidos();
            ob[3] = lista.get(i).getCelular();
            ob[4] = lista.get(i).getPosicion();
            ob[5] = lista.get(i).getDni();
            modelo.addRow(ob);
        }
        vista.tablaVendedores.setModel(modelo);
    }

    void agregar() {

        if (vista.fieldId.getText().equals("") || vista.cmbPosicion.getSelectedIndex() == 0 || vista.fieldApellidos.getText().equals("")
                || vista.fieldCelular.getText().equals("")
                || vista.fieldDni.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            try {
                String id = vista.fieldId.getText();
                String nombres = vista.fieldNombres.getText();
                String apellido = vista.fieldApellidos.getText();
                int celular = Integer.parseInt(vista.fieldCelular.getText());
                String posicion = vista.cmbPosicion.getSelectedItem().toString();
                int dni = Integer.parseInt(vista.fieldDni.getText());
                Vendedor v = new Vendedor();
                v.setIdVendedor(id);
                v.setNombres(nombres);
                v.setApellidos(apellido);
                v.setCelular(celular);
                v.setPosicion(posicion);
                v.setDni(dni);
                dao.create(v);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    void actualizar() {
        int fila = vista.tablaVendedores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vista.fieldId.getText().equals("") || vista.cmbPosicion.getSelectedIndex() == 0 || vista.fieldApellidos.getText().equals("")
                    || vista.fieldCelular.getText().equals("")
                    || vista.fieldDni.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {
                String id = vista.fieldId.getText();
                String nombres = vista.fieldNombres.getText();
                String apellido = vista.fieldApellidos.getText();
                int celular = Integer.parseInt(vista.fieldCelular.getText());
                String posicion = vista.cmbPosicion.getSelectedItem().toString();
                Vendedor v = new Vendedor();
                v.setIdVendedor(id);
                v.setNombres(nombres);
                v.setApellidos(apellido);
                v.setCelular(celular);
                v.setPosicion(posicion);
                dao.update(v);
            }
        }
    }

    void eliminar() {
        int fila = vista.tablaVendedores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            dao.delete(idVendedor);
        }
    }

    void nuevo() {
        vista.fieldId.setText("");
        vista.cmbPosicion.setSelectedIndex(0);
        vista.fieldNombres.setText("");
        vista.fieldApellidos.setText("");
        vista.fieldCelular.setText("");
        vista.fieldDni.setText("");
        vista.fieldId.requestFocus();
        vista.buttonAgregar.setEnabled(true);
        vista.fieldId.setEditable(true);
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void buscar() {
        String idp = vista.fieldLlave.getText();
        if (vista.fieldLlave.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE PONER EL ID DEL VENDEDOR QUE BUSCA");
        } else {
            Vendedor cv = dao.read(idp);
            if (cv.getIdVendedor() != null) {
                limpiarTabla();
                modelo = (DefaultTableModel) vista.tablaVendedores.getModel();
                Object[] ob = new Object[6];
                ob[0] = cv.getIdVendedor();
                ob[1] = cv.getNombres();
                ob[2] = cv.getApellidos();
                ob[3] = cv.getCelular();
                ob[4] = cv.getPosicion();
                ob[5] = cv.getDni();
                modelo.addRow(ob);
                vista.tablaVendedores.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "EL VENDEDOR NO ESTA REGISTRADO");

            }

        }

    }
}
