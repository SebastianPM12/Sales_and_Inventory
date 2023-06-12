/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO.Venta;

import java.util.List;

/**
 *
 * @author SEBAS
 */
public class Vendedor extends Persona{
    private String idVendedor;
    private String posicion;
    private int dni;
    private List<CabeceraDeVenta> cabeceraDeVenta;

    public Vendedor() {
    }

    public Vendedor(String idVendedor, String posicion, int dni, List<CabeceraDeVenta> cabeceraDeVenta, String nombres, String apellidos, int celular) {
        super(nombres, apellidos, celular);
        this.idVendedor = idVendedor;
        this.posicion = posicion;
        this.dni = dni;
        this.cabeceraDeVenta = cabeceraDeVenta;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public List<CabeceraDeVenta> getCabeceraDeVenta() {
        return cabeceraDeVenta;
    }

    public void setCabeceraDeVenta(List<CabeceraDeVenta> cabeceraDeVenta) {
        this.cabeceraDeVenta = cabeceraDeVenta;
    }

    
    

    
    
    
    
    
    
}
