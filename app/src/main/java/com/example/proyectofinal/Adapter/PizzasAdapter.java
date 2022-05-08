package com.example.proyectofinal.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectofinal.Activity.ShowDetailActivity;
import com.example.proyectofinal.Domain.MenuDomain;
import com.example.proyectofinal.R;

import java.util.ArrayList;

public class PizzasAdapter extends RecyclerView.Adapter<PizzasAdapter.ViewHolder> {
    ArrayList<MenuDomain> pizzasDomains;

    public PizzasAdapter(ArrayList<MenuDomain> pizzaDomain) {
        this.pizzasDomains = pizzaDomain;
    }

    @NonNull
    @Override
    public PizzasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pizzas,parent,false);
        return new PizzasAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzasAdapter.ViewHolder holder, int position) {
        holder.NombrePizza.setText(pizzasDomains.get(position).getNombre());
        holder.PrecioPizza.setText(String.valueOf(pizzasDomains.get(position).getPrecio()));


        int drawableReourceId=holder.itemView.getContext().getResources()
                .getIdentifier(pizzasDomains.get(position).getPic(),"mipmap",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.PicPizza);
        holder.addPbtn.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("object",pizzasDomains.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pizzasDomains.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView NombrePizza,PrecioPizza;
        ImageView PicPizza,addPbtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NombrePizza=itemView.findViewById(R.id.NombrePizza);
            PrecioPizza=itemView.findViewById(R.id.PrecioPizza);
            PicPizza=itemView.findViewById(R.id.PicPizza);
            addPbtn=itemView.findViewById(R.id.addPbtn);

        }
    }
}

