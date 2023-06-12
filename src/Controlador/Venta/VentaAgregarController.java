/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.Asesor;
import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.DTO.Venta.Cliente;
import Modelo.DTO.Venta.Comision;
import Modelo.DTO.Venta.DetalleDeVenta;
import Modelo.DTO.Venta.Empresa;
import Modelo.DTO.Venta.Producto;
import Modelo.DTO.Venta.Vendedor;
import Modelo.Dao.Venta.AsesorDao;
import Modelo.Dao.Venta.CabeceraDeVentaDao;
import Modelo.Dao.Venta.ClienteDao;
import Modelo.Dao.Venta.ComisionDao;
import Modelo.Dao.Venta.DetalleDeVentaDao;
import Modelo.Dao.Venta.EmpresaDao;
import Modelo.Dao.Venta.GenerarCodigo;
import Modelo.Dao.Venta.ProductoDao;
import Modelo.Dao.Venta.ServicioCantidad;
import Modelo.Dao.Venta.VendedorDao;
import Vista.Ventas.VentanaAgregarVentas;
import Vista.Ventas.VentanaComision;
import Vista.Ventas.VentanaComisionValores;
import java.awt.Label;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEBAS
 */
public class VentaAgregarController implements ActionListener {

    String ordenDeVenta;
    VentanaAgregarVentas vista;
    CabeceraDeVenta clase;
    CabeceraDeVentaDao dao1;
    DefaultTableModel modelo = new DefaultTableModel();
    DetalleDeVenta det = null;
    List<DetalleDeVenta> lista = new ArrayList<>();

