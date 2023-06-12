/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Controlador.Venta.VentaAgregarController;
import Modelo.DTO.Inventario.CabeceraEntrada;
import Modelo.DTO.Inventario.DetalleEntradas;
import Modelo.DTO.Inventario.Insumos;
import Modelo.DTO.Inventario.PersonalInventario;
import Modelo.DTO.Inventario.Proveedor;
import Modelo.DTO.Venta.DetalleDeVenta;
import Modelo.DTO.Venta.Producto;
import Modelo.Dao.Inventario.CabeceraEntradaDao;
import Modelo.Dao.Inventario.DetalleEntradasDao;
import Modelo.Dao.Inventario.GenerarCodigoEntrada;
import Modelo.Dao.Inventario.InsumosDao;
import Modelo.Dao.Inventario.PersonalInventariodao;
import Modelo.Dao.Inventario.ProveedorDao;
import Modelo.Dao.Inventario.ServicioCantidadEntrada;
import Modelo.Dao.Venta.GenerarCodigo;
import Modelo.Dao.Venta.ProductoDao;
import Modelo.Dao.Venta.ServicioCantidad;
import Vista.Inventario.VentanaAgregarEntrada;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEBAS
 */
public class AgregarEntradaController implements ActionListener {

    VentanaAgregarEntrada vista;
    CabeceraEntrada clase;
    CabeceraEntradaDao dao;
    DefaultTableModel modelo = new DefaultTableModel();
    List<DetalleEntradas> lista = new ArrayList<>();
    DetalleEntradas det = null;

