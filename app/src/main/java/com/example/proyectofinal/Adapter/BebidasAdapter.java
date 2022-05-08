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

public class BebidasAdapter extends RecyclerView.Adapter<BebidasAdapter.ViewHolder> {
    ArrayList<MenuDomain> bebidasDomains;

    public BebidasAdapter(ArrayList<MenuDomain> BebidasDomain) {
        this.bebidasDomains = BebidasDomain;
    }

    @NonNull
    @Override
    public BebidasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bebidas,parent,false);
        return new BebidasAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BebidasAdapter.ViewHolder holder, int position) {
        holder.NombreBebida.setText(bebidasDomains.get(position).getNombre());
        holder.PrecioBebida.setText(String.valueOf(bebidasDomains.get(position).getPrecio()));


        int drawableReourceId=holder.itemView.getContext().getResources()
                .getIdentifier(bebidasDomains.get(position).getPic(),"mipmap",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.PicBebida);

        holder.addBbtn.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("object",bebidasDomains.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bebidasDomains.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView NombreBebida,PrecioBebida;
        ImageView PicBebida,addBbtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreBebida=itemView.findViewById(R.id.NombreBebida);
            PrecioBebida=itemView.findViewById(R.id.PrecioBebida);
            PicBebida=itemView.findViewById(R.id.PicBebida);
            addBbtn=itemView.findViewById(R.id.addBbtn);

        }
    }
}
