/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO.Inventario;

/**
 *
 * @author SEBAS
 */
public class DetalleEntradas {

    private String cabeceraEntrada;
    private Insumos insumo;
    private int cantidad;
    private double valorTotal;

    public DetalleEntradas() {
    }

    public DetalleEntradas(String cabeceraEntrada, Insumos insumo, int cantidad, double valorTotal) {
        this.cabeceraEntrada = cabeceraEntrada;
        this.insumo = insumo;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
    }

    public String getCabeceraEntrada() {
        return cabeceraEntrada;
    }

    public void setCabeceraEntrada(String cabeceraEntrada) {
        this.cabeceraEntrada = cabeceraEntrada;
    }

    public Insumos getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumos insumo) {
        this.insumo = insumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

   
}
