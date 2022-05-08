package com.example.proyectofinal.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectofinal.Models.Usuarios;

import java.util.UUID;

public class MeserosDB  extends SQLiteOpenHelper {

    public MeserosDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table meseros(id integer primary key autoincrement, nombre text, apellidos text, usuario text, password text)";
        String query2="create table NombreMesero(id integer primary key ,Nombre text)";
        String query3="create table NumMesa(id interger primary key,NombreMesa text)";
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void abrrir(){
        this.getWritableDatabase();
    }
    public void cerrar(){
        this.close();
    }
    public void Mesa(){
        ContentValues valores=new ContentValues();
        valores.put("id",1);
        valores.put("NombreMesa","Mesa default");
        this.getWritableDatabase().insert("NumMesa",null,valores);
    }
//    public void insertarMesero(String nombre,String apellidos,String usuario,String password){
//
//        Usuarios p= new Usuarios();
//        p.setId(UUID.randomUUID().toString());
//        p.setNombre(nombre);
//        p.setApellidos(apellidos);
//        p.setUsuario(usuario);
//        p.setPassword(password);
//        database
////        ContentValues valores=new ContentValues();
////        valores.put("Nombre",nom);
////        valores.put("Apellidos",apellido);
////        valores.put("Usuario",usuario);
////        valores.put("Password",Password);
////
////        this.getWritableDatabase().insert("meseros",null,valores);
//    }

    public Cursor ConsultarUsuario(String Usuario,String Contraseña){
        Cursor mCursor=null;
        mCursor=this.getReadableDatabase().query("meseros",new String[]{"id","Nombre","Apellidos","Usuario","Password"},"Usuario like '"+Usuario+"'"
                +"and Password like '"+Contraseña+"'",null,null,null,null);
        return mCursor;
    }
    public void setNomMesero(String Usuario,String Contraseña){


//        Cursor mCursor=null;
//        mCursor=this.getReadableDatabase().query("meseros",new String[]{"id","Nombre","Apellidos","Usuario","Password"},"Usuario like '"+Usuario+"'"
//                +"and Password like '"+Contraseña+"'",null,null,null,null);
//        mCursor.moveToFirst();
//        String Nombre = mCursor.getString(1);
//        ContentValues valores=new ContentValues();
//        valores.put("id",1);
//        valores.put("Nombre",Nombre);
//        this.getWritableDatabase().insert("NombreMesero",null,valores);

    }
    public void CerrarSesion(){
        String[] parametros={"1"};
        this.getWritableDatabase().delete("NombreMesero","id=?",parametros);
    }
    public void setNumMesa(String NombreMesa ){
        ContentValues valores=new ContentValues();

        String filter = "id=?";
        String[] parametros={"1"};
        valores.put("NombreMesa",NombreMesa);
     this.getWritableDatabase().update("NumMesa",valores,filter,parametros);
    }
    public String GetNumMesa(){
        String NombreMesa;
        Cursor mCursor=null;
        String[] columns ={"NombreMesa"};
        mCursor=this.getReadableDatabase().query("NumMesa",columns,null,null,null,null,null);
        mCursor.moveToFirst();
        NombreMesa = mCursor.getString(0);
        return NombreMesa;
    }
    public String GetNomMesero(){
        String Nombre;
        Cursor mCursor=null;
        String[] columns ={"Nombre"};
        mCursor=this.getReadableDatabase().query("NombreMesero",columns,null,null,null,null,null);
        mCursor.moveToFirst();
        Nombre = mCursor.getString(0);
        return Nombre;
    }
}
