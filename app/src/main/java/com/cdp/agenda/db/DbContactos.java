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
            values.put("correo_electronico", correo);
            values.put("Fecha_nac", fecha_nac);

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
                cliente.setCorreo(cursorClientes.getString(3));
                cliente.setCelular(cursorClientes.getString(4));
                cliente.setTelefono(cursorClientes.getString(5));
                cliente.setFecha_naci(cursorClientes.getString(6));
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
            cliente.setCorreo(cursorClientes.getString(3));
            cliente.setCelular(cursorClientes.getString(4));
            cliente.setTelefono(cursorClientes.getString(5));
            cliente.setFecha_naci(cursorClientes.getString(6));
        }

        cursorClientes.close();

        return cliente;
    }
    /*

    public boolean editarContacto(int id, String nombre, String telefono, String correo_electronico) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CONTACTOS + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', correo_electronico = '" + correo_electronico + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarContacto(int id) {

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
    */
}
