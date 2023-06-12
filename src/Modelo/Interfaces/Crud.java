/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo.Interfaces;

import java.util.List;

/**
 *
 * @author SEBAS
 */
public interface Crud <C,c>{
    public boolean create(C clase);
    public List read();
    public C read(c id);
    public boolean update (C clase);
    public boolean delete (c id);
}
