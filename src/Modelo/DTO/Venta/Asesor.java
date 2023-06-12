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
public class Asesor extends Persona {

    private int dni;
    private String posicion;
    private List<CabeceraDeVenta> cabeceraDeVenta;  

    public Asesor() {
    }

    public Asesor(int dni, String posicion, List<CabeceraDeVenta> cabeceraDeVenta) {
        this.dni = dni;
        this.posicion = posicion;
        this.cabeceraDeVenta = cabeceraDeVenta;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public List<CabeceraDeVenta> getCabeceraDeVenta() {
        return cabeceraDeVenta;
    }

    public void setCabeceraDeVenta(List<CabeceraDeVenta> cabeceraDeVenta) {
        this.cabeceraDeVenta = cabeceraDeVenta;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

  

}
