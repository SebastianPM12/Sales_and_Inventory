/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Venta;

import Modelo.DTO.Venta.Asesor;
import Modelo.DTO.Venta.Cliente;
import Modelo.DTO.Venta.Empresa;
import Modelo.DTO.Venta.Producto;
import Modelo.DTO.Venta.Vendedor;
import Modelo.Dao.Venta.AsesorDao;
import Modelo.Dao.Venta.ClienteDao;
import Modelo.Dao.Venta.EmpresaDao;
import Modelo.Dao.Venta.ProductoDao;
import Modelo.Dao.Venta.VendedorDao;
import Vista.Inicio.VentanaDeInicio;
import Vista.Ventas.VentanaAsesores;
import Vista.Ventas.VentanaClientes;
import Vista.Ventas.VentanaMantenimiento;
import Vista.Ventas.VentanaNegocio;
import Vista.Ventas.VentanaProductos;
import Vista.Ventas.VentanaUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SEBAS
 */
public class MantenimientoController implements ActionListener {

    VentanaMantenimiento vista;

    public MantenimientoController(VentanaMantenimiento vista) {
        this.vista = vista;
        this.vista.buttonAsesores.addActionListener(this);
        this.vista.buttonOtros.addActionListener(this);
        this.vista.buttonVendedores.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.buttonAsesores) {
            VentanaAsesores vaa = new VentanaAsesores();
            vaa.setVisible(true);
            vista.setVisible(false);

        } else if (e.getSource() == vista.buttonOtros) {
            VentanaNegocio vn = new VentanaNegocio();
            vn.setVisible(true);
            vista.setVisible(false);;

        } else if (e.getSource() == vista.buttonVendedores) {
            VentanaUsuarios ventana = new VentanaUsuarios();
            ventana.setVisible(true);
            vista.setVisible(false);

        }
    }

}
