package com.example.proyectofinal.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.proyectofinal.Domain.MenuDomain;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class PizzasDB {
    private SharedPreferences preferences;


    public PizzasDB(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    public ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
    }
    public void clear(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        preferences.edit().remove(key).apply();
    }
    public ArrayList<MenuDomain> getListObject(String key) {
        Gson gson = new Gson();

        ArrayList<String> objStrings = getListString(key);
        ArrayList<MenuDomain> MenuList = new ArrayList<MenuDomain>();

        for (String jObjString : objStrings) {
            MenuDomain menuItem = gson.fromJson(jObjString, MenuDomain.class);
            MenuList.add(menuItem);
        }
        return MenuList;
    }



    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }


    public void putListObject(String key, ArrayList<MenuDomain> menuList) {
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for (MenuDomain menuItem : menuList) {
            objStrings.add(gson.toJson(menuItem));
        }
        putListString(key, objStrings);
    }


    private void checkForNullKey(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }

}

