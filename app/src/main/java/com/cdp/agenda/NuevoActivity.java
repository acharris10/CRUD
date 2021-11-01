package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.agenda.db.DbContactos;

import java.util.Calendar;

public class NuevoActivity extends AppCompatActivity {

    EditText Fecha_nac, Telefono, editCelular, txtCorreo, txtApellidos, txtNombres, Identificacion;
    Button btnGuarda, Fecha;

    private int dia, mes, ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        Identificacion = findViewById(R.id.Identificacion);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.Apellidos);
        txtCorreo = findViewById(R.id.txtCorreo);
        editCelular = findViewById(R.id.editCelular);
        Telefono = findViewById(R.id.Telefono);
        Fecha_nac = findViewById(R.id.Fecha_nac);

        Fecha = findViewById(R.id.Fecha);
        btnGuarda = findViewById(R.id.btnGuarda);


        Fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(NuevoActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                if (!Identificacion.getText().toString().equals("") && !txtNombres.getText().toString().equals("") && !txtApellidos.getText().toString().equals("")
                        && !txtCorreo.getText().toString().equals("") && !editCelular.getText().toString().equals("")
                        && !Telefono.getText().toString().equals("") && !Fecha_nac.getText().toString().equals("")) {

                    DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                    long id = dbContactos.insertarDatos(Integer.parseInt(Identificacion.getText().toString()), txtNombres.getText().toString(), txtApellidos.getText().toString()
                            , Telefono.getText().toString(), editCelular.getText().toString(), txtCorreo.getText().toString(), Fecha_nac.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }

            }

        });
    }

    private void limpiar() {
        Identificacion.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        editCelular.setText("");
        Telefono.setText("");
        Fecha_nac.setText("");
    }
}