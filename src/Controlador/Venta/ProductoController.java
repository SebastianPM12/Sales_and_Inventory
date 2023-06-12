/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.Producto;
import Modelo.DTO.Venta.Vendedor;
import Modelo.Dao.Venta.ProductoDao;
import Modelo.Dao.Venta.VendedorDao;
import Vista.Ventas.VentanaProductos;
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
public class ProductoController implements ActionListener {

    String codigo;
    VentanaProductos vista;
    Producto clase;
    ProductoDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.tableProductos.getSelectedRow();
            if (fila == -1) {
                vista.buttonAgregar.setEnabled(true);
                vista.fieldCodigo.setEditable(true);

                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                try {
                    vista.buttonAgregar.setEnabled(false);
                    vista.fieldCodigo.setEditable(false);
                    codigo = vista.tableProductos.getValueAt(fila, 0).toString();
                    String precioUnitario = vista.tableProductos.getValueAt(fila, 1).toString();
                    String valor = vista.tableProductos.getValueAt(fila, 2).toString();
                    String capacidad = vista.tableProductos.getValueAt(fila, 3).toString();
                    String tipo = vista.tableProductos.getValueAt(fila, 4).toString();
                    String descripcion = vista.tableProductos.getValueAt(fila, 5).toString();
                    String largo = vista.tableProductos.getValueAt(fila, 6).toString();
                    String ancho = vista.tableProductos.getValueAt(fila, 7).toString();
                    String alto = vista.tableProductos.getValueAt(fila, 8).toString();

                    vista.fieldCodigo.setText(codigo);
                    vista.fieldPrecioUnitario.setText(precioUnitario);
                    vista.fieldValor.setText(valor);
                    vista.fieldCapacidad.setText(capacidad);
                    vista.fieldTipo.setText(tipo);
                    vista.fieldDescripcion.setText(descripcion);
                    vista.fieldLargo.setText(largo);
                    vista.fieldAncho.setText(ancho);
                    vista.fieldAlto.setText(alto);
                } catch (Exception a) {
                    JOptionPane.showMessageDialog(null, "EL REGISTRO TIENE CAMPOS VACIOS");

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

    public ProductoController(VentanaProductos vista, Producto clase, ProductoDao dao) {
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
        this.vista.tableProductos.addMouseListener(oyenteRaton);
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
        }

        if (e.getSource() == vista.buttonReiniciar) {
            vista.fieldKey.setText("");
            limpiarTabla();
            listar();
            nuevo();
        }
    }

    void listar() {
        List<Producto> lista = dao.read();
        modelo = (DefaultTableModel) vista.tableProductos.getModel();
        Object[] ob = new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getCodigo();
            ob[1] = lista.get(i).getPrecioUnitario();
            ob[2] = lista.get(i).getValor();
            ob[3] = lista.get(i).getCapacidad();
            ob[4] = lista.get(i).getTipo();
            ob[5] = lista.get(i).getDescripcion();
            ob[6] = lista.get(i).getLargo();
            ob[7] = lista.get(i).getAncho();
            ob[8] = lista.get(i).getAlto();
            modelo.addRow(ob);
        }
        vista.tableProductos.setModel(modelo);
    }

    void agregar() {

        if (vista.fieldCodigo.getText().equals("") || vista.fieldPrecioUnitario.getText().equals("")
                || vista.fieldValor.getText().equals("") || vista.fieldCapacidad.getText().equals("")
                || vista.fieldTipo.getText().equals("") || vista.fieldDescripcion.getText().equals("")
                || vista.fieldLargo.getText().equals("") || vista.fieldAncho.getText().equals("")
                || vista.fieldAlto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            try {
                String codigo = vista.fieldCodigo.getText();
                double precioUnitario = Double.parseDouble(vista.fieldPrecioUnitario.getText());
                double valor = Double.parseDouble(vista.fieldValor.getText());
                String capacidad = vista.fieldCapacidad.getText();
                String tipo = vista.fieldTipo.getText();
                String descripcion = vista.fieldDescripcion.getText();
                String largo = vista.fieldLargo.getText();
                String ancho = vista.fieldAncho.getText();
                String alto = vista.fieldAlto.getText();

                Producto p = new Producto();
                p.setCodigo(codigo);
                p.setPrecioUnitario(precioUnitario);
                p.setValor(valor);
                p.setCapacidad(capacidad);
                p.setTipo(tipo);
                p.setDescripcion(descripcion);
                p.setLargo(largo);
                p.setAncho(ancho);
                p.setAlto(alto);
                dao.create(p);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    void actualizar() {
        int fila = vista.tableProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vista.fieldCodigo.getText().equals("") || vista.fieldPrecioUnitario.getText().equals("")
                    || vista.fieldValor.getText().equals("") || vista.fieldCapacidad.getText().equals("")
                    || vista.fieldTipo.getText().equals("") || vista.fieldDescripcion.getText().equals("")
                    || vista.fieldLargo.getText().equals("") || vista.fieldAncho.getText().equals("")
                    || vista.fieldAlto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {

                String codigo = vista.fieldCodigo.getText();
                double precioUnitario = Double.parseDouble(vista.fieldPrecioUnitario.getText());
                double valor = Double.parseDouble(vista.fieldValor.getText());
                String capacidad = vista.fieldCapacidad.getText();
                String tipo = vista.fieldTipo.getText();
                String descripcion = vista.fieldDescripcion.getText();
                String largo = vista.fieldLargo.getText();
                String ancho = vista.fieldAncho.getText();
                String alto = vista.fieldAlto.getText();

                Producto p = new Producto();
                p.setCodigo(codigo);
                p.setPrecioUnitario(precioUnitario);
                p.setValor(valor);
                p.setCapacidad(capacidad);
                p.setTipo(tipo);
                p.setDescripcion(descripcion);
                p.setLargo(largo);
                p.setAncho(ancho);
                p.setAlto(alto);
                dao.update(p);
            }
        }
    }

    void eliminar() {
        int fila = vista.tableProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            dao.delete(codigo);
        }
    }

    void nuevo() {
        vista.fieldCodigo.setText("");
        vista.fieldPrecioUnitario.setText("");
        vista.fieldValor.setText("");
        vista.fieldCapacidad.setText("");
        vista.fieldTipo.setText("");
        vista.fieldDescripcion.setText("");
        vista.fieldCodigo.requestFocus();
        vista.fieldAlto.setText("");
        vista.fieldAncho.setText("");
        vista.fieldLargo.setText("");
        vista.fieldCodigo.setEditable(true);
        vista.buttonAgregar.setEnabled(true);
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
            JOptionPane.showMessageDialog(null, "DEBE PONER EL ID DEL PRODUCTO QUE BUSCA");
        } else {
            Producto p = dao.read(idp);
            if (p.getCodigo() != null) {
                limpiarTabla();
                modelo = (DefaultTableModel) vista.tableProductos.getModel();
                Object[] ob = new Object[9];
                ob[0] = p.getCodigo();
                ob[1] = p.getPrecioUnitario();
                ob[2] = p.getValor();
                ob[3] = p.getCapacidad();
                ob[4] = p.getTipo();
                ob[5] = p.getDescripcion();
                ob[6] = p.getLargo();
                ob[7] = p.getAncho();
                ob[8] = p.getAlto();
                modelo.addRow(ob);
                vista.tableProductos.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "EL PRODUCTO NO ESTA REGISTRADO");

            }

        }

    }
}
