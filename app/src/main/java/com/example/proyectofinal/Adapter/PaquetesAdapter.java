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

public class PaquetesAdapter extends RecyclerView.Adapter<PaquetesAdapter.ViewHolder> {
    ArrayList<MenuDomain> PaquetesDomains;

    public PaquetesAdapter(ArrayList<MenuDomain> PaquteDomain) {
        this.PaquetesDomains = PaquteDomain;
    }

    @NonNull
    @Override
    public PaquetesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_paquetes,parent,false);
        return new PaquetesAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PaquetesAdapter.ViewHolder holder, int position) {
        holder.NombrePaquete.setText(PaquetesDomains.get(position).getNombre());
        holder.PrecioPaquete.setText(String.valueOf(PaquetesDomains.get(position).getPrecio()));

        int drawableReourceId=holder.itemView.getContext().getResources()
                .getIdentifier(PaquetesDomains.get(position).getPic(),"mipmap",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.PicPaquete);
        holder.addCbtn.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("object",PaquetesDomains.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return PaquetesDomains.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView NombrePaquete,PrecioPaquete;
        ImageView PicPaquete,addCbtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NombrePaquete=itemView.findViewById(R.id.NombreCombo);
            PrecioPaquete=itemView.findViewById(R.id.PrecioCombo);
            PicPaquete=itemView.findViewById(R.id.PicCombo);
            addCbtn=itemView.findViewById(R.id.addCbtn);

        }
    }
}

