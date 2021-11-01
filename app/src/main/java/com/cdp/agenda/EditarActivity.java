
package com.cdp.agenda;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cdp.agenda.db.DbContactos;
import com.cdp.agenda.entidades.Clientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class EditarActivity extends AppCompatActivity {

    EditText Identificacion, vNombres, Apellidos, txtCorreo, editCelular, Telefono, Fecha_nac;
    Button btnGuarda,  Fecha;
    FloatingActionButton fabEditar, fabEliminar;

    Clientes cliente;
    int id = 0;
    private int dia, mes, ano;

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
        Fecha = findViewById(R.id.Fecha);


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

        final DbContactos dbContactos = new DbContactos(EditarActivity.this);
        cliente = dbContactos.verCliente(id);

        if(cliente != null){

            Identificacion.setText(String.valueOf(cliente.getId()));
            vNombres.setText(cliente.getNombres());
            Apellidos.setText(cliente.getApellidos());
            txtCorreo.setText(cliente.getCorreo());
            editCelular.setText(cliente.getCelular());
            Telefono.setText(cliente.getTelefono());
            Fecha_nac.setText(cliente.getFecha_naci());
            fabEditar.hide();
            fabEliminar.hide();
            Fecha.setVisibility(View.VISIBLE);
        }
        Fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        Fecha_nac.setText(i+"/"+(i1+1)+"/"+i2);
                    }
                }
                        ,dia, mes, ano);
                datePickerDialog.show();
            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Identificacion.getText().toString().equals("") && !vNombres.getText().toString().equals("")&& !Apellidos.getText().toString().equals("")
                        && !txtCorreo.getText().toString().equals("") && !editCelular.getText().toString().equals("")
                        && !Telefono.getText().toString().equals("") && !Fecha_nac.getText().toString().equals("")) {


                    boolean sw = dbContactos.EditarDatos(id,Integer.parseInt(Identificacion.getText().toString()), vNombres.getText().toString(), Apellidos.getText().toString()
                            , Telefono.getText().toString(), editCelular.getText().toString(), txtCorreo.getText().toString() , Fecha_nac.getText().toString());

                    if (sw) {
                        Toast.makeText(EditarActivity.this, "ACTUALIZADO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        limpiar();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL ACTUALIZAR", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void verRegistro(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void limpiar() {
        Identificacion.setText("");
        vNombres.setText("");
        Apellidos.setText("");
        txtCorreo.setText("");
        editCelular.setText("");
        Telefono.setText("");
        Fecha_nac.setText("");
    }
}

