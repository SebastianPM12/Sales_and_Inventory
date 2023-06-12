/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.Cliente;
import Modelo.Dao.Venta.ClienteDao;
import Vista.Ventas.VentanaClientes;
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
public class ClienteController implements ActionListener {

    String dniRuc;
    VentanaClientes vista;
    Cliente clase;
    ClienteDao dao;
    DefaultTableModel modelo = new DefaultTableModel();
    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.tablaClientes.getSelectedRow();
            if (fila == -1) {
                vista.buttonAgregar.setEnabled(true);
                vista.fieldDniRuc.setEditable(true);
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                vista.buttonAgregar.setEnabled(false);
                vista.fieldDniRuc.setEditable(false);
                dniRuc = vista.tablaClientes.getValueAt(fila, 0).toString();
                String nombres = vista.tablaClientes.getValueAt(fila, 1).toString();
                String apellidos = vista.tablaClientes.getValueAt(fila, 2).toString();
                String celular = vista.tablaClientes.getValueAt(fila, 3).toString();
                if (!nombres.equals("") || !apellidos.equals("") || !celular.equals("")) {
                    vista.fieldDniRuc.setText(String.valueOf(dniRuc));
                    vista.fieldNombres.setText(nombres);
                    vista.fieldApellidos.setText(apellidos);
                    vista.fieldCelular.setText(celular);
                } else {
                    JOptionPane.showMessageDialog(null, "NO HAY NINGUN ELEMENTO EN ESTE REGISTRO", "ERROR", JOptionPane.ERROR_MESSAGE);

                }

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

    public ClienteController(VentanaClientes vista, Cliente clase, ClienteDao dao) {
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
        this.vista.tablaClientes.addMouseListener(oyenteRaton);
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
            }

        } else if (e.getSource() == vista.buttonNuevo) {
            vista.buttonAgregar.setEnabled(true);
            nuevo();
            limpiarTabla();
            listar();
        } else if (e.getSource() == vista.buttonActualizar) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas Seguro de modificar?", "Confirmar", dialogButton);
            actualizar();
            limpiarTabla();
            listar();
            nuevo();
        } else if (e.getSource() == vista.buttonEliminar) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas Seguro de eliminar?", "Confirmar", dialogButton);
            eliminar();
            limpiarTabla();
            listar();
            nuevo();
        } else if (e.getSource() == vista.buttonBuscar) {
            buscar();
        } else if (e.getSource() == vista.buttonReiniciar) {
            vista.fieldKey.setText("");
            vista.fieldKey.requestFocus();
            limpiarTabla();
            listar();
            vista.buttonAgregar.setEnabled(true);
            nuevo();

        }
    }

    void listar() {
        List<Cliente> lista = dao.read();
        modelo = (DefaultTableModel) vista.tablaClientes.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getDniRuc();
            ob[1] = lista.get(i).getNombres();
            ob[2] = lista.get(i).getApellidos();
            ob[3] = lista.get(i).getCelular();
            modelo.addRow(ob);
        }
        vista.tablaClientes.setModel(modelo);
    }

    void agregar() {

        if (vista.fieldDniRuc.getText().equals("")
                || vista.fieldApellidos.getText().equals("")
                || vista.fieldCelular.getText().equals("")
                || vista.fieldNombres.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            String id = vista.fieldDniRuc.getText();
            String nombres = vista.fieldNombres.getText();
            String apellido = vista.fieldApellidos.getText();
            int celular = Integer.parseInt(vista.fieldCelular.getText());
            Cliente c = new Cliente();
            c.setDniRuc(id);
            c.setNombres(nombres);
            c.setApellidos(apellido);
            c.setCelular(celular);
            dao.create(c);
        }
    }

    void actualizar() {
        int fila = vista.tablaClientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vista.fieldDniRuc.getText().equals("")
                    || vista.fieldApellidos.getText().equals("")
                    || vista.fieldCelular.getText().equals("")
                    || vista.fieldNombres.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {
                String id = vista.fieldDniRuc.getText();
                String nombres = vista.fieldNombres.getText();
                String apellido = vista.fieldApellidos.getText();
                int celular = Integer.parseInt(vista.fieldCelular.getText());
                Cliente c = new Cliente();
                c.setDniRuc(id);
                c.setNombres(nombres);
                c.setApellidos(apellido);
                c.setCelular(celular);
                dao.update(c);
            }
        }
    }

    void eliminar() {
        int fila = vista.tablaClientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            dao.delete(dniRuc);
        }
    }

    void nuevo() {
        vista.fieldDniRuc.setText("");
        vista.fieldNombres.setText("");
        vista.fieldApellidos.setText("");
        vista.fieldCelular.setText("");
        vista.fieldDniRuc.requestFocus();
        vista.buttonAgregar.setEnabled(true);
        vista.fieldDniRuc.setEditable(true);
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void buscar() {
        if (vista.fieldKey.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE PONER EL ID DEL CLIENTE QUE BUSCA");
        } else {
            try {
                String idp = vista.fieldKey.getText();
                Cliente cv = dao.read(idp);
                if (cv.getDniRuc() != null) {
                    limpiarTabla();
                    modelo = (DefaultTableModel) vista.tablaClientes.getModel();
                    Object[] ob = new Object[4];
                    ob[0] = cv.getDniRuc();
                    ob[1] = cv.getNombres();
                    ob[2] = cv.getApellidos();
                    ob[3] = cv.getCelular();
                    modelo.addRow(ob);
                    vista.tablaClientes.setModel(modelo);
                } else {
                    JOptionPane.showMessageDialog(null, "EL CLIENTE NO ESTA REGISTRADO");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "DEBE PONER EL NUMERO DE DNI O RUC DEL CLIENTE Y NO LETRAS");

            }
        }

    }
}
