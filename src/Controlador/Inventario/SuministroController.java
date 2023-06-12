/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Modelo.DTO.Inventario.Insumos;
import Modelo.DTO.Inventario.Proveedor;
import Modelo.Dao.Inventario.InsumosDao;
import Modelo.Dao.Inventario.ProveedorDao;
import Vista.Inventario.VentanaSuministros;
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
public class SuministroController implements ActionListener {

    String idSuministro;
    VentanaSuministros vista;
    Insumos clase;
    InsumosDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public SuministroController(VentanaSuministros vista, Insumos clase, InsumosDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista.buttonActualizar.addActionListener(this);
        this.vista.buttonAgregar.addActionListener(this);
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.buttonEliminar.addActionListener(this);
        this.vista.buttonNuevo.addActionListener(this);
        this.vista.buttonReiniciar.addActionListener(this);
        this.vista.tablaInsumos.addMouseListener(oyenteRaton);
        this.vista.fieldIngresos.setText(0 + "");
        this.vista.fieldSalidas.setText(0 + "");
        this.vista.fieldStock.setText(0 + "");
        this.vista.fieldIngresos.setEditable(false);
        this.vista.fieldSalidas.setEditable(false);
        this.vista.fieldStock.setEditable(false);
        listar();
    }

    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.tablaInsumos.getSelectedRow();
            if (fila == -1) {
                vista.buttonAgregar.setEnabled(true);
                vista.fieldCodigo.setEditable(true);
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                vista.buttonAgregar.setEnabled(false);
                vista.fieldCodigo.setEditable(false);
                idSuministro = vista.tablaInsumos.getValueAt(fila, 0).toString();
                String costo = vista.tablaInsumos.getValueAt(fila, 1).toString();
                String idproveedor = vista.tablaInsumos.getValueAt(fila, 2).toString();
                String descripcion = vista.tablaInsumos.getValueAt(fila, 3).toString();
                String tipo = vista.tablaInsumos.getValueAt(fila, 4).toString();
                String area = vista.tablaInsumos.getValueAt(fila, 5).toString();
                String unidad = vista.tablaInsumos.getValueAt(fila, 6).toString();
                String entradas = vista.tablaInsumos.getValueAt(fila, 7).toString();
                String salidas = vista.tablaInsumos.getValueAt(fila, 8).toString();
                String stock = vista.tablaInsumos.getValueAt(fila, 9).toString();

                vista.fieldCodigo.setText(idSuministro);
                vista.fieldCosto.setText(costo);
                vista.fieldProveedor.setText(idproveedor);
                vista.fieldDescripcion.setText(descripcion);
                vista.fieldTiipo.setText(tipo);
                vista.fieldArea.setText(area);
                vista.fieldUnidad.setText(unidad);
                vista.fieldIngresos.setText(entradas);
                vista.fieldSalidas.setText(salidas);
                vista.fieldStock.setText(stock);
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
        List<Insumos> lista = dao.read();
        modelo = (DefaultTableModel) vista.tablaInsumos.getModel();
        Object[] ob = new Object[10];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getCodigoInsumo();
            ob[1] = lista.get(i).getCosto();
            ob[2] = lista.get(i).getProveedor().getIdProveedor();
            ob[3] = lista.get(i).getDescripcion();
            ob[4] = lista.get(i).getTipo();
            ob[5] = lista.get(i).getArea();
            ob[6] = lista.get(i).getUnidad();
            ob[7] = lista.get(i).getEntradas();
            ob[8] = lista.get(i).getSalidas();
            ob[9] = lista.get(i).getStock();
            modelo.addRow(ob);
        }
        vista.tablaInsumos.setModel(modelo);
    }

    void agregar() {
        if (vista.fieldCodigo.getText().equals("") || vista.fieldCosto.getText().equals("")
                || vista.fieldProveedor.getText().equals("")
                || vista.fieldDescripcion.getText().equals("")
                || vista.fieldTiipo.getText().equals("")
                || vista.fieldArea.getText().equals("")
                || vista.fieldUnidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            try {
                String proveedor = vista.fieldProveedor.getText();
                Insumos v = new Insumos();
                Proveedor p = new Proveedor();
                ProveedorDao pdao = new ProveedorDao();
                p = pdao.read(proveedor);
                if (p.getIdProveedor() == null) {
                    JOptionPane.showMessageDialog(null, "EL PROVEEDOR NO EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    String codigo = vista.fieldCodigo.getText();
                    Double costo = Double.parseDouble(vista.fieldCosto.getText());
                    String descripcion = vista.fieldDescripcion.getText();
                    String tipo = vista.fieldTiipo.getText();
                    String area = vista.fieldArea.getText();
                    String unidad = vista.fieldUnidad.getText();
                    int entrada = Integer.parseInt(vista.fieldIngresos.getText());
                    int salida = Integer.parseInt(vista.fieldSalidas.getText());
                    int stock = Integer.parseInt(vista.fieldStock.getText());
                    v.setCodigoInsumo(codigo);
                    v.setCosto(costo);
                    v.setProveedor(p);
                    v.setDescripcion(descripcion);
                    v.setTipo(tipo);
                    v.setArea(area);
                    v.setUnidad(unidad);
                    v.setEntradas(entrada);
                    v.setSalidas(salida);
                    v.setStock(stock);
                    dao.create(v);
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    void actualizar() {
        int fila = vista.tablaInsumos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vista.fieldCodigo.getText().equals("") || vista.fieldCosto.getText().equals("")
                    || vista.fieldProveedor.getText().equals("")
                    || vista.fieldDescripcion.getText().equals("")
                    || vista.fieldTiipo.getText().equals("")
                    || vista.fieldArea.getText().equals("")
                    || vista.fieldUnidad.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {
                try {
                    String proveedor = vista.fieldProveedor.getText();
                    Insumos v = new Insumos();
                    Proveedor p = new Proveedor();
                    ProveedorDao pdao = new ProveedorDao();
                    p = pdao.read(proveedor);
                    if (p.getIdProveedor() == null) {
                        JOptionPane.showMessageDialog(null, "EL PROVEEDOR NO EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String codigo = vista.fieldCodigo.getText();
                        Double costo = Double.parseDouble(vista.fieldCosto.getText());
                        String descripcion = vista.fieldDescripcion.getText();
                        String tipo = vista.fieldTiipo.getText();
                        String area = vista.fieldArea.getText();
                        String unidad = vista.fieldUnidad.getText();
                        int entrada = Integer.parseInt(vista.fieldIngresos.getText());
                        int salida = Integer.parseInt(vista.fieldSalidas.getText());
                        int stock = Integer.parseInt(vista.fieldStock.getText());
                        v.setCodigoInsumo(codigo);
                        v.setCosto(costo);
                        v.setProveedor(p);
                        v.setDescripcion(descripcion);
                        v.setTipo(tipo);
                        v.setArea(area);
                        v.setUnidad(unidad);
                        v.setEntradas(entrada);
                        v.setSalidas(salida);
                        v.setStock(stock);
                        dao.update(v);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            }
        }
    }

    void eliminar() {
        int fila = vista.tablaInsumos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            dao.delete(idSuministro);
        }
    }

    void nuevo() {
        vista.fieldCodigo.setText("");
        vista.fieldCosto.setText("");
        vista.fieldProveedor.setText("");
        vista.fieldDescripcion.setText("");
        vista.fieldTiipo.setText("");
        vista.fieldArea.setText("");
        vista.fieldUnidad.setText("");
        vista.fieldIngresos.setText("");
        vista.fieldSalidas.setText("");
        vista.fieldStock.setText("");
        vista.buttonAgregar.setEnabled(true);
        vista.fieldCodigo.setEditable(true);
        vista.fieldCodigo.requestFocus();
        vista.fieldIngresos.setEditable(false);
        vista.fieldSalidas.setEditable(false);
        vista.fieldStock.setEditable(false);
        vista.fieldIngresos.setText(0 + "");
        vista.fieldSalidas.setText(0 + "");
        vista.fieldStock.setText(0 + "");
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
            JOptionPane.showMessageDialog(null, "DEBE PONER EL ID DEL INSUMO QUE BUSCA");
        } else {
            Insumos cv = dao.read(idp);
            if (cv.getCodigoInsumo() != null) {
                limpiarTabla();
                modelo = (DefaultTableModel) vista.tablaInsumos.getModel();
                Object[] ob = new Object[10];
                ob[0] = cv.getCodigoInsumo();
                ob[1] = cv.getCosto();
                ob[2] = cv.getProveedor().getIdProveedor();
                ob[3] = cv.getDescripcion();
                ob[4] = cv.getTipo();
                ob[5] = cv.getArea();
                ob[6] = cv.getUnidad();
                ob[7] = cv.getEntradas();
                ob[8] = cv.getSalidas();
                ob[9] = cv.getStock();
                modelo.addRow(ob);
                vista.tablaInsumos.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "EL VENDEDOR NO ESTA REGISTRADO");
            }
        }
    }

}
