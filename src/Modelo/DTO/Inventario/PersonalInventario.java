/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO.Inventario;

import Modelo.DTO.Venta.DetalleDeVenta;
import java.util.List;

/**
 *
 * @author SEBAS
 */
public class PersonalInventario {

    private String idPersonal;
    private String nombres;
    private String apellidos;
    private int dni;
    private int celular;
    private String posicion;
    private List<CabeceraEntrada> cabeceraEntrada;
    private List<CabeceraSalida> cabeceraSalida;

    public PersonalInventario() {
    }

    public PersonalInventario(String idPersonal, String nombres, String apellidos, int dni, int celular, String posicion, List<CabeceraEntrada> cabeceraEntrada, List<CabeceraSalida> cabeceraSalida) {
        this.idPersonal = idPersonal;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.celular = celular;
        this.posicion = posicion;
        this.cabeceraEntrada = cabeceraEntrada;
        this.cabeceraSalida = cabeceraSalida;
    }

    public String getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(String idPersonal) {
        this.idPersonal = idPersonal;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public List<CabeceraEntrada> getCabeceraEntrada() {
        return cabeceraEntrada;
    }

    public void setCabeceraEntrada(List<CabeceraEntrada> cabeceraEntrada) {
        this.cabeceraEntrada = cabeceraEntrada;
    }

    public List<CabeceraSalida> getCabeceraSalida() {
        return cabeceraSalida;
    }

    public void setCabeceraSalida(List<CabeceraSalida> cabeceraSalida) {
        this.cabeceraSalida = cabeceraSalida;
    }

    @Override
    public String toString() {
        return "PersonalInventario{" + "idPersonal=" + idPersonal + ", nombres=" + nombres + ", apellidos=" + apellidos + ", dni=" + dni + ", celular=" + celular + ", posicion=" + posicion + ", cabeceraEntrada=" + cabeceraEntrada + ", cabeceraSalida=" + cabeceraSalida + '}';
    }
    
    


}
