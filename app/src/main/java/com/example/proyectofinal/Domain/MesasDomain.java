package com.example.proyectofinal.Domain;

import java.io.Serializable;

public class MesasDomain implements Serializable {
    private String Mesa;
    private String pic;

    public MesasDomain(String mesa, String pic) {
        Mesa = mesa;
        this.pic = pic;
    }
    public String getMesa() {
        return Mesa;
    }

    public void setMesa(String mesa) {
        Mesa = mesa;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


}
