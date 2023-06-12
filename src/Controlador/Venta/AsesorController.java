/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.Asesor;
import Modelo.Dao.Venta.AsesorDao;
import Vista.Ventas.VentanaAsesores;
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
public class AsesorController implements ActionListener {

    int idVendedor;
    VentanaAsesores vista;
    Asesor clase;
    AsesorDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.tablaAsesores.getSelectedRow();
            if (fila == -1) {
                vista.buttonAgregar.setEnabled(true);
                vista.fieldDni.setEditable(true);
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                vista.buttonAgregar.setEnabled(false);
                vista.fieldDni.setEditable(false);

                idVendedor = Integer.parseInt(vista.tablaAsesores.getValueAt(fila, 0).toString());
                String nombres = vista.tablaAsesores.getValueAt(fila, 1).toString();
                String apellidos = vista.tablaAsesores.getValueAt(fila, 2).toString();
                String celular = vista.tablaAsesores.getValueAt(fila, 3).toString();
                vista.fieldDni.setText(String.valueOf(idVendedor));
                vista.fieldNombres.setText(nombres);
                vista.fieldApellidos.setText(apellidos);
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

    public AsesorController(VentanaAsesores vista, Asesor clase, AsesorDao dao) {
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
        this.vista.tablaAsesores.addMouseListener(oyenteRaton);
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
            vista.buttonAgregar.setEnabled(true);
            vista.fieldDni.setEditable(true);
        }

        if (e.getSource() == vista.buttonReiniciar) {
            vista.fieldKey.setText("");
            vista.fieldKey.requestFocus();
            limpiarTabla();
            listar();
            nuevo();
        }
    }

    void listar() {
        List<Asesor> lista = dao.read();
        modelo = (DefaultTableModel) vista.tablaAsesores.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getDni();
            ob[1] = lista.get(i).getNombres();
            ob[2] = lista.get(i).getApellidos();
            ob[3] = lista.get(i).getCelular();
            modelo.addRow(ob);
        }
        vista.tablaAsesores.setModel(modelo);
    }

    void agregar() {

        if (vista.fieldDni.getText().equals("")
                || vista.fieldApellidos.getText().equals("")
                || vista.fieldCelular.getText().equals("")
                || vista.fieldNombres.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            int id = Integer.parseInt(vista.fieldDni.getText());
            String nombres = vista.fieldNombres.getText();
            String apellido = vista.fieldApellidos.getText();
            int celular = Integer.parseInt(vista.fieldCelular.getText());
            Asesor a = new Asesor();
            a.setDni(id);
            a.setNombres(nombres);
            a.setApellidos(apellido);
            a.setCelular(celular);
            dao.create(a);
        }
    }

    void actualizar() {
        int fila = vista.tablaAsesores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vista.fieldDni.getText().equals("")
                    || vista.fieldApellidos.getText().equals("")
                    || vista.fieldCelular.getText().equals("")
                    || vista.fieldNombres.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {
                int id = Integer.parseInt(vista.fieldDni.getText());
                String nombres = vista.fieldNombres.getText();
                String apellido = vista.fieldApellidos.getText();
                int celular = Integer.parseInt(vista.fieldCelular.getText());
                Asesor a = new Asesor();
                a.setDni(id);
                a.setNombres(nombres);
                a.setApellidos(apellido);
                a.setCelular(celular);
                dao.update(a);
            }
        }
    }

    void eliminar() {
        int fila = vista.tablaAsesores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            dao.delete(idVendedor);
        }
    }

    void nuevo() {
        vista.fieldDni.setText("");
        vista.fieldNombres.setText("");
        vista.fieldApellidos.setText("");
        vista.fieldCelular.setText("");
        vista.fieldDni.requestFocus();
        vista.fieldDni.setEditable(true);
        vista.buttonAgregar.setEnabled(true);

    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void buscar() {
        if (vista.fieldKey.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE PONER EL ID DEL ASESOR QUE BUSCA");
        } else {
            try {
                int idp = Integer.parseInt(vista.fieldKey.getText());
                Asesor cv = dao.read(idp);
                if (cv.getDni() != 0) {
                    limpiarTabla();
                    modelo = (DefaultTableModel) vista.tablaAsesores.getModel();
                    Object[] ob = new Object[4];
                    ob[0] = cv.getDni();
                    ob[1] = cv.getNombres();
                    ob[2] = cv.getApellidos();
                    ob[3] = cv.getCelular();
                    modelo.addRow(ob);
                    vista.tablaAsesores.setModel(modelo);
                } else {
                    JOptionPane.showMessageDialog(null, "EL ASESOR NO ESTA REGISTRADO");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "DEBE PONER EL NUMERO DE DNI DEL ASESOR Y NO LETRAS");

            }

        }

    }

}
