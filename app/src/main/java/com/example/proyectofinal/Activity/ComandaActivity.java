package com.example.proyectofinal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.Adapter.ComandaAdapter;
import com.example.proyectofinal.Helper.ManagementComanda;
import com.example.proyectofinal.Helper.MeserosDB;
import com.example.proyectofinal.Interface.ChangeNumberItemsListener;
import com.example.proyectofinal.R;

public class ComandaActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private  RecyclerView recyclerViewList;
private ManagementComanda managementComanda;
private TextView subTotalTxt,ivaTxt,servicioTxt,totalTxt,emptyTxt,NomMesa;
private double iva;
private ScrollView scrollView;
    MeserosDB helper=new MeserosDB(this,"MeserosDB",null,1);


    String Nombre="";
    String password="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        
        managementComanda =new ManagementComanda(this);
        initView();
        initList();
        calcularPedido();
        bottomNavigation();
    }

    private void CargarDatos() {


        try{

            Intent intent= getIntent();
            Bundle datos= intent.getExtras();
            if(datos!=null){
                Nombre=datos.getString("nombreusuario");
                password=datos.getString("password");
            }else{
                Toast.makeText(getApplicationContext(),"nulo",Toast.LENGTH_LONG).show();
            }


        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"cargardatos"+e.toString(),Toast.LENGTH_LONG).show();
        }


    }
    private void bottomNavigation() {
        LinearLayout pizzasBtn=findViewById(R.id.pizzasCbtn);
        LinearLayout  MesasBtn=findViewById(R.id.MesasCBtn);
        LinearLayout  CerrarSecionBtn=findViewById(R.id.CerrarSecionCBtn);
        ConstraintLayout EnviarBtn=findViewById(R.id.enviarPedidoBtn);
        EnviarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle= new Bundle();
                bundle.putString("nombreusuario",Nombre);
                bundle.putString("password",password);
                Toast.makeText(getApplicationContext(), "Pedido Enviado a Cocina", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(ComandaActivity.this,MesasActivity.class);
//                startActivity(new Intent(ComandaActivity.this,PedidoActivity.class));
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        pizzasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComandaActivity.this,MenuPrincipalActivity.class));

            }
        });

        MesasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComandaActivity.this,MesasActivity.class));
                finish();
            }
        });
        CerrarSecionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //helper.CerrarSesion();
                startActivity(new Intent(ComandaActivity.this,InicioDeSesionActivity.class));
                finish();
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new ComandaAdapter(managementComanda.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calcularPedido();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementComanda.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calcularPedido() {
        double porcentajeIva=0.16;
        double servicio=20;
        iva=Math.round((managementComanda.getTotalFee()*porcentajeIva)*100.0)/100.0;
        double total =Math.round((managementComanda.getTotalFee()+iva+servicio)*100.0)/100.0;
        double subTotal=Math.round(managementComanda.getTotalFee()*100.0)/100.0;
        NomMesa.setText(helper.GetNumMesa());
        subTotalTxt.setText("$"+subTotal);
        ivaTxt.setText("$"+iva);
        servicioTxt.setText("$"+servicio);
        totalTxt.setText("$"+total);
    }

    private void initView() {
        subTotalTxt=findViewById(R.id.textSubTotalT);
        ivaTxt=findViewById(R.id.textIvaT);
        servicioTxt=findViewById(R.id.textServicioT);
        totalTxt=findViewById(R.id.textTotalT);
        recyclerViewList=findViewById(R.id.ViewComanda);
        scrollView=findViewById(R.id.ScrollViewPedido);
        emptyTxt=findViewById(R.id.textVacio);
        NomMesa=findViewById(R.id.comandaMesaTxt);

    }

}