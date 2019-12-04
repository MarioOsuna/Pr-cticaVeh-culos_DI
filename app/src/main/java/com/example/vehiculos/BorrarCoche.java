package com.example.vehiculos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BorrarCoche extends AppCompatActivity {
    Button buttonEnviar;
    EditText matr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrar_coches);

        buttonEnviar = findViewById(R.id.buttonEnviarBorrarCoche);

        matr = findViewById(R.id.editTextBorrarMatrCoche);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.NOMBRE, "Vuelvo de Borrar coche");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
