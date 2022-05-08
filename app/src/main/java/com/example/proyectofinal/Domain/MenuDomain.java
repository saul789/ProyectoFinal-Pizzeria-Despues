package com.example.proyectofinal.Domain;

import java.io.Serializable;

public class MenuDomain implements Serializable {
    private String Nombre;
    private String Pic;
    private String Descripcion;
    private int calorias;
    private int numberInComanda;
    private double Precio;

    public MenuDomain(String nombre, String pic, String descripcion, int calorias, double precio) {
        Nombre = nombre;
        Pic = pic;
        Descripcion = descripcion;
        this.calorias = calorias;
        Precio = precio;
    }

    public int getNumberInComanda() {
        return numberInComanda;
    }

    public void setNumberInComanda(int numberInComanda) {
        this.numberInComanda = numberInComanda;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }
}
