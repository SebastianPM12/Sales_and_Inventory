/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DTO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author SEBAS
 */
public class Conexxion {

    Connection con;
    String url = "jdbc:mysql://localhost:3307/medgarperu?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String pass = "";

    public Connection Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
        }
        return con;
    }
    
     /*
    java.sql.Connection con;
    String url = "jdbc:mysql://localhost:3307/consecionaria?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "2828d7pakz5afzetfbjn";
    String pass = "pscale_pw_ombvQWUD7vuXus4jbtXgm2WWE6NFx9SwxTSfRnArEUu";

    public java.sql.Connection Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://aws-sa-east-1.connect.psdb.cloud/basededatosprueba?sslMode=VERIFY_IDENTITY",
                    "2828d7pakz5afzetfbjn",
                    "pscale_pw_ombvQWUD7vuXus4jbtXgm2WWE6NFx9SwxTSfRnArEUu");
            System.out.println("SALIO WACHOO, HOMAR CABRO");

        } catch (Exception e) {
        }
        return con;
    }*/
}
