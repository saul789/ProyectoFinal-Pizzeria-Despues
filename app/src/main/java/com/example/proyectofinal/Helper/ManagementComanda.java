package com.example.proyectofinal.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.proyectofinal.Domain.MenuDomain;
import com.example.proyectofinal.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementComanda {
    private Context context;
    private PizzasDB pizzasDB;

    public ManagementComanda(Context context) {
        this.context = context;
        this.pizzasDB = new PizzasDB(context);

    }

    public void insertFood(MenuDomain item) {
        ArrayList<MenuDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getNombre().equals(item.getNombre())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listFood.get(n).setNumberInComanda(item.getNumberInComanda());
        } else {
            listFood.add(item);
        }

        pizzasDB.putListObject("ComandaList", listFood);
        Toast.makeText(context, "Elemento Agregado", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<MenuDomain> getListCart() {
        return pizzasDB.getListObject("ComandaList");
    }

    public void minusNumberFood(ArrayList<MenuDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listfood.get(position).getNumberInComanda() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberInComanda(listfood.get(position).getNumberInComanda() - 1);
        }
        pizzasDB.putListObject("ComandaList", listfood);

        changeNumberItemsListener.changed();
    }

    public void plusNumberFood(ArrayList<MenuDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listfood.get(position).setNumberInComanda(listfood.get(position).getNumberInComanda() + 1);
        pizzasDB.putListObject("ComandaList", listfood);
        changeNumberItemsListener.changed();
    }
    public  void LimpiarLista(){
        pizzasDB.clear("ComandaList");
    }
    public Double getTotalFee() {
        ArrayList<MenuDomain> listfood2 = getListCart();
        double Precio = 0;
        for (int i = 0; i < listfood2.size(); i++) {
            Precio = Precio + (listfood2.get(i).getPrecio() * listfood2.get(i).getNumberInComanda());
        }
        return Precio;
    }
}

