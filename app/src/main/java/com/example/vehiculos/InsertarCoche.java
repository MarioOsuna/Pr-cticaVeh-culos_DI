package com.example.vehiculos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InsertarCoche extends AppCompatActivity {
    Button buttonEnviar;
    EditText matr, marca, color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertar_coche);

        buttonEnviar = findViewById(R.id.buttonEnviarInsCoche);

        matr = findViewById(R.id.editTextMatr_coches);
        marca = findViewById(R.id.editTextMarca);
        color = findViewById(R.id.editTextColor);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.NOMBRE, "Vuelvo de Insertar coche");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
