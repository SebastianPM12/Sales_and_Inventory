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
public class Empresa {

    private String ruc;
    private String razonSocial;
    private String direccion;
    private int telefono;
    private List<CabeceraDeVenta> cabeceraDeVenta;

    public Empresa() {

    }

    public Empresa(String ruc, String razonSocial, String direccion, int telefono, List<CabeceraDeVenta> cabeceraDeVenta) {
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cabeceraDeVenta = cabeceraDeVenta;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public List<CabeceraDeVenta> getCabeceraDeVenta() {
        return cabeceraDeVenta;
    }

    public void setCabeceraDeVenta(List<CabeceraDeVenta> cabeceraDeVenta) {
        this.cabeceraDeVenta = cabeceraDeVenta;
    }
    
    


}
