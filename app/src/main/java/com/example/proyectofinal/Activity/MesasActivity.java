package com.example.proyectofinal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.Adapter.MesasAdapter;
import com.example.proyectofinal.Domain.MesasDomain;
import com.example.proyectofinal.Helper.MeserosDB;
import com.example.proyectofinal.R;

import java.util.ArrayList;

public class MesasActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter,adapter2;
private RecyclerView recyclerViewMesas1List,recyclerViewMesas2List;
    MeserosDB helper=new MeserosDB(this,"MeserosDB",null,1);
    String nombre="";
    String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);
        TextView txtNombre=findViewById(R.id.TxtNombreMesero);
        try {
            Intent intent=getIntent();
            Bundle datos = intent.getExtras();
            nombre = datos.getString("nombreusuario");
            password = datos.getString("password");
            txtNombre.setText(nombre/*helper.GetNomMesero()*/);
            recyclerViewMesas1();
            recyclerViewMesas2();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"mesas"+e.toString(),Toast.LENGTH_LONG).show();

        }


    }

    private void recyclerViewMesas1() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewMesas1List=findViewById(R.id.View1);
        recyclerViewMesas1List.setLayoutManager(linearLayoutManager);

        ArrayList<MesasDomain> Mesas1List=new ArrayList<>();
        Mesas1List.add(new MesasDomain("Mesa 1","comedor_dos_p"));
        Mesas1List.add(new MesasDomain("Mesa 3","comedor_rectangular_3p"));
        Mesas1List.add(new MesasDomain("Mesa 5","comedor_rectangular_4p"));
        Mesas1List.add(new MesasDomain("Mesa 7","comedor_rectangular_4p"));
        Mesas1List.add(new MesasDomain("Mesa 9","comedor_rectangular_8p"));

        adapter=new MesasAdapter(Mesas1List,nombre,password);
        recyclerViewMesas1List.setAdapter(adapter);
    }
    private void recyclerViewMesas2() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewMesas2List=findViewById(R.id.View2);
        recyclerViewMesas2List.setLayoutManager(linearLayoutManager);

        ArrayList<MesasDomain> Mesas2List=new ArrayList<>();
        Mesas2List.add(new MesasDomain("Mesa 2","comedor_dos_p"));
        Mesas2List.add(new MesasDomain("Mesa 4","comedor_rectangular_3p"));
        Mesas2List.add(new MesasDomain("Mesa 6","comedor_rectangular_4p"));
        Mesas2List.add(new MesasDomain("Mesa 8","comedor_rectangular_4p"));
        Mesas2List.add(new MesasDomain("Mesa 10","comedor_rectangular_8p"));

        adapter2=new MesasAdapter(Mesas2List,nombre,password);
        recyclerViewMesas2List.setAdapter(adapter2);
    }

}