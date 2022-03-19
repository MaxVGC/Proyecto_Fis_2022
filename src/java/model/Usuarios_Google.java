package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author carlo
 */
public class Usuarios_Google {

    private int id;
    private String id_google;
    private String image;

    public Usuarios_Google(int id, String id_google, String image) {
        this.id = id;
        this.id_google = id_google;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_google() {
        return id_google;
    }

    public void setId_google(String id_google) {
        this.id_google = id_google;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Usuarios_Google{" + "id=" + id + ", id_google=" + id_google + ", image=" + image + '}';
    }    

}
