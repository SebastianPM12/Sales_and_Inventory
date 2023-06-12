/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO.Venta;

import java.util.List;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author SEBAS
 */
public class CabeceraDeVenta {

    private String ordendeventa;
    private Cliente cliente;
    private Empresa empresa;
    private Date fechaEmision;
    private Asesor asesor;
    private Vendedor vendedor;
    private Comision idComision;
    private double valorTotal;
    private double igv;
    private double precioTotal;
    private List<DetalleDeVenta> deatallesDeVenta;

    public CabeceraDeVenta() {
    }

    public CabeceraDeVenta(String ordendeventa, Cliente cliente, Empresa empresa, Date fechaEmision, Asesor asesor, Vendedor vendedor, Comision idComision, double valorTotal, double igv, double precioTotal, List<DetalleDeVenta> deatallesDeVenta) {
        this.ordendeventa = ordendeventa;
        this.cliente = cliente;
        this.empresa = empresa;
        this.fechaEmision = fechaEmision;
        this.asesor = asesor;
        this.vendedor = vendedor;
        this.idComision = idComision;
        this.valorTotal = valorTotal;
        this.igv = igv;
        this.precioTotal = precioTotal;
        this.deatallesDeVenta = deatallesDeVenta;
    }

    public String getOrdendeventa() {
        return ordendeventa;
    }

    public void setOrdendeventa(String ordendeventa) {
        this.ordendeventa = ordendeventa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Asesor getAsesor() {
        return asesor;
    }

    public void setAsesor(Asesor asesor) {
        this.asesor = asesor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Comision getIdComision() {
        return idComision;
    }

    public void setIdComision(Comision idComision) {
        this.idComision = idComision;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<DetalleDeVenta> getDeatallesDeVenta() {
        return deatallesDeVenta;
    }

    public void setDeatallesDeVenta(List<DetalleDeVenta> deatallesDeVenta) {
        this.deatallesDeVenta = deatallesDeVenta;
    }

  


}
