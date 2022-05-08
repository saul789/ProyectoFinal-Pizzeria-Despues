package com.example.proyectofinal.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal.Adapter.MesasAdapter;
import com.example.proyectofinal.Helper.MeserosDB;
import com.example.proyectofinal.Models.Usuarios;
import com.example.proyectofinal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class InicioDeSesionActivity extends AppCompatActivity {
MeserosDB helper=new MeserosDB(this,"MeserosDB",null,1);
    DatabaseReference mRootReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    EditText txtUsuario, txtContraseña;
    Button LoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_de_sesion);
//        setContentView(R.layout.activity_registro);


       inicioNavigation();

    }
    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Usuarios");

    }
    private void IniciarSesion(String Usuario,String Contraseña){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean existe=false;
                String usuario="";
                String usuariopassdatos="";
                for(DataSnapshot datos: snapshot.getChildren()){
                    usuario= datos.child("usuario").getValue(String.class);
                    usuariopassdatos=datos.child("password").getValue(String.class);

                    if(usuario.equals(Usuario)){
                         if(usuariopassdatos.equals(Contraseña)) {
                             existe=true;
                             break;
                         }
                    }}
                    if(existe){
                        helper.Mesa();
                        helper.setNomMesero(Usuario, Contraseña);
                        Intent showActivity = new Intent(getApplicationContext(), MesasActivity.class);
                        Intent menuActivity= new Intent(getApplicationContext(), MesasAdapter.class);
                        Bundle bundle= new Bundle();
                        bundle.putString("nombreusuario",Usuario);
                        bundle.putString("password",Contraseña);
                        showActivity.putExtras(bundle);
                        menuActivity.putExtras(bundle);
                        startActivity(showActivity);

                    }else{
                        Toast.makeText(getApplicationContext(),"Acceso Inválido. Por favor inténtelo otra Vez ",Toast.LENGTH_LONG).show();
                    }
                    txtUsuario.setText("");
                    txtContraseña.setText("");
                    txtContraseña.findFocus();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void inicioNavigation() {
        LoginBtn= findViewById(R.id.btnLogin);


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUsuario = findViewById(R.id.TxtUsuario);
                txtContraseña=findViewById(R.id.TxtContraseña);

                try {
                    iniciarFirebase();
                    IniciarSesion(txtUsuario.getText().toString(),txtContraseña.getText().toString());

                    /*Cursor cursor = helper.ConsultarUsuario(txtUsuario.getText().toString(), txtContraseña.getText().toString());

                    if (cursor.getCount()>0){
                        helper.Mesa();
                       helper.setNomMesero(txtUsuario.getText().toString(), txtContraseña.getText().toString());
                        Intent showActivity=new Intent(getApplicationContext(),MesasActivity.class);
                        startActivity(showActivity);


                    }else {
                        Toast.makeText(getApplicationContext(),"Acceso Inválido. Por favor inténtelo otra Vez ",Toast.LENGTH_LONG).show();
                    }
                    txtUsuario.setText("");
                    txtContraseña.setText("");
                    txtContraseña.findFocus();
                } catch (SQLException e) {
                    e.printStackTrace();
                }*/}
            catch(Exception e){
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();

                    }


            }
        });
        TextView registroBtn=findViewById(R.id.registroBtn);
        registroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioDeSesionActivity.this,RegistroActivity.class));
            }
        });
    }

}