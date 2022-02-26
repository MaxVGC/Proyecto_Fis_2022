package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Esta clase se encarga de establecer y obtener la conexion del servlet con la
 * base de datos (PostgreSQL).
 *
 * @param url Localizacion de la base de datos, ya sea remota o local.
 * @param pass Contraseña de la base de datos.
 * @param user Usuario dueño de la base de datos.
 * @param driverDB Driver usado para establecer la conexion.
 * @author Andres Marlex
 */
public class conexionJDBC {

    Connection conexion = null;

    String user = "awpbzdko";
    String pass = "5kVCZpOG5pMo19zAmKiFMP86TeM-EAGo";
    String driverDB = "org.postgresql.Driver";
    String url = "jdbc:postgresql://otto.db.elephantsql.com:5432/awpbzdko";

    /**
     * Usa los parametros user, pass, url y driverDB para establecer la conexion
     * a la pase de datos.
     *
     * @return La conexion como tal para usar la base de datos.
     */
    public Connection conectar() {
        try {
            Class.forName(driverDB);
            conexion = DriverManager.getConnection(url, user, pass);
            return conexion;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    /**
     * Obtiene la conexion de la base de datos.
     *
     * @return Retorna la conexion si esta existe en otro caso retorna null.
     */
    public Connection getConexion() {
        return conexion;
    }

}
