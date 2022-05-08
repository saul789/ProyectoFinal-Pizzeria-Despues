package com.example.proyectofinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectofinal.Domain.MenuDomain;
import com.example.proyectofinal.Helper.ManagementComanda;
import com.example.proyectofinal.Interface.ChangeNumberItemsListener;
import com.example.proyectofinal.R;

import java.util.ArrayList;

public class ComandaAdapter extends RecyclerView.Adapter<ComandaAdapter.ViewHolder> {
    ArrayList<MenuDomain> listFoodSelected;
    private ManagementComanda managementComanda;
    ChangeNumberItemsListener changeNumberItemsListener;

    public ComandaAdapter(ArrayList<MenuDomain> listFoodSelected, Context context,ChangeNumberItemsListener changeNumberItemsListener) {
        this.listFoodSelected = listFoodSelected;
        managementComanda=new ManagementComanda(context);
        this.changeNumberItemsListener=changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ComandaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_comanda,parent,false);
        return new ComandaAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ComandaAdapter.ViewHolder holder, int position) {
        holder.Nombre.setText(listFoodSelected.get(position).getNombre());
        holder.PrecioEachItem.setText("$"+listFoodSelected.get(position).getPrecio());
        holder.totalEachItem.setText("$"+Math.round((listFoodSelected.get(position).getNumberInComanda()*listFoodSelected.get(position).getPrecio())));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInComanda()));

        int drawableReourceId=holder.itemView.getContext().getResources()
                .getIdentifier(listFoodSelected.get(position).getPic(),"mipmap",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.Pic);
        holder.agregarItem.setOnClickListener(v -> managementComanda.plusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

        holder.quitarItem.setOnClickListener(v -> managementComanda.minusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView Nombre,PrecioEachItem,totalEachItem,num;
        ImageView Pic,agregarItem,quitarItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Nombre=itemView.findViewById(R.id.nombreTxt);
            PrecioEachItem=itemView.findViewById(R.id.precioEachItem);
            Pic=itemView.findViewById(R.id.picC);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            agregarItem=itemView.findViewById(R.id.añadirPedidoBtn);
            quitarItem=itemView.findViewById(R.id.quitarPedidoBtn);
            num=itemView.findViewById(R.id.numberItemDTxt);

        }
    }
}

