package com.example.proyectofinal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal.Controller.PedidoController;
import com.example.proyectofinal.Helper.MeserosDB;
import com.example.proyectofinal.Models.AlimentosYBebidas;
import com.example.proyectofinal.Models.Pedido;
import com.example.proyectofinal.Models.Usuarios;
import com.example.proyectofinal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class RegistroActivity extends AppCompatActivity {
    DatabaseReference mRootReference;
    Button btnGuardarMesero;
    EditText txtNombre,txtApellido,txtUsuario,txtPassword;
    MeserosDB helper= new MeserosDB(this,"MeserosDB",null,1);
   FirebaseDatabase firebaseDatabase;
   DatabaseReference databaseReference;
//   long maxid=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnGuardarMesero=(Button) findViewById(R.id.agregarMeseroBtn);
        txtNombre=findViewById(R.id.TxtNombreM);
        txtApellido=findViewById(R.id.TxtApellidosM);
        txtUsuario=findViewById(R.id.TxtUsuarioM);
        txtPassword=findViewById(R.id.TxtContraseñaM);

        iniciarFirebase();

        btnGuardarMesero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        insertarMesero(String.valueOf(txtNombre.getText()),
                        String.valueOf(txtApellido.getText()),
                        String.valueOf(txtUsuario.getText()),
                        String.valueOf(txtPassword.getText()));



                PedidoController pedidoController= new PedidoController(getApplicationContext());
                ArrayList<AlimentosYBebidas> nuevo= new ArrayList<>();
                AlimentosYBebidas alimentosYBebidas= new AlimentosYBebidas(1,1,100.0,1,"cosa");
                nuevo.add(alimentosYBebidas);
                AlimentosYBebidas nue= new AlimentosYBebidas(2,2,20.0,2,"perron");
                nuevo.add(nue);

                Pedido pedido= new Pedido(1,1,nuevo,10.0,10.0,10.0,10.0);
                pedidoController.insertarPedido(pedido);



//                Intent intent=new Intent(getApplicationContext(),InicioDeSesionActivity.class);
//                startActivity(intent);
            }
        });

        //Prueba para ver si puedo autoincrementar los usuarios.
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            if(dataSnapshot.exists()){
//                maxid=(dataSnapshot.getChildrenCount()); }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();

    }
//    private void RegistroUsers(){
//
//        String nombre = txtNombre.getText().toString();
//        String apellidos = txtApellido.getText().toString();
//        String usuario = txtUsuario.getText().toString();
//        String password = txtPassword.getText().toString();
//        if(nombre.equals("")||apellidos.equals("")||usuario.equals("")||password.equals("")){
//            validacion();
//        }
//    }



    public void insertarMesero(String nombre,String apellidos,String usuario,String password){

        if(nombre.equals("")||apellidos.equals("")||usuario.equals("")||password.equals("")){
            validacion();
        }
        else {

            Usuarios p = new Usuarios();
            p.setId(UUID.randomUUID().toString());
            p.setNombre(nombre);
            p.setApellidos(apellidos);
            p.setUsuario(usuario);
            p.setPassword(password);
            databaseReference.child("Usuarios").child(p.getId()).setValue(p);




            Toast.makeText(getApplicationContext(), "Mesero Guardado con Exito", Toast.LENGTH_LONG).show();
            LimpiarCajas();
//        ContentValues valores=new ContentValues();
//        valores.put("Nombre",nom);
//        valores.put("Apellidos",apellido);
//        valores.put("Usuario",usuario);
//        valores.put("Password",Password);
//
//        this.getWritableDatabase().insert("meseros",null,valores);
        }
    }
    private void validacion() {
        String nombre = txtNombre.getText().toString();
        String apellidos = txtApellido.getText().toString();
        String usuario = txtUsuario.getText().toString();
        String password = txtPassword.getText().toString();

        if (nombre.equals("")){
            txtNombre.setError("Required");
        }
        else if (apellidos.equals("")){
            txtApellido.setError("Required");
        }
        else if (usuario.equals("")){
            txtUsuario.setError("Required");
        }
        else if (password.equals("")){
            txtPassword.setError("Required");
        }
    }
    private void LimpiarCajas() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
    }


}