/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventario;

import Controlador.Venta.VentaAgregarController;
import Modelo.DTO.Inventario.CabeceraEntrada;
import Modelo.DTO.Inventario.CabeceraSalida;
import Modelo.DTO.Inventario.DetalleEntradas;
import Modelo.DTO.Inventario.DetalleSalidas;
import Modelo.DTO.Inventario.Insumos;
import Modelo.DTO.Inventario.PersonalInventario;
import Modelo.DTO.Inventario.Proveedor;
import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.Dao.Inventario.CabeceraEntradaDao;
import Modelo.Dao.Inventario.CabeceraSalidaDao;
import Modelo.Dao.Inventario.DetalleEntradasDao;
import Modelo.Dao.Inventario.DetalleSalidasDao;
import Modelo.Dao.Inventario.GenerarCodigoEntrada;
import Modelo.Dao.Inventario.GenerarCodigoSalida;
import Modelo.Dao.Inventario.InsumosDao;
import Modelo.Dao.Inventario.PersonalInventariodao;
import Modelo.Dao.Inventario.ProveedorDao;
import Modelo.Dao.Inventario.ServicioCantidadEntrada;
import Modelo.Dao.Inventario.ServicioCantidadSalida;
import Modelo.Dao.Venta.CabeceraDeVentaDao;
import Vista.Inventario.VentanaAgregarEntrada;
import Vista.Inventario.VentanaAgregarSalida;
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
public class AgregarSalidaController implements ActionListener {

    VentanaAgregarSalida vista;
    CabeceraSalida clase;
    CabeceraSalidaDao dao;
    DefaultTableModel modelo = new DefaultTableModel();
    List<DetalleSalidas> lista = new ArrayList<>();
    DetalleSalidas det = null;

    public AgregarSalidaController(VentanaAgregarSalida vista, CabeceraSalida clase, CabeceraSalidaDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        this.vista.buttonAgregar.addActionListener(this);
        this.vista.buttonGuardarSalida.addActionListener(this);
        this.vista.buttonCancelarSalida.addActionListener(this);
        this.vista.buttonEliminar.addActionListener(this);
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.fieldFechaRegistro.setEditable(false);
        this.vista.fieldNumeroDocumentoSalida.setEditable(false);
        this.vista.fieldNumeroDocumentoSalida.setText(geTordenDeVentaM());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonAgregar) {
            agregarDetaller();
        } else if (e.getSource() == vista.buttonBuscar) {
            getProducto();

        } else if (e.getSource() == vista.buttonCancelarSalida) {
            limpiarTodo();

        } else if (e.getSource() == vista.buttonEliminar) {
            borrarDetalle();

        } else if (e.getSource() == vista.buttonGuardarSalida) {
            guardarEntrada();
            vista.fieldNumeroDocumentoSalida.setText(geTordenDeVentaM());
        }

    }

    public void agregarDetaller() {
        if (vista.fieldCodigoProducto.getText().isEmpty() || vista.fieldDescripcion.getText().isEmpty()
                || vista.Stock.getText().isEmpty() || vista.fieldCodigoProducto.getText().isEmpty()
                || Integer.parseInt(vista.cantidad.getText()) <= 0 || Integer.parseInt(vista.Stock.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO, NO HAY DATOS PARA REGISTRAR\n", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            det = new DetalleSalidas();
            DecimalFormat df = new DecimalFormat("#0.00");
            String key = vista.fieldCodigoProducto.getText();
            Insumos p = new Insumos();
            InsumosDao pdao = new InsumosDao();
            p = pdao.read(key);
            int cantidad = Integer.parseInt(vista.cantidad.getText());
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
        modelo = (DefaultTableModel) vista.tableDetalleSalida.getModel();
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
        vista.tableDetalleSalida.setModel(modelo);
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void borrarDetalle() {
        int fila = vista.tableDetalleSalida.getSelectedRow();
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
        for (DetalleSalidas detalleSalidas : lista) {
            precioTotal += detalleSalidas.getValorTotal();
        }
        igv = precioTotal * 0.18;
        subtotal = precioTotal - igv;
        vista.fieldTotalEntrada.setText(String.valueOf(df.format(precioTotal)));
        vista.fieldIgv.setText(String.valueOf(df.format(igv)));
        vista.fieldTOPG.setText(String.valueOf(df.format(subtotal)));
    }

    public void limpiarProductos() {
        vista.fieldCodigoProducto.setText("");
        vista.cantidad.setText("");
        vista.fieldDescripcion.setText("");
        vista.cantidad.setText("");
        vista.Stock.setText("");
        vista.fieldValor.setText("");
        vista.fieldCodigoProducto.requestFocus();
    }

    public void limpiarTodo() {
        vista.fieldNumeroDocumentoSalida.setText("");
        vista.fieldOrdenDeVenta.setText("");
        vista.fieldCodigoGestor.setText("");
        vista.fieldTOPG.setText("");
        vista.fieldIgv.setText("");
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
                vista.fieldDescripcion.setText(p.getDescripcion());
                vista.fieldValor.setText(String.valueOf(p.getCosto()));
                vista.Stock.setText(String.valueOf(p.getStock()));
            } else {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO, NO SE ENCUENTRA EL ID DEL PRODUCTO \n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void guardarEntrada() {
        if (this.vista.fieldOrdenDeVenta.getText().equals("")
                || this.vista.fieldCodigoGestor.getText().equals("")
                || this.vista.fieldTotalEntrada.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "NECESITA LLENAR TODOS LOS CAMPOS \n", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            PersonalInventariodao pidao = new PersonalInventariodao();
            PersonalInventario pid = pidao.read(this.vista.fieldCodigoGestor.getText());
            CabeceraDeVentaDao vdao = new CabeceraDeVentaDao();
            CabeceraDeVenta cv = vdao.read(this.vista.fieldOrdenDeVenta.getText());
            if (cv.getOrdendeventa()!= null || pid.getApellidos() != null) {
                String ordenSalida = vista.fieldNumeroDocumentoSalida.getText();
                Date fechaEmision = obtenerFecha(vista.fieldFechaRegistro.getText());
                Double topg = Double.parseDouble(this.vista.fieldTOPG.getText());
                Double igv = Double.parseDouble(this.vista.fieldIgv.getText());
                Double total = Double.parseDouble(this.vista.fieldTotalEntrada.getText());
                clase.setNumeroDocumentoSalida(ordenSalida);
                clase.setPersonalInventario(pid);
                clase.setOrdenDeVenta(cv.getOrdendeventa());
                clase.setFechaEmision(fechaEmision);
                clase.setValorTotal(topg);
                clase.setIgv(igv);
                clase.setPrecioTotal(total);
                clase.setDetalleSalidas(lista);
                dao.create(clase);
                DetalleSalidasDao dSalida = new DetalleSalidasDao();
                for (DetalleSalidas detalleSalidas : lista) {
                    detalleSalidas.setCabeceraSalida(clase.getNumeroDocumentoSalida());
                    dSalida.create(detalleSalidas);
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
        ServicioCantidadSalida svc = new ServicioCantidadSalida();
        GenerarCodigoSalida gc = new GenerarCodigoSalida();
        int cantidad = svc.getCount();
        if (cantidad == 0) {
            return "S001 - 00001";
        } else {
            return gc.getNum("select * from cabecerasalida");
        }
    }

}
