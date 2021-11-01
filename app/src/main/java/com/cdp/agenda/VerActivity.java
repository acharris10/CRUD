package com.cdp.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cdp.agenda.db.DbContactos;
import com.cdp.agenda.entidades.Clientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText Identificacion, vNombres, Apellidos, txtCorreo, editCelular, Telefono, Fecha_nac;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Clientes cliente;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        Identificacion = findViewById(R.id.Identificacion);
        vNombres = findViewById(R.id.vNombres);
        Apellidos = findViewById(R.id.Apellidos);
        fabEditar = findViewById(R.id.fabEditar);
        txtCorreo = findViewById(R.id.txtCorreo);
        editCelular = findViewById(R.id.editCelular);
        Telefono = findViewById(R.id.Telefono);
        Fecha_nac = findViewById(R.id.Fecha_nac);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnGuarda.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbContactos dbContactos = new DbContactos(VerActivity.this);
        cliente = dbContactos.verCliente(id);

        if(cliente != null){

            Identificacion.setText(cliente.getId());
            vNombres.setText(cliente.getNombres());
            Apellidos.setText(cliente.getApellidos());
            txtCorreo.setText(cliente.getCorreo());
            editCelular.setText(cliente.getCelular());
            Telefono.setText(cliente.getTelefono());
            Fecha_nac.setText(cliente.getFecha_naci());

            btnGuarda.setVisibility(View.INVISIBLE);
            Identificacion.setInputType(InputType.TYPE_NULL);
            vNombres.setInputType(InputType.TYPE_NULL);
            Apellidos.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);
            editCelular.setInputType(InputType.TYPE_NULL);
            Telefono.setInputType(InputType.TYPE_NULL);
            Fecha_nac.setInputType(InputType.TYPE_NULL);
        }
        /*
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbContactos.eliminarContacto(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
        */

    }
     /*
    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    */


}