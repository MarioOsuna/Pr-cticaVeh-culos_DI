package com.example.vehiculos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InsertarGastos extends AppCompatActivity {
    Button buttonEnviar;
    EditText matr, concepto, valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertar_gastos);

        buttonEnviar = findViewById(R.id.buttonEnviarInsertarGastos);

        matr = findViewById(R.id.editTextMatr_coches);
        concepto = findViewById(R.id.editTextConcepto);
        valor = findViewById(R.id.editTextValor);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.NOMBRE, "Vuelvo de Insertar gastos");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
