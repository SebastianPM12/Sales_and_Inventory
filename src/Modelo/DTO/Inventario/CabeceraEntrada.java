/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO.Inventario;

import java.util.Date;
import java.util.List;

/**
 *
 * @author SEBAS
 */
public class CabeceraEntrada {
    private String numeroDocumentoEntrada;
    private PersonalInventario personalInventario;
    private Proveedor proveedor;
    private String tipo;
    private Date fechaEmision;
    private double valorTotal;
    private double igv;
    private double precioTotal;
    private List<DetalleEntradas> detalleEntradas;

    public CabeceraEntrada() {
    }

    public CabeceraEntrada(String numeroDocumentoEntrada, PersonalInventario personalInventario, Proveedor proveedor, String tipo, Date fechaEmision, double valorTotal, double igv, double precioTotal, List<DetalleEntradas> detalleEntradas) {
        this.numeroDocumentoEntrada = numeroDocumentoEntrada;
        this.personalInventario = personalInventario;
        this.proveedor = proveedor;
        this.tipo = tipo;
        this.fechaEmision = fechaEmision;
        this.valorTotal = valorTotal;
        this.igv = igv;
        this.precioTotal = precioTotal;
        this.detalleEntradas = detalleEntradas;
    }

    public String getNumeroDocumentoEntrada() {
        return numeroDocumentoEntrada;
    }

    public void setNumeroDocumentoEntrada(String numeroDocumentoEntrada) {
        this.numeroDocumentoEntrada = numeroDocumentoEntrada;
    }

    public PersonalInventario getPersonalInventario() {
        return personalInventario;
    }

    public void setPersonalInventario(PersonalInventario personalInventario) {
        this.personalInventario = personalInventario;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
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

    public List<DetalleEntradas> getDetalleEntradas() {
        return detalleEntradas;
    }

    public void setDetalleEntradas(List<DetalleEntradas> detalleEntradas) {
        this.detalleEntradas = detalleEntradas;
    }
    
    
}