    public VentaAgregarController(VentanaAgregarVentas vista, CabeceraDeVenta clase, CabeceraDeVentaDao dao1) {
        this.vista = vista;
        this.clase = clase;
        this.dao1 = dao1;
        this.vista.agregarDetalle.addActionListener(this);
        this.vista.cancelarVentaButton.addActionListener(this);
        this.vista.crearVentaButton.addActionListener(this);
        this.vista.buttonBuscarProducto.addActionListener(this);
        this.vista.eliminarDetalle.addActionListener(this);
        this.vista.buttonCambiarP.addActionListener(this);
        this.vistaComisionp.buttonCambiarPorcentaje.addActionListener(this);
        this.vista.labelOrdenDeVenta.setText(geTordenDeSalidaC());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.agregarDetalle) {
            this.vista.labelOrdenDeVenta.setText(geTordenDeSalidaC());
            agregarDetaller();

        } else if (e.getSource() == vista.cancelarVentaButton) {
            this.vista.labelOrdenDeVenta.setText(geTordenDeSalidaC());

        } else if (e.getSource() == vista.buttonBuscarProducto) {
            this.vista.labelOrdenDeVenta.setText(geTordenDeSalidaC());
            getProducto();

        } else if (e.getSource() == vista.eliminarDetalle) {
            this.vista.labelOrdenDeVenta.setText(geTordenDeSalidaC());
            borrarDetalle();
        } else if (e.getSource() == vista.buttonCambiarP) {
            vistaComisionp.setVisible(true);

        } else if (e.getSource() == vistaComisionp.buttonCambiarPorcentaje) {
            this.vista.labelOrdenDeVenta.setText(geTordenDeSalidaC());
            comisionesAsesor();
            comisionVendedor();
        } else if (e.getSource() == vista.crearVentaButton) {
            this.vista.labelOrdenDeVenta.setText(geTordenDeSalidaC());
            crearVenta();
            limpiarTodo();
            limpiarTabla();
        }

    }

    public void getProducto() {
        if (vista.fieldCodigoProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO, DEBE PONER EL CODIGO DEL PRODUCTO \n", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            String key = vista.fieldCodigoProducto.getText();
            Producto p = new Producto();
            ProductoDao pdao = new ProductoDao();
            p = pdao.read(key);
            if (p.getCodigo() != null) {
                vista.fieldDescripcion.setText(p.getDescripcion());
                vista.fieldPunit.setText(String.valueOf(p.getPrecioUnitario()));
            } else {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO, NO SE ENCUENTRA EL ID DEL PRODUCTO \n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void limpiarProductos() {
        vista.fieldCodigoProducto.setText("");
        vista.fieldPunit.setText("");
        vista.fieldDescripcion.setText("");
        vista.fieldCantidad.setText("");
        vista.fieldAbono.setText("");
        vista.fieldCodigoProducto.requestFocus();
    }

    public void limpiarTodo() {
        vista.fieldDniRuc.setText("");
        vista.fieldCliente.setText("");
        vista.fieldAsesor.setText("");
        vista.fieldVendedor.setText("");
        vista.fieldComisionAsesor.setText("");
        vista.fieldComisionVendedor.setText("");
        vista.fieldTOPG.setText("");
        vista.fieldIgv.setText("");
        vista.fieldTotalFactura.setText("");
        limpiarProductos();
    }

    public void crearVenta() {
       
        if (vista.fieldDniRuc.getText().equals("") || vista.fieldCliente.getText().equals("")
                || vista.fieldVendedor.getText().equals("") || vista.fieldAsesor.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO, DEBE LLENAR TODOS LOS CAMPOS CORRESPONDIENTES \n", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            String dni = vista.fieldDniRuc.getText();
            int asesor = Integer.parseInt(vista.fieldAsesor.getText());
            String vendedor = vista.fieldVendedor.getText();
            ClienteDao cdao = new ClienteDao();
            Cliente c = cdao.read(dni);
            AsesorDao adao = new AsesorDao();
            Asesor a = adao.read(asesor);
            VendedorDao vdao = new VendedorDao();
            Vendedor v = vdao.read(dni);
            
            
            if ((!(c.getNombres() == null)) || (!(v.getNombres()== null)) || (!(a.getNombres()== null))) {
                String ordenDeVenta = vista.labelOrdenDeVenta.getText();
                String ruc = vista.labelRuc.getText();
                Date fechaEmision = obtenerFecha(vista.fieldFecha.getText());
                double valorT = Double.parseDouble(vista.fieldTOPG.getText());
                double igv = Double.parseDouble(vista.fieldIgv.getText());
                double pTotal = Double.parseDouble(vista.fieldTotalFactura.getText());
                EmpresaDao empdao = new EmpresaDao();
                Empresa emp = empdao.read(ruc);
                clase = new CabeceraDeVenta();
                clase.setOrdendeventa(ordenDeVenta);
                clase.setCliente(c);
                clase.setEmpresa(emp);
                clase.setValorTotal(valorT);
                clase.setIgv(igv);
                clase.setVendedor(v);
                clase.setAsesor(a);
                clase.setPrecioTotal(pTotal);
                clase.setDeatallesDeVenta(lista);
                clase.setFechaEmision(fechaEmision);
                DetalleDeVentaDao dventaDao = new DetalleDeVentaDao();
                ComisionDao comisiondao = new ComisionDao();
                double comiVendedor = Double.parseDouble(vista.fieldComisionVendedor.getText());
                double comiAsesor = Double.parseDouble(vista.fieldComisionAsesor.getText());
                boolean bo = dao1.create(clase);
                for (DetalleDeVenta detalleDeVenta : lista) {
                    detalleDeVenta.setIdCabezeraDeVenta(ordenDeVenta);
                    dventaDao.create(detalleDeVenta);
                }
                Comision cm = new Comision(ordenDeVenta, comiVendedor, comiAsesor);
                comisiondao.create(cm);
                lista.clear();
            } else {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO, UNO DE LOS DATOS QUE INGRESO NO ESTA EN LOS REGISTROS\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void agregarDetaller() {
        if (vista.fieldCodigoProducto.getText().isEmpty() || vista.fieldPunit.getText().isEmpty()
                || vista.fieldDescripcion.getText().isEmpty() || vista.fieldCantidad.getText().isEmpty()
                || vista.fieldAbono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO, NO HAY DATOS PARA REGISTRAR\n", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            det = new DetalleDeVenta();
            DecimalFormat df = new DecimalFormat("#0.00");
            String key = vista.fieldCodigoProducto.getText();
            Producto p = new Producto();
            ProductoDao pdao = new ProductoDao();
            p = pdao.read(key);
            int cantidad = Integer.parseInt(vista.fieldCantidad.getText());
            double precioTotal = (p.getPrecioUnitario() * cantidad);
            double abono = Double.parseDouble(vista.fieldAbono.getText());
            double abonoPorcentaje = ((100 * abono) / precioTotal);
            double porCobrar = (precioTotal - abono);
            if (abonoPorcentaje > 100.0) {
                JOptionPane.showMessageDialog(null, "El abono supera el precio de venta Total\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                det.setProducto(p);
                det.setCantidad(cantidad);
                det.setAbono(abono);
                det.setAbonoP(df.format(abonoPorcentaje) + "%");
                det.setPorCobrar(porCobrar);
                det.setPrecioTotal(precioTotal);
                lista.add(det);
                limpiarTabla();
                showDetalle();
                TotalVenta();
                limpiarProductos();
            }
        }
    }

    public void showDetalle() {
        modelo = (DefaultTableModel) vista.tableDetalleDeVenta.getModel();
        Object[] ob = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getProducto().getCodigo();
            ob[1] = lista.get(i).getProducto().getDescripcion();
            ob[2] = lista.get(i).getCantidad();
            ob[3] = lista.get(i).getAbono();
            ob[4] = lista.get(i).getAbonoP();
            ob[5] = lista.get(i).getPorCobrar();
            ob[6] = lista.get(i).getProducto().getPrecioUnitario();
            ob[7] = lista.get(i).getPrecioTotal();
            modelo.addRow(ob);
        }
        vista.tableDetalleDeVenta.setModel(modelo);
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void borrarDetalle() {
        int fila = vista.tableDetalleDeVenta.getSelectedRow();
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
        for (DetalleDeVenta detalleDeVenta : lista) {
            precioTotal += detalleDeVenta.getPrecioTotal();
        }
        igv = precioTotal * 0.18;
        subtotal = precioTotal - igv;
        vista.fieldTotalFactura.setText(String.valueOf(df.format(precioTotal)));
        vista.fieldIgv.setText(String.valueOf(df.format(igv)));
        vista.fieldTOPG.setText(String.valueOf(df.format(subtotal)));
        comisionesAsesor();
        comisionVendedor();
    }

    VentanaComisionValores vistaComisionp = new VentanaComisionValores();

    public void comisionesAsesor() {
        if (vista.fieldAsesor.getText().equals("")) {
            vista.fieldComisionAsesor.setText(0.0 + "");
        } else {
            DecimalFormat df = new DecimalFormat("#0.00");
            double comisionAsesor = 0;
            double pasesor = Double.parseDouble(vistaComisionp.fieldAsesorVariable.getText());
            double precioTotal = Double.parseDouble(vista.fieldTOPG.getText());
            comisionAsesor = (precioTotal * pasesor) / 100;
            vista.fieldComisionAsesor.setText(df.format(comisionAsesor) + "");
        }

    }

    public void comisionVendedor() {
        if (vista.fieldVendedor.getText().equals("")) {
            vista.fieldComisionVendedor.setText(0.0 + "");
        } else {
            DecimalFormat df = new DecimalFormat("#0.00");
            double comisionVendedor = 0;
            double pvendedor = Double.parseDouble(vistaComisionp.fieldVendedorVariable.getText());
            double precioTotal = Double.parseDouble(vista.fieldTOPG.getText());
            comisionVendedor = (precioTotal * pvendedor) / 100;
            vista.fieldComisionVendedor.setText(df.format(comisionVendedor) + "");
        }

    }

    public String geTordenDeSalidaC() {
        ServicioCantidad svc = new ServicioCantidad();
        GenerarCodigo gc = new GenerarCodigo();
        int cantidad = svc.getCount();
        if (cantidad == 0) {
            return "001 - 00001";
        } else {
            return gc.getNum("select * from cabeceradeventas");
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

}
