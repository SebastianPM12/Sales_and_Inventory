/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO.Inventario;

import Modelo.DTO.Venta.DetalleDeVenta;
import java.util.List;

/**
 *
 * @author SEBAS
 */
public class Proveedor {
    private String idProveedor;
    private String nombreDeEmpresa;
    private String direccion;
    private int celular;
    private List<CabeceraEntrada> deatallesDeVenta;

    public Proveedor() {
    }

    public Proveedor(String idProveedor, String nombreDeEmpresa, String direccion, int celular, List<CabeceraEntrada> deatallesDeVenta) {
        this.idProveedor = idProveedor;
        this.nombreDeEmpresa = nombreDeEmpresa;
        this.direccion = direccion;
        this.celular = celular;
        this.deatallesDeVenta = deatallesDeVenta;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    
    public String getNombreDeEmpresa() {
        return nombreDeEmpresa;
    }

    public void setNombreDeEmpresa(String nombreDeEmpresa) {
        this.nombreDeEmpresa = nombreDeEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public List<CabeceraEntrada> getDeatallesDeVenta() {
        return deatallesDeVenta;
    }

    public void setDeatallesDeVenta(List<CabeceraEntrada> deatallesDeVenta) {
        this.deatallesDeVenta = deatallesDeVenta;
    }
    
}
