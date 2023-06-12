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
public class Cliente extends Persona{
    private String dniRuc;
    private List<CabeceraDeVenta> cabeceraDeVenta;  


    public Cliente() {
    }

    public Cliente(String dniRuc, List<CabeceraDeVenta> cabeceraDeVenta, String nombres, String apellidos, int celular) {
        super(nombres, apellidos, celular);
        this.dniRuc = dniRuc;
        this.cabeceraDeVenta = cabeceraDeVenta;
    }

    public String getDniRuc() {
        return dniRuc;
    }

    public void setDniRuc(String dniRuc) {
        this.dniRuc = dniRuc;
    }

    public List<CabeceraDeVenta> getCabeceraDeVenta() {
        return cabeceraDeVenta;
    }

    public void setCabeceraDeVenta(List<CabeceraDeVenta> cabeceraDeVenta) {
        this.cabeceraDeVenta = cabeceraDeVenta;
    }

    
  
    
}
