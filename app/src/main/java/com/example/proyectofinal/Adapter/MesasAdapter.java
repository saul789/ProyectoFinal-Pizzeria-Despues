package com.example.proyectofinal.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectofinal.Activity.MenuPrincipalActivity;
import com.example.proyectofinal.Activity.MesasActivity;
import com.example.proyectofinal.Activity.ShowDetailActivity;
import com.example.proyectofinal.Domain.MesasDomain;
import com.example.proyectofinal.Helper.MeserosDB;
import com.example.proyectofinal.R;

import java.io.Serializable;
import java.util.ArrayList;

public class MesasAdapter extends RecyclerView.Adapter<MesasAdapter.ViewHolder> {
    ArrayList<MesasDomain> mesasDomains;
    private String nombreusuario;
    private String password;
    public MesasAdapter(ArrayList<MesasDomain> mesasDomains, String nombreusuario,String password) {
        this.mesasDomains = mesasDomains;
        this.nombreusuario=nombreusuario;
        this.password=password;
    }

    @NonNull
    @Override
    public MesasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_mesas1,parent,false);
    return new MesasAdapter.ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.MesasName.setText(mesasDomains.get(position).getMesa());

        int drawableReourceId=holder.itemView.getContext().getResources()
                .getIdentifier(mesasDomains.get(position).getPic(),"mipmap",
                holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.MesasPic);

        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), MenuPrincipalActivity.class);
            MeserosDB helper=new MeserosDB(holder.itemView.getContext(),"MeserosDB",null,1);
            helper.setNumMesa(mesasDomains.get(position).getMesa());
            Bundle bundle= new Bundle();
            bundle.putString("nombreusuario",nombreusuario);
            bundle.putString("password",password);
            intent.putExtras(bundle);


            holder.itemView.getContext().startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return mesasDomains.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView MesasName;
        ImageView MesasPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MesasName=itemView.findViewById(R.id.MesasName);
            MesasPic=itemView.findViewById(R.id.MesasPic);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
