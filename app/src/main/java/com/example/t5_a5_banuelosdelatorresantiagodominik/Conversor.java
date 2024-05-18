package com.example.t5_a5_banuelosdelatorresantiagodominik;

import android.util.Log;

public class Conversor {
    byte gradoEntrada, gradoSalida;
    double entrada, salida;

    public double aCelsius(){
        Log.i("CONVERSOR", "Convirtiendo " + entrada + " de " + gradoEntrada);
        switch (gradoEntrada){
            case 0:return entrada;
            case 1:return (entrada-32.0)*(5.0/9.0);
            case 2:return entrada+273.15;
            case 3:return (entrada-491.67)*(5.0/9.0);
            default:return 0;
        }
    }
    public double convertir(){
        Log.i("CONVERSOR", "Convirtiendo " + entrada + " a " + gradoSalida);
        switch (gradoSalida){
            case 0:return entrada;
            case 1:return entrada*9.0/5.0 + 32;
            case 2:return entrada-273.15;
            case 3:return entrada * 9.0/5.0 + 491.67;
            default:return 0;
        }
    }
}
