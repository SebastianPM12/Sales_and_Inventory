/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO.Venta;

/**
 *
 * @author SEBAS
 */
public class Producto {

    private String codigo;
    private double precioUnitario;
    private double valor;
    private String capacidad;
    private String tipo;
    private String descripcion;
    private String largo;
    private String ancho;
    private String alto;

    public Producto() {
    }

    public Producto(String codigo, double precioUnitario, double valor, String capacidad, String tipo, String descripcion, String largo, String ancho, String alto) {
        this.codigo = codigo;
        this.precioUnitario = precioUnitario;
        this.valor = valor;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }
    
    

   
}
