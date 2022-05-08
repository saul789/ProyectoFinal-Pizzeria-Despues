package com.example.proyectofinal.Models;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Pedido {
    private long id_Pedido;
    private int numeroMesa;
    private ArrayList<AlimentosYBebidas>pedido;
    private Double sub_Total;
    private Double iva;
    private Double servicio;
    private Double total;
    public Pedido(long id_Pedido, int numeroMesa,ArrayList<AlimentosYBebidas>pedido, Double sub_Total, Double iva, Double servicio,Double total){
        this.id_Pedido=id_Pedido;
        this.numeroMesa=numeroMesa;
        this.pedido=pedido;
        this.sub_Total=sub_Total;
        this.iva=iva;
        this.servicio=servicio;
        this.total=total;
    }


    public long getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(long id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public ArrayList<AlimentosYBebidas> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<AlimentosYBebidas> pedido) {
        this.pedido = pedido;
    }

    public Double getSub_Total() {
        return sub_Total;
    }

    public void setSub_Total(Double sub_Total) {
        this.sub_Total = sub_Total;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getServicio() {
        return servicio;
    }

    public void setServicio(Double servicio) {
        this.servicio = servicio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
