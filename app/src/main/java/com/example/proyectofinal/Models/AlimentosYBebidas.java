package com.example.proyectofinal.Models;

import java.math.BigDecimal;

import javax.xml.transform.dom.DOMLocator;

public class AlimentosYBebidas {
    private int id_alimentoYBebida;
    private int cant;
    private Double precio;
    private int calorias;
    private String descripcion;
    public AlimentosYBebidas(int id_alimentoYBebida, int cant, Double precio, int calorias, String descripcion){
        this.id_alimentoYBebida=id_alimentoYBebida;
        this.cant=cant;
        this.precio=precio;
        this.calorias=calorias;
        this.descripcion=descripcion;

    }

    public int getId_alimentoYBebida() {
        return id_alimentoYBebida;
    }

    public void setId_alimentoYBebida(int id_alimentoYBebida) {
        this.id_alimentoYBebida = id_alimentoYBebida;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
