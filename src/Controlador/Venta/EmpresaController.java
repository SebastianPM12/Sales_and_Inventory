/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.Empresa;
import Modelo.DTO.Venta.Producto;
import Modelo.Dao.Venta.EmpresaDao;
import Modelo.Dao.Venta.ProductoDao;
import Vista.Ventas.VentanaNegocio;
import Vista.Ventas.VentanaProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEBAS
 */
public class EmpresaController implements ActionListener {

    String ruc;
    VentanaNegocio vista;
    Empresa clase;
    EmpresaDao dao;

    public EmpresaController(VentanaNegocio vista, Empresa clase, EmpresaDao dao) {
        this.vista = vista;
        this.clase = clase;
        this.dao = dao;
        listar();
        this.vista.buttonGuardarCambios.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonGuardarCambios) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas Seguro de modificar?", "Confirmar", dialogButton);
            actualizar();
            listar();
        }
    }

    void listar() {
        List<Empresa> lista = dao.read();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getRuc();
            ob[1] = lista.get(i).getRazonSocial();
            ob[2] = lista.get(i).getDireccion();
            ob[3] = lista.get(i).getTelefono();
        }
        vista.fieldRuc.setText(lista.get(0).getRuc());
        vista.fieldRazonSocial.setText(lista.get(0).getRazonSocial());
        vista.fieldDireccion.setText(lista.get(0).getDireccion());
        vista.fieldTelefono.setText(String.valueOf(lista.get(0).getTelefono()));
    }

    void actualizar() {

        if (vista.fieldRazonSocial.getText().equals("") || vista.fieldDireccion.getText().equals("")
                || vista.fieldTelefono.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            String ruc = vista.fieldRuc.getText();
            String razonSocial = vista.fieldRazonSocial.getText();
            String direccion = vista.fieldDireccion.getText();
            int telefono = Integer.parseInt(vista.fieldTelefono.getText());
            Empresa emp = new Empresa();
            emp.setRuc(ruc);
            emp.setRazonSocial(razonSocial);
            emp.setDireccion(direccion);
            emp.setTelefono(telefono);
            dao.update(emp);
        }
    }
}

