package com.example.proyectofinal.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectofinal.Models.AlimentosYBebidas;
import com.example.proyectofinal.Models.Pedido;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PedidoController {
    private Context context;
    private long id;
    public  PedidoController(Context context){
        this.context=context;
    }
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private void iniciarFirebase() {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();

    }
    public ArrayList<Pedido>getPedidos(){
        iniciarFirebase();
        ArrayList<Pedido> pedidosList= new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datos: snapshot.getChildren()){
                    pedidosList.add(datos.getValue(Pedido.class));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return pedidosList;
    }
    public void insertarPedido(Pedido pedido){
        iniciarFirebase();
        long id= generarID();
        pedido.setId_Pedido(id);
        databaseReference.child("alimentosyBebidas").child(Long.toString(pedido.getId_Pedido())).setValue(pedido);

        //databaseReference.child("alimentosyBebidas").push().setValue(pedido);
        //databaseReference.child("alimentosyBebidas").removeValue();


    }
    public Long generarID(){
        int mes= Calendar.getInstance().get(Calendar.MONTH);
        int hora=Calendar.getInstance().get(Calendar.HOUR);
        int minuto=Calendar.getInstance().get(Calendar.MINUTE);
        int segundo=Calendar.getInstance().get(Calendar.SECOND);
        String id= ""+mes+""+hora+""+minuto+""+segundo;
        return Long.parseLong(id);


    }
    public void actualizarPedido(String id){
        iniciarFirebase();
        /*Pedido PedidoNuevo= new Pedido();
        databaseReference.child("alimentosyBebidas").child(id).setValue(PedidoNuevo);*/

    }
    public void eliminarPedido(String id){
        iniciarFirebase();
        databaseReference.child("alimentosyBebidas").child(id).removeValue();

    }



}
