/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao.Venta;

import Modelo.DTO.Conexxion;
import java.sql.*;
/**
 *
 * @author SEBAS
 */
public class GenerarCodigo {
    public String getNum(String sql) {
        String numGen;
        String numObt = null;
        try {
            Connection cn = new Conexxion().Conectar();
            if (cn == null) {
                numGen = null;
            } else {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    numObt = rs.getString(1);
                }
                
                String cod_parte1 = numObt.substring(0, 3);
                String cod_parte2 = numObt.substring(3, 6);
                String cod_parte3 = numObt.substring(6);

                String nueParInt_1 = "";
                String nueParInt_2 = "";
                
                int num_parte_2 = Integer.parseInt(cod_parte3);

                if (num_parte_2 == 99999) {
                    int num_parte_1 = Integer.parseInt(cod_parte1);
                    nueParInt_1 = String.valueOf(num_parte_1 + 1);
                    while (nueParInt_1.length() < 3) {
                        nueParInt_1 = "0" + nueParInt_1;
                    }
                    nueParInt_2 = "00001";
                } else {
                    nueParInt_1 = cod_parte1;
                    nueParInt_2 = String.valueOf(num_parte_2 + 1);
                    while (nueParInt_2.length() < 5) {
                        nueParInt_2 = "0" + nueParInt_2;
                    }
                }
                numGen = nueParInt_1 + cod_parte2 + nueParInt_2;
            }
        } catch (SQLException e) {
            numGen = null;
        }
        return numGen;
    }
    
}