    public AgregarEntradaController(VentanaAgregarEntrada vista, CabeceraEntrada clase, CabeceraEntradaDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista.buttonAgregar.addActionListener(this);
        this.vista.buttonGuardarEntrada.addActionListener(this);
        this.vista.buttonCancelarEntrada.addActionListener(this);
        this.vista.buttonEliminard.addActionListener(this);
        this.vista.buttonBuscarProducto.addActionListener(this);
        this.vista.fieldFechaDeRegistro.setEditable(false);
        this.vista.fieldNumeroDocumento.setEditable(false);
        this.vista.fieldNumeroDocumento.setText(geTordenDeVentaM());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonAgregar) {
            agregarDetaller();
        } else if (e.getSource() == vista.buttonBuscarProducto) {
            getProducto();

        } else if (e.getSource() == vista.buttonCancelarEntrada) {
            limpiarTodo();

        } else if (e.getSource() == vista.buttonEliminard) {
            borrarDetalle();

        } else if (e.getSource() == vista.buttonGuardarEntrada) {
            guardarEntrada();
            vista.fieldNumeroDocumento.setText(geTordenDeVentaM());

        }

    }

    public void agregarDetaller() {
        if (vista.fieldCodigoProducto.getText().isEmpty() || vista.fieldDescripcionProducto.getText().isEmpty()
                || vista.fieldValor.getText().isEmpty() || vista.fieldCodigoProducto.getText().isEmpty()
                || Integer.parseInt(vista.fieldCantidad.getText()) == 0
                || Integer.parseInt(vista.fieldStock1.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO, NO HAY DATOS PARA REGISTRAR\n", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            det = new DetalleEntradas();
            DecimalFormat df = new DecimalFormat("#0.00");
            String key = vista.fieldCodigoProducto.getText();
            Insumos p = new Insumos();
            InsumosDao pdao = new InsumosDao();
            p = pdao.read(key);
            int cantidad = Integer.parseInt(vista.fieldCantidad.getText());
            double precioTotal = (p.getCosto() * cantidad);
            det.setInsumo(p);
            det.setCantidad(cantidad);
            det.setValorTotal(precioTotal);
            lista.add(det);
            limpiarTabla();
            showDetalle();
            TotalVenta();
            limpiarProductos();
        }
    }

    public void showDetalle() {
        modelo = (DefaultTableModel) vista.tableDetalleEntrada.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getInsumo().getCodigoInsumo();
            ob[1] = lista.get(i).getInsumo().getDescripcion();
            ob[2] = lista.get(i).getInsumo().getUnidad();
            ob[3] = lista.get(i).getCantidad();
            ob[4] = lista.get(i).getInsumo().getCosto();
            ob[5] = lista.get(i).getValorTotal();
            modelo.addRow(ob);
        }
        vista.tableDetalleEntrada.setModel(modelo);
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void borrarDetalle() {
        int fila = vista.tableDetalleEntrada.getSelectedRow();
        if (fila > -1) {
            lista.remove(fila);//elimina file de la coleccion
            limpiarTabla();
            showDetalle();
            TotalVenta();
        } else {
            JOptionPane.showMessageDialog(null, "NECESITA ESCOGER UNA FILA\n", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void TotalVenta() {
        DecimalFormat df = new DecimalFormat("#0.00");
        double precioTotal = 0;
        double igv = 0;
        double subtotal = 0;
        for (DetalleEntradas detalleEntradas : lista) {
            precioTotal += detalleEntradas.getValorTotal();
        }
        igv = precioTotal * 0.18;
        subtotal = precioTotal - igv;
        vista.fieldTotalEntrada.setText(String.valueOf(df.format(precioTotal)));
        vista.fieldIgv.setText(String.valueOf(df.format(igv)));
        vista.fieldTOPG.setText(String.valueOf(df.format(subtotal)));
    }

    public void limpiarProductos() {
        vista.fieldCodigoProducto.setText("");
        vista.fieldCantidad.setText("");
        vista.fieldDescripcionProducto.setText("");
        vista.fieldCantidad.setText("");
        vista.fieldValor.setText("");
        vista.fieldStock1.setText("");

        vista.fieldCodigoProducto.requestFocus();
    }

    public void limpiarTodo() {
        vista.fieldNumeroDocumento.setText("");
        vista.fieldProveedor.setText("");
        vista.FieldCodigo.setText("");
        vista.cmbTipo.setSelectedIndex(0);
        vista.fieldTOPG.setText("");
        vista.fieldIgv.setText("");
        vista.fieldStock1.setText("");
        vista.fieldTotalEntrada.setText("");
        limpiarTabla();
        limpiarProductos();
        lista.clear();
    }

    public void getProducto() {
        if (vista.fieldCodigoProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO, DEBE PONER EL CODIGO DEL PRODUCTO \n", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            String key = vista.fieldCodigoProducto.getText();
            Insumos p = new Insumos();
            InsumosDao pdao = new InsumosDao();
            p = pdao.read(key);
            if (p.getCodigoInsumo() != null) {
                vista.fieldDescripcionProducto.setText(p.getDescripcion());
                vista.fieldValor.setText(String.valueOf(p.getCosto()));
                vista.fieldStock1.setText(String.valueOf(p.getStock()));
            } else {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO, NO SE ENCUENTRA EL ID DEL PRODUCTO \n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void guardarEntrada() {
        if (this.vista.fieldProveedor.getText().equals("")
                || this.vista.FieldCodigo.getText().equals("")
                || this.vista.cmbTipo.getSelectedIndex() == 0
                || this.vista.tableDetalleEntrada.getRowCount() == -1) {
            JOptionPane.showMessageDialog(null, "NECESITA LLENAR TODOS LOS CAMPOS \n", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            ProveedorDao pdao = new ProveedorDao();
            Proveedor p = pdao.read(this.vista.fieldProveedor.getText());
            PersonalInventariodao pidao = new PersonalInventariodao();
            PersonalInventario pid = pidao.read(this.vista.FieldCodigo.getText());
            if (p.getNombreDeEmpresa() != null || pid.getApellidos() != null) {
                String ordenEntrada = vista.fieldNumeroDocumento.getText();
                Date fechaEmision = obtenerFecha(vista.fieldFechaDeRegistro.getText());
                String tipo = this.vista.cmbTipo.getSelectedItem().toString();
                Double topg = Double.parseDouble(this.vista.fieldTOPG.getText());
                Double igv = Double.parseDouble(this.vista.fieldIgv.getText());
                Double total = Double.parseDouble(this.vista.fieldTotalEntrada.getText());
                clase.setNumeroDocumentoEntrada(ordenEntrada);
                clase.setProveedor(p);
                clase.setPersonalInventario(pid);
                clase.setTipo(tipo);
                clase.setFechaEmision(fechaEmision);
                clase.setValorTotal(topg);
                clase.setIgv(igv);
                clase.setPrecioTotal(total);
                clase.setDetalleEntradas(lista);
                System.out.println(pid.getApellidos());
                dao.create(clase);
                DetalleEntradasDao dEntrada = new DetalleEntradasDao();
                for (DetalleEntradas detalleentrada : lista) {
                    detalleentrada.setCabeceraEntrada(clase.getNumeroDocumentoEntrada());
                    dEntrada.create(detalleentrada);
                }
                limpiarTodo();
            } else {
                JOptionPane.showMessageDialog(null, "LOS CAMPOS QUE LLENO TIENE FORMACION ERRONEA\n", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public Date obtenerFecha(String fecha) {
        Date fechaD = new Date();
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaD = formato.parse(fecha);

        } catch (ParseException ex) {
            Logger.getLogger(VentaAgregarController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return fechaD;
    }

    public String geTordenDeVentaM() {
        ServicioCantidadEntrada svc = new ServicioCantidadEntrada();
        GenerarCodigoEntrada gc = new GenerarCodigoEntrada();
        int cantidad = svc.getCount();
        if (cantidad == 0) {
            return "E001 - 00001";
        } else {
            return gc.getNum("select * from cabeceraentrada");
        }
    }

}
