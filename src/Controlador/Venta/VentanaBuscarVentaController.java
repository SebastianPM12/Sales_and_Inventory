/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.CabeceraDeVenta;
import Modelo.DTO.Venta.Comision;
import Modelo.Dao.Venta.CabeceraDeVentaDao;
import Modelo.Dao.Venta.ComisionDao;
import Vista.Ventas.VentanaBuscarVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEBAS
 */
public class VentanaBuscarVentaController implements ActionListener {

    VentanaBuscarVentas vista;
    CabeceraDeVenta clase;
    CabeceraDeVentaDao cdao;
    DefaultTableModel modelo = new DefaultTableModel();

    public VentanaBuscarVentaController(VentanaBuscarVentas vista, CabeceraDeVenta clase, CabeceraDeVentaDao cdao) {
        this.vista = vista;
        this.clase = clase;
        this.cdao = cdao;
        this.vista.buttonBuscar.addActionListener(this);
        this.vista.buttonLimpiar.addActionListener(this);
        this.vista.pdf.addActionListener(this);
        limpiarTabla();
        limpiarTodo();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonBuscar) {
            mostrarFactura();
        } else if (e.getSource() == vista.buttonLimpiar) {
            limpiarTodo();

        } else if (e.getSource()==vista.pdf){
            
        }
    }

    void limpiarTodo() {
        vista.fieldKey.setText("");
        vista.fieldNumeroOrden.setText("");
        vista.fieldRucDni.setText("");
        vista.fieldCliente.setText("");
        vista.fieldFecha.setText("");
        vista.fieldAsesor.setText("");
        vista.fieldVendedor.setText("");
        vista.fieldComisionAsesor.setText("");
        vista.fieldComisionVendedor.setText("");
        vista.fieldTOPGV.setText("");
        vista.fieldIGV.setText("");
        vista.fieldFactura.setText("");
        limpiarTabla();
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void mostrarFactura() {
        if (vista.fieldKey.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + "EL CODIGO DE VENTA NO SE ENCUENTRA", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            clase = cdao.read(vista.fieldKey.getText());
            if (clase.getOrdendeventa() != null) {
                ComisionDao cmdao = new ComisionDao();
                Comision cms = cmdao.read(vista.fieldKey.getText());
                limpiarTodo();
                vista.fieldNumeroOrden.setText(clase.getOrdendeventa() + "");
                vista.fieldRucDni.setText(clase.getCliente().getDniRuc() + "");
                vista.fieldCliente.setText(clase.getCliente().getNombres() + "");
                vista.fieldFecha.setText(clase.getFechaEmision() + "");
                vista.fieldAsesor.setText(clase.getAsesor().getDni() + "");
                vista.fieldVendedor.setText(clase.getVendedor().getIdVendedor() + "");
                vista.fieldComisionAsesor.setText(cms.getComisionAsesor() + "");
                vista.fieldComisionVendedor.setText(cms.getComisionVendedor() + "");
                vista.fieldTOPGV.setText(clase.getValorTotal() + "");
                vista.fieldIGV.setText(clase.getIgv() + "");
                vista.fieldFactura.setText(clase.getPrecioTotal() + "");
                modelo = (DefaultTableModel) vista.tablev.getModel();
                Object[] ob = new Object[8];
                for (int i = 0; i < clase.getDeatallesDeVenta().size(); i++) {
                    ob[0] = clase.getDeatallesDeVenta().get(i).getProducto().getCodigo();
                    ob[1] = clase.getDeatallesDeVenta().get(i).getProducto().getDescripcion();
                    ob[2] = clase.getDeatallesDeVenta().get(i).getCantidad();
                    ob[3] = clase.getDeatallesDeVenta().get(i).getAbono();
                    ob[4] = clase.getDeatallesDeVenta().get(i).getAbonoP();
                    ob[5] = clase.getDeatallesDeVenta().get(i).getPorCobrar();
                    ob[6] = clase.getDeatallesDeVenta().get(i).getProducto().getPrecioUnitario();
                    ob[7] = clase.getDeatallesDeVenta().get(i).getPrecioTotal();
                    modelo.addRow(ob);
                }
                vista.tablev.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "HUBO UN FALLO \n" + "EL CODIGO DE VENTA NO SE ENCUENTRA", "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        }
    }
    /*
    void pdfImp(){
        try {
            FileOutputStream archivo;
            File file = new File("src/pdf/venta.pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            Pdf
        } catch (Exception e) {
        } finally {
        }
    }
*/
}
