/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO.Venta;

/**
 *
 * @author SEBAS
 */
public class Comision {
    private int idComision;
    private String cabeceraDeVenta;
    private double comisionVendedor;
    private double comisionAsesor;

    public Comision() {
    }

    public Comision(int idComision, String cabeceraDeVenta, double comisionVendedor, double comisionAsesor) {
        this.idComision = idComision;
        this.cabeceraDeVenta = cabeceraDeVenta;
        this.comisionVendedor = comisionVendedor;
        this.comisionAsesor = comisionAsesor;
    }

    public Comision(String cabeceraDeVenta, double comisionVendedor, double comisionAsesor) {
        this.cabeceraDeVenta = cabeceraDeVenta;
        this.comisionVendedor = comisionVendedor;
        this.comisionAsesor = comisionAsesor;
    }

    public int getIdComision() {
        return idComision;
    }

    public void setIdComision(int idComision) {
        this.idComision = idComision;
    }

    public String getCabeceraDeVenta() {
        return cabeceraDeVenta;
    }

    public void setCabeceraDeVenta(String cabeceraDeVenta) {
        this.cabeceraDeVenta = cabeceraDeVenta;
    }

    public double getComisionVendedor() {
        return comisionVendedor;
    }

    public void setComisionVendedor(double comisionVendedor) {
        this.comisionVendedor = comisionVendedor;
    }

    public double getComisionAsesor() {
        return comisionAsesor;
    }

    public void setComisionAsesor(double comisionAsesor) {
        this.comisionAsesor = comisionAsesor;
    }

    
   
}
