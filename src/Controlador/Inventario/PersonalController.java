/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Modelo.DTO.Inventario.PersonalInventario;
import Modelo.DTO.Inventario.Proveedor;
import Modelo.Dao.Inventario.PersonalInventariodao;
import Vista.Inventario.VentanaPersonalnventario;
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
public class PersonalController implements ActionListener {

    String idP;
    VentanaPersonalnventario vista;
    PersonalInventario clase;
    PersonalInventariodao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public PersonalController(VentanaPersonalnventario vista, PersonalInventario clase, PersonalInventariodao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista.buttonActualizar.addActionListener(this);
        this.vista.buttonAgregar.addActionListener(this);
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.buttonEliminar.addActionListener(this);
        this.vista.buttonNuevo.addActionListener(this);
        this.vista.buttonReiniciar.addActionListener(this);
        this.vista.tablaPersonal.addMouseListener(oyenteRaton);
        listar();
    }

    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.tablaPersonal.getSelectedRow();
            if (fila == -1) {
                vista.buttonAgregar.setEnabled(true);
                vista.fieldID.setEditable(true);
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                vista.buttonAgregar.setEnabled(false);
                vista.fieldID.setEditable(false);
                idP = vista.tablaPersonal.getValueAt(fila, 0).toString();
                String nombre = vista.tablaPersonal.getValueAt(fila, 1).toString();
                String apellido = vista.tablaPersonal.getValueAt(fila, 2).toString();
                String dni = vista.tablaPersonal.getValueAt(fila, 3).toString();
                String celular = vista.tablaPersonal.getValueAt(fila, 4).toString();
                String posicion = vista.tablaPersonal.getValueAt(fila, 5).toString();
                vista.fieldID.setText(idP);
                vista.fieldNombre.setText(nombre);
                vista.fieldApellido.setText(apellido);
                vista.fieldDni.setText(dni);
                vista.fieldcelular.setText(celular);
                vista.cmbPosicion.setSelectedItem(posicion);
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
        List<PersonalInventario> lista = dao.read();
        modelo = (DefaultTableModel) vista.tablaPersonal.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdPersonal();
            ob[1] = lista.get(i).getNombres();
            ob[2] = lista.get(i).getApellidos();
            ob[3] = lista.get(i).getDni();
            ob[4] = lista.get(i).getCelular();
            ob[5] = lista.get(i).getPosicion();
            modelo.addRow(ob);
        }
        vista.tablaPersonal.setModel(modelo);
    }

    void agregar() {

        if (vista.fieldDni.getText().equals("") || vista.cmbPosicion.getSelectedIndex() == 0
                || vista.fieldID.getText().equals("")
                || vista.fieldNombre.getText().equals("")
                || vista.fieldApellido.getText().equals("")
                || vista.fieldcelular.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            try {
                String id = vista.fieldID.getText();
                String nombres = vista.fieldNombre.getText();
                String apellidoString = vista.fieldApellido.getText();
                int dni = Integer.parseInt(vista.fieldDni.getText());
                int celular = Integer.parseInt(vista.fieldcelular.getText());
                String posicion = vista.cmbPosicion.getSelectedItem().toString();
                PersonalInventario v = new PersonalInventario();
                v.setIdPersonal(id);
                v.setNombres(nombres);
                v.setApellidos(apellidoString);
                v.setDni(dni);
                v.setCelular(celular);
                v.setPosicion(posicion);
                dao.create(v);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    void actualizar() {
        int fila = vista.tablaPersonal.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vista.fieldDni.getText().equals("") || vista.cmbPosicion.getSelectedIndex() == 0
                    || vista.fieldID.getText().equals("")
                    || vista.fieldNombre.getText().equals("")
                    || vista.fieldApellido.getText().equals("")
                    || vista.fieldcelular.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {
                String id = vista.fieldID.getText();
                String nombres = vista.fieldNombre.getText();
                String apellidoString = vista.fieldApellido.getText();
                int dni = Integer.parseInt(vista.fieldDni.getText());
                int celular = Integer.parseInt(vista.fieldcelular.getText());
                String posicion = vista.cmbPosicion.getSelectedItem().toString();
                PersonalInventario v = new PersonalInventario();
                v.setIdPersonal(id);
                v.setNombres(nombres);
                v.setApellidos(apellidoString);
                v.setDni(dni);
                v.setCelular(celular);
                v.setPosicion(posicion);
                dao.update(v);
            }
        }
    }

    void eliminar() {
        int fila = vista.tablaPersonal.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            dao.delete(idP);
        }
    }

    void nuevo() {
        vista.fieldID.setText("");
        vista.fieldNombre.setText("");
        vista.fieldApellido.setText("");
        vista.fieldDni.setText("");
        vista.cmbPosicion.setSelectedIndex(0);
        vista.fieldcelular.setText("");
        vista.fieldID.requestFocus();
        vista.buttonAgregar.setEnabled(true);
        vista.fieldID.setEditable(true);
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
            JOptionPane.showMessageDialog(null, "DEBE PONER EL ID DEL PERSONAL QUE BUSCA");
        } else {
            PersonalInventario cv = dao.read(idp);
            if (cv.getIdPersonal() != null) {
                limpiarTabla();
                modelo = (DefaultTableModel) vista.tablaPersonal.getModel();
                Object[] ob = new Object[6];
                ob[0] = cv.getIdPersonal();
                ob[1] = cv.getNombres();
                ob[2] = cv.getApellidos();
                ob[3] = cv.getDni();
                ob[4] = cv.getCelular();
                ob[5] = cv.getPosicion();
                modelo.addRow(ob);
                vista.tablaPersonal.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "EL PERSONAL NO ESTA REGISTRADO");

            }

        }

    }

}
