package com.example.t5_a5_banuelosdelatorresantiagodominik;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView cajaEntrada, cajaSalida;
    Spinner spnEntrada, spnSalida;
    int sEntrada = 0, sSalida = 1;
    boolean numeroObtenido = false;
    Conversor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cajaEntrada = findViewById(R.id.cajaEntrada);
        cajaSalida = findViewById(R.id.cajaSalida);

        spnEntrada = findViewById(R.id.spnEntrada);
        spnSalida = findViewById(R.id.spnSalida);

        String[] convertibles = {"Celsius","Farenheit", "Kelvin", "Rankin"};
        spnEntrada.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                convertibles
        ));
        spnSalida.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                convertibles
        ));

        spnEntrada.setOnItemSelectedListener(this);
        spnSalida.setOnItemSelectedListener(this);
        spnEntrada.setSelection(sEntrada);
        spnSalida.setSelection(sSalida);

        c = new Conversor();
    }
    public double obtenerEntrada(){
        try {
            numeroObtenido = true;
            return Double.parseDouble(cajaEntrada.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(this, "Formato de numero incorrecto", Toast.LENGTH_SHORT).show();
        }
        numeroObtenido = false;
        return 0;
    }
    public void resultar(String t){
        cajaSalida.setText(t);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner contrario = spnSalida;
        int sContra = sSalida, anterior = sEntrada;
        if(contrario == parent) {
            contrario = spnEntrada;
            sContra = sEntrada;
            anterior = sSalida;
        }
        //compara la nueva seleccion con la ultima seleccion de la caja contraria
        if(position == sContra) contrario.setSelection(anterior);

        sSalida = spnSalida.getSelectedItemPosition();
        sEntrada = spnEntrada.getSelectedItemPosition();

        c.entrada = obtenerEntrada();
        c.gradoEntrada = (byte)sEntrada;
        c.entrada = c.aCelsius();
        c.gradoSalida = (byte)sSalida;
        c.salida = c.convertir();
        //mostrar resultado limitado a 3 decimales
        if(numeroObtenido)resultar(""+((double)Math.round(c.salida*1000) / 1000));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}