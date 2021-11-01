package com.cdp.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.cdp.agenda.entidades.Clientes;

import java.util.ArrayList;

public class DbContactos extends DbHelper {

    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarDatos(int id, String nombres, String apellidos, String telefono, String celular,  String correo, String fecha_nac) {

        long ide = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("id", id);
            values.put("nombres", nombres);
            values.put("apellidos", apellidos);
            values.put("telefono", telefono);
            values.put("celular", celular);
            values.put("Fecha_nac", fecha_nac);
            values.put("correo_electronico", correo);

            ide = db.insert(TABLE_CONTACTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return ide;
    }

    public ArrayList<Clientes> mostrarClientes() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clientes> listaClientes = new ArrayList<>();
        Clientes cliente;
        Cursor cursorClientes;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " ORDER BY nombres ASC", null);

        if (cursorClientes.moveToFirst()) {
            do {
                cliente = new Clientes();
                cliente.setId(cursorClientes.getInt(0));
                cliente.setNombres(cursorClientes.getString(1));
                cliente.setApellidos(cursorClientes.getString(2));
                cliente.setTelefono(cursorClientes.getString(3));
                cliente.setCelular(cursorClientes.getString(4));
                cliente.setFecha_naci(cursorClientes.getString(5));
                cliente.setCorreo(cursorClientes.getString(6));
                listaClientes.add(cliente);
            } while (cursorClientes.moveToNext());
        }

        cursorClientes.close();

        return listaClientes;
    }



    public Clientes verCliente(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Clientes cliente = null;
        Cursor cursorClientes;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorClientes.moveToFirst()) {
            cliente = new Clientes();
            cliente.setId(cursorClientes.getInt(0));
            cliente.setNombres(cursorClientes.getString(1));
            cliente.setApellidos(cursorClientes.getString(2));
            cliente.setTelefono(cursorClientes.getString(3));
            cliente.setCelular(cursorClientes.getString(4));
            cliente.setFecha_naci(cursorClientes.getString(5));
            cliente.setCorreo(cursorClientes.getString(6));
        }

        cursorClientes.close();

        return cliente;
    }
    public boolean EditarDatos(int ida, int id, String nombres, String apellidos, String telefono, String celular,  String correo, String fecha_nac) {

        boolean sw = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CONTACTOS + " SET id = '" + id + "', nombres = '" + nombres + "', apellidos = '" + apellidos + "', telefono = '"+ telefono + "', celular = '"+ celular + "', Fecha_nac = '"+ fecha_nac + "', correo_electronico = '"+ correo + "'   WHERE id='" + ida + "' ");
            sw = true;
        } catch (Exception ex) {
            ex.toString();
            sw = false;
        } finally {
            db.close();
        }

        return sw;
    }




    public boolean eliminarCliente(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CONTACTOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

}
