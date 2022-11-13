package com.mycompany.proyectofinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion_MySql {

    private String Id_Inventario;
    private String Nombre;
    private String fecha_ini;
    private String fecha_fin;
    private String stock_ini;
    private String entradas;
    private String salidas;
    private String total;

    public Conexion_MySql() {

    }

    public void DePrueba() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/inventario", "root", "");

            String direccion = "final.txt";
            BufferedReader bf = new BufferedReader(new FileReader(direccion));

            String linea = bf.readLine();
            String[] arreglo;
            while (linea != null) {
                
                arreglo = linea.split(",");

                this.Id_Inventario = arreglo[0];
                this.Nombre = arreglo[1];
                this.fecha_ini = arreglo[2];
                this.fecha_fin = arreglo[3];
                this.stock_ini = arreglo[4];
                this.entradas = arreglo[5];
                this.salidas = arreglo[6];
                this.total = arreglo[7];
                PreparedStatement pst = cn.prepareStatement("INSERT INTO producto (id_inventario,nombre,fecha_ini,fecha_fin,stock_inicial,entradas,salidas,total) values(?,?,?,?,?,?,?,?)");
                pst.setString(1, this.Id_Inventario.trim());
                pst.setString(2, this.Nombre.trim());
                pst.setString(3, this.fecha_ini.trim());
                pst.setString(4, this.fecha_fin.trim());
                pst.setString(5, this.stock_ini.trim());
                pst.setString(6, this.entradas.trim());
                pst.setString(7, this.salidas.trim());
                pst.setString(8, this.total.trim());
                pst.executeUpdate();

                linea = bf.readLine();

            }
            
            cn.close();
            JOptionPane.showMessageDialog(null, "Operacion Sin errores");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la B-D");
        }
    }

    public void Datos_Sql(String id_inventario, String nombre, String fecha_ini, String fecha_fin, String stock_ini,
            String entradas, String salidas, String total) {

        this.Id_Inventario = id_inventario;
        this.Nombre = nombre;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
        this.stock_ini = stock_ini;
        this.entradas = entradas;
        this.salidas = salidas;
        this.total = total;
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/inventario", "root", "");
            PreparedStatement pst = cn.prepareStatement("insert into producto values(?,?,?,?,?,?,?,?)");

            pst.setString(1, "0");
            pst.setString(2, this.Nombre.trim());
            pst.setString(3, this.fecha_ini.trim());
            pst.setString(4, this.fecha_fin.trim());
            pst.setString(5, this.stock_ini.trim());
            pst.setString(6, this.entradas.trim());
            pst.setString(7, this.salidas.trim());
            pst.setString(8, this.total.trim());
            pst.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en los Datos");
        }

    }

}
