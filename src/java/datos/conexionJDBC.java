package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlo
 */
public class conexionJDBC {
    Connection conexion=null;
    String db="awpbzdko";
    String host="otto.db.elephantsql.com";
    String port="5432";
    String user="awpbzdko";
    String pass="5kVCZpOG5pMo19zAmKiFMP86TeM-EAGo";
    
    String driverDB="org.postgresql.Driver";
    String url="jdbc:postgresql://otto.db.elephantsql.com:5432/awpbzdko";
    
    public Connection conectar(){
        try{
            Class.forName(driverDB);
            conexion=DriverManager.getConnection(url, user, pass);
            if (!conexion.isClosed()) {
                System.out.println("Conexion establecida");
            }
            return conexion;
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex);
            return null;
        }
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
}
