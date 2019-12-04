package com.example.vehiculos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static String NOMBRE = "NOMBRE";
    Button buttonModificarCoche, buttonModificarGastos, buttonInsertarGastos,
            buttonInsertarCoche, buttonBorrarCoche, buttonBorrarGastos, buttonMostrarCoches, buttonMostrarGastos;
    ListView lista;
    private int INST_COCHE = 1;
    private int INST_GASTO = 2;
    private int MOD_COCHE = 3;
    private int MOD_GASTO = 4;
    private int BOR_COCHE = 5;
    private int BOR_GASTO = 6;
    static String SERVIDOR = "http://192.168.100.19/";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMostrarCoches = findViewById(R.id.buttonMostrarCoche);
        buttonMostrarGastos = findViewById(R.id.buttonMostrarGasto);

        buttonInsertarGastos = findViewById(R.id.buttonInsertarGasto);
        buttonInsertarCoche = findViewById(R.id.buttonInsertarCoche);

        buttonModificarCoche = findViewById(R.id.buttonModificarCoche);
        buttonModificarGastos = findViewById(R.id.buttonModificarGasto);

        buttonBorrarCoche = findViewById(R.id.buttonBorrarCoche);
        buttonBorrarGastos = findViewById(R.id.buttonBorrarGasto);


        lista = findViewById(R.id.Lista);

        buttonMostrarCoches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescargarCSV descargarCSV = new DescargarCSV();
                descargarCSV.execute("web/listadoCSV.php");
            }
        });
        buttonMostrarGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        buttonInsertarCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, InsertarCoche.class);

                startActivityForResult(intento, INST_COCHE);
            }
        });
        buttonInsertarGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, InsertarGastos.class);

                startActivityForResult(intento, INST_GASTO);
            }
        });
        buttonModificarCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, ModificarCoche.class);

                startActivityForResult(intento, MOD_COCHE);
            }
        });
        buttonModificarGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, ModificarGastos.class);

                startActivityForResult(intento, MOD_GASTO);
            }
        });
        buttonBorrarCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, BorrarCoche.class);

                startActivityForResult(intento, BOR_COCHE);
            }
        });
        buttonBorrarGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, BorrarGastos.class);

                startActivityForResult(intento, BOR_GASTO);
            }
        });
    }
    private class DescargarCSV extends AsyncTask<String, Void, Void> {
        String total = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setTitle("Descargando la información de la red en csv.");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayAdapter<String> adapter;
            List<String> list = new ArrayList<String>();

            String[] lineas = total.split("\n");

            for (String lin : lineas) {
                String[] campos = lin.split(",");
                String dato = "MATRÍCULA: " + campos[0];
                dato += "MARCA: " + campos[1];
                dato += "COLOR: " + campos[2];
                list.add(dato);
            }
            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, list);
            lista.setAdapter(adapter);

            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(String... strings) {
            String script = strings[0];

            URL url = null;
            HttpURLConnection httpURLConnection = null;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                url = new URL(SERVIDOR + script);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                    String linea = "";

                    while ((linea = br.readLine()) != null) {
                        total += linea + "\n";
                    }

                    br.close();
                    inputStream.close();


                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i("CONEXION", total);

            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INST_COCHE && resultCode == RESULT_OK) {
            String nombre = data.getStringExtra(NOMBRE);


            Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
        }
        if (requestCode == INST_GASTO && resultCode == RESULT_OK) {
            String nombre = data.getStringExtra(NOMBRE);

            Toast.makeText(this, data.getStringExtra(NOMBRE), Toast.LENGTH_SHORT).show();
        }
        if (requestCode == MOD_COCHE && resultCode == RESULT_OK) {
            String nombre = data.getStringExtra(NOMBRE);

            Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
        }
        if (requestCode == MOD_GASTO && resultCode == RESULT_OK) {
            String nombre = data.getStringExtra(NOMBRE);

            Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
        }
        if (requestCode == BOR_COCHE && resultCode == RESULT_OK) {
            String nombre = data.getStringExtra(NOMBRE);

            Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
        }
        if (requestCode == BOR_GASTO && resultCode == RESULT_OK) {
            String nombre = data.getStringExtra(NOMBRE);

            Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
        }
    }
}
