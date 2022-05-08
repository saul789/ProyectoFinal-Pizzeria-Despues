package com.example.proyectofinal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.Adapter.BebidasAdapter;
import com.example.proyectofinal.Adapter.PaquetesAdapter;
import com.example.proyectofinal.Adapter.PizzasAdapter;
import com.example.proyectofinal.Domain.MenuDomain;
import com.example.proyectofinal.Domain.MesasDomain;
import com.example.proyectofinal.Helper.MeserosDB;
import com.example.proyectofinal.R;

import java.util.ArrayList;

public class MenuPrincipalActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter,adapter2,adapter3;
    private  RecyclerView recyclerViewPaquetesList,recyclerViewPizzasList,recyclerViewBebidasList;
    private TextView NumMesa,NomMesero;
    MeserosDB helper=new MeserosDB(this,"MeserosDB",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

            recyclerViewPaquetes();
            recyclerViewPizzas();
            recyclerViewBebidas();
            bottomNavigation();
            CargarDatos();


}

    private void CargarDatos() {


        try{
            NumMesa=findViewById(R.id.numMesaTxt);
           NomMesero=findViewById(R.id.NomMeseroTxt);
            Intent intent= getIntent();
            Bundle datos= intent.getExtras();
            if(datos!=null){
                String Nombre=datos.getString("nombreusuario");
                NomMesero.setText(Nombre/*helper.GetNomMesero()*/);
                NumMesa.setText(helper.GetNumMesa());
            }else{
                Toast.makeText(getApplicationContext(),"nulo",Toast.LENGTH_LONG).show();
            }


        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"cargardatos"+e.toString(),Toast.LENGTH_LONG).show();
        }


    }

    private void bottomNavigation() {

        LinearLayout  ComandaBtn=findViewById(R.id.comandaBtn);
        LinearLayout  MesasBtn=findViewById(R.id.MesasBtn);
        LinearLayout  CerrarSecionBtn=findViewById(R.id.CerrarSecionBtn);



        ComandaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipalActivity.this,ComandaActivity.class));

            }
        });
        MesasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(MenuPrincipalActivity.this,MesasActivity.class));
                    finish();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
        CerrarSecionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.CerrarSesion();
                startActivity(new Intent(MenuPrincipalActivity.this,InicioDeSesionActivity.class));
                finish();
            }
        });
    }

    private void recyclerViewBebidas() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewBebidasList=findViewById(R.id.viewBebidas);
        recyclerViewBebidasList.setLayoutManager(linearLayoutManager);

        ArrayList<MenuDomain>BebidasList=new ArrayList<>();
        BebidasList.add(new MenuDomain("Coca-Cola","coca_cola"," Bebida azucarada gaseosa, 600ml",250,25));
        BebidasList.add(new MenuDomain("Horchata","horchata"," Bebida refrescante de arroz remojados que se muelen y se mezclan con agua endulzada,Jarra de agua",150,60));
        BebidasList.add(new MenuDomain("Jamaica","jamaica","  Infusión hecha a base de flor de Jamaica, Jarra de agua",120,50));
        BebidasList.add(new MenuDomain("Limonada","limonada"," Bebida a base de limón, agua y azúcar, Jarra de agua",110,50));
        adapter=new BebidasAdapter(BebidasList);
        recyclerViewBebidasList.setAdapter(adapter);
    }

    private void recyclerViewPizzas() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPizzasList=findViewById(R.id.ViewPizzas);
        recyclerViewPizzasList.setLayoutManager(linearLayoutManager);

        ArrayList<MenuDomain>PizzasList=new ArrayList<>();
        PizzasList.add(new MenuDomain("Pizza de Pepperoni","pizza_pepperoni","Pizza con base de salsa de tomate, mozzarella y pepperoni",1070,120));
        PizzasList.add(new MenuDomain("Pizza BBQ","pizza_bbq","Pizza con base de carne BBQ, mozzarella ,pepperoni y pimiento morron",1140,140));
        PizzasList.add(new MenuDomain("Pizza Hawaiana","pizza_hawaiana","Pizza con base de salsa de tomate, mozzarella,Jamon,pepperoni y piña ",1050,130));
        PizzasList.add(new MenuDomain("Pizza Cuaresma","pizza_cuaresma","Pizza con base de salsa de tomate, mozzarella,champiñones,aceituna y atún",1000,125));
        adapter2 =new PizzasAdapter(PizzasList);
        recyclerViewPizzasList.setAdapter(adapter2);
    }

    private void recyclerViewPaquetes() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPaquetesList=findViewById(R.id.ViewPaquetes);
        recyclerViewPaquetesList.setLayoutManager(linearLayoutManager);
        ArrayList<MenuDomain>PaquetesList=new ArrayList<>();
        PaquetesList.add(new MenuDomain("Paquete #1","paquete_1","2 pizzas a elegir, Pan de ajo, Coca-Cola y Alitas ",4300,320));
        PaquetesList.add(new MenuDomain("Paquete #2","paquete_2","2 pizzas a elegir",2500,250));
        PaquetesList.add(new MenuDomain("Paquete #3","paquete_3","2 pizzas a elegir y 2 Coca-Colas",2800,270));
        PaquetesList.add(new MenuDomain("Paquete #4","paquete_4","pizza a elegir y 4 Coca-Colas",1800,220));
        adapter3=new PaquetesAdapter(PaquetesList);
        recyclerViewPaquetesList.setAdapter(adapter3);
    }
}