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
public class CabeceraSalida {

    private String numeroDocumentoSalida;
    private String ordenDeVenta;
    private PersonalInventario personalInventario;
    private Date fechaEmision;
    private double valorTotal;
    private double igv;
    private double precioTotal;
    private List<DetalleSalidas> detalleSalidas;

    public CabeceraSalida() {
    }

    public CabeceraSalida(String numeroDocumentoSalida, String ordenDeVenta, PersonalInventario personalInventario, Date fechaEmision, double valorTotal, double igv, double precioTotal, List<DetalleSalidas> detalleSalidas) {
        this.numeroDocumentoSalida = numeroDocumentoSalida;
        this.ordenDeVenta = ordenDeVenta;
        this.personalInventario = personalInventario;
        this.fechaEmision = fechaEmision;
        this.valorTotal = valorTotal;
        this.igv = igv;
        this.precioTotal = precioTotal;
        this.detalleSalidas = detalleSalidas;
    }

    public String getNumeroDocumentoSalida() {
        return numeroDocumentoSalida;
    }

    public void setNumeroDocumentoSalida(String numeroDocumentoSalida) {
        this.numeroDocumentoSalida = numeroDocumentoSalida;
    }

    public String getOrdenDeVenta() {
        return ordenDeVenta;
    }

    public void setOrdenDeVenta(String ordenDeVenta) {
        this.ordenDeVenta = ordenDeVenta;
    }

    public PersonalInventario getPersonalInventario() {
        return personalInventario;
    }

    public void setPersonalInventario(PersonalInventario personalInventario) {
        this.personalInventario = personalInventario;
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

    public List<DetalleSalidas> getDetalleSalidas() {
        return detalleSalidas;
    }

    public void setDetalleSalidas(List<DetalleSalidas> detalleSalidas) {
        this.detalleSalidas = detalleSalidas;
    }
    
    
}
