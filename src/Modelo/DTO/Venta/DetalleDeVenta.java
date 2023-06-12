/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO.Venta;

/**
 *
 * @author SEBAS
 */
public class DetalleDeVenta {
    private String idCabezeraDeVenta;
    private Producto producto;
    private int cantidad;
    private double abono;
    private String abonoP;  
    private double porCobrar;
    private double precioTotal;

    public DetalleDeVenta() {
    }

    public DetalleDeVenta(String idCabezeraDeVenta, Producto producto, int cantidad, double abono, String abonoP, double porCobrar, double precioTotal) {
        this.idCabezeraDeVenta = idCabezeraDeVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.abono = abono;
        this.abonoP = abonoP;
        this.porCobrar = porCobrar;
        this.precioTotal = precioTotal;
    }

    public String getIdCabezeraDeVenta() {
        return idCabezeraDeVenta;
    }

    public void setIdCabezeraDeVenta(String idCabezeraDeVenta) {
        this.idCabezeraDeVenta = idCabezeraDeVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public String getAbonoP() {
        return abonoP;
    }

    public void setAbonoP(String abonoP) {
        this.abonoP = abonoP;
    }

    public double getPorCobrar() {
        return porCobrar;
    }

    public void setPorCobrar(double porCobrar) {
        this.porCobrar = porCobrar;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "DetalleDeVenta{" + "idCabezeraDeVenta=" + idCabezeraDeVenta + ", producto=" + producto + ", cantidad=" + cantidad + ", abono=" + abono + ", abonoP=" + abonoP + ", porCobrar=" + porCobrar + ", precioTotal=" + precioTotal + '}';
    }

   

    
}
