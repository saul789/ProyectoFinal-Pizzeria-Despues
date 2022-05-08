package com.example.proyectofinal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.Adapter.ComandaAdapter;
import com.example.proyectofinal.Helper.ManagementComanda;
import com.example.proyectofinal.Helper.MeserosDB;
import com.example.proyectofinal.Interface.ChangeNumberItemsListener;
import com.example.proyectofinal.R;

public class PedidoActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private  RecyclerView recyclerViewList;
    private ManagementComanda managementComanda;
    private TextView NomMesaTxt;
    private ConstraintLayout NuevoPedidoBtn;
    MeserosDB helper=new MeserosDB(this,"MeserosDB",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        managementComanda =new ManagementComanda(this);
        initList();

        NuevoPedidoBtn=findViewById(R.id.NuevoPedidioBtn);
        NuevoPedidoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (managementComanda.getListCart().isEmpty()){

                }else{
                    managementComanda.LimpiarLista();
                }
                Toast.makeText(getApplicationContext(), "Seleccione una mesa para continuar", Toast.LENGTH_SHORT).show();
                Intent intent =new  Intent(PedidoActivity.this,MesasActivity.class);
                startActivity(intent);
                finish();


            }
        });
    }

    private void initList() {
        recyclerViewList=findViewById(R.id.ViewPedidoP);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new ComandaAdapter(managementComanda.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {

            }
        });
        recyclerViewList.setAdapter(adapter);
        NomMesaTxt=findViewById(R.id.pedidoMesaTxt);
        NomMesaTxt.setText(helper.GetNumMesa());
    }
}