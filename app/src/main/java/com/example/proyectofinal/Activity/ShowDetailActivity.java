package com.example.proyectofinal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectofinal.Domain.MenuDomain;
import com.example.proyectofinal.Helper.ManagementComanda;
import com.example.proyectofinal.R;

public class ShowDetailActivity extends AppCompatActivity {
private TextView addPedidoBtn;
private TextView nombreDTxt,precioTxt,precioTotalTxt,descripcionDTxt,caloriasTxt,numberOrderTxt;
private ImageView agregarDBtn,quitarDBtn,picFood;
private MenuDomain object;
private int numberOrder=1;
private ManagementComanda managementComanda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementComanda=new ManagementComanda(this);

        iniView();
        CargarDatos();
    }

    private void CargarDatos() {
        object=(MenuDomain)getIntent().getSerializableExtra("object");
        int mipmapResourceId=this.getResources().getIdentifier(object.getPic(),"mipmap",this.getPackageName());
        Glide.with(this).load(mipmapResourceId).into(picFood);

        nombreDTxt.setText(object.getNombre());
        precioTxt.setText("$"+object.getPrecio());
        descripcionDTxt.setText(object.getDescripcion());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        caloriasTxt.setText(object.getCalorias()+"Calorias");
        precioTotalTxt.setText("$"+Math.round(numberOrder*object.getPrecio()));

        agregarDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder= numberOrder +1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                precioTotalTxt.setText("$"+Math.round(numberOrder*object.getPrecio()));
            }
        });
        quitarDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder >1){
                    numberOrder=numberOrder-1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
                precioTotalTxt.setText("$"+Math.round(numberOrder*object.getPrecio()));
            }
        });
        addPedidoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInComanda(numberOrder);
                managementComanda.insertFood(object);
                finish();
            }
        });
    }

    private void iniView() {
        addPedidoBtn=findViewById(R.id.addPedidoBtn);
        nombreDTxt=findViewById(R.id.nombreDTxt);
        precioTxt=findViewById(R.id.precioTxt);
        precioTotalTxt=findViewById(R.id.precioTotalDTxt);
        descripcionDTxt=findViewById(R.id.descripcionDTxt);
        caloriasTxt=findViewById(R.id.CaloriasTxt);
        numberOrderTxt=findViewById(R.id.numberItemDTxt);
        agregarDBtn=findViewById(R.id.añadirPedidoBtn);
        quitarDBtn=findViewById(R.id.quitarPedidoBtn);
        picFood=findViewById(R.id.foodPic);
    }
}