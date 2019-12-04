package com.example.vehiculos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BorrarGastos extends AppCompatActivity {
    Button buttonEnviar;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrar_gastos);

        buttonEnviar = findViewById(R.id.buttonEnviarBorrarGastos);

        id = findViewById(R.id.editTextBorrarID);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.NOMBRE, "Vuelvo de Borrar gastos");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

