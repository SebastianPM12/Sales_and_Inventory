/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO.Inventario;

/**
 *
 * @author SEBAS
 */
public class Insumos {
    private String codigoInsumo;
    private double costo;
    private Proveedor proveedor;
    private String descripcion;
    private String tipo;
    private String area;
    private String unidad;
    private int entradas; 
    private int salidas;
    private int stock;

    public Insumos() {
    }

    public Insumos(String codigoInsumo, double costo, Proveedor proveedor, String descripcion, String tipo, String area, String unidad, int entradas, int salidas, int stock) {
        this.codigoInsumo = codigoInsumo;
        this.costo = costo;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.area = area;
        this.unidad = unidad;
        this.entradas = entradas;
        this.salidas = salidas;
        this.stock = stock;
    }

    public String getCodigoInsumo() {
        return codigoInsumo;
    }

    public void setCodigoInsumo(String codigoInsumo) {
        this.codigoInsumo = codigoInsumo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getSalidas() {
        return salidas;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Insumos{" + "codigoInsumo=" + codigoInsumo + ", costo=" + costo + ", proveedor=" + proveedor + ", descripcion=" + descripcion + ", tipo=" + tipo + ", area=" + area + ", unidad=" + unidad + ", entradas=" + entradas + ", salidas=" + salidas + ", stock=" + stock + '}';
    }
    

}
