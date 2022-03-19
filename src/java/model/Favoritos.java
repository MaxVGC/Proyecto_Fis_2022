/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author carlo
 */
public class Favoritos {
    private int id_user;
    private int id_ciudad;

    public Favoritos(int id_user, int id_ciudad) {
        this.id_user = id_user;
        this.id_ciudad = id_ciudad;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    @Override
    public String toString() {
        return "Favoritos{" + "id_user=" + id_user + ", id_ciudad=" + id_ciudad + '}';
    }
    
    
}
