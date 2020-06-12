package com.example.pruebabd;

import android.util.Log;

public class Game {
    static final int NFILAS = 10;
    static final int NCOLUMNAS = 10;
    static final String VACIO = ".";
    public String tablero[][];


    public Game(){
        tablero = new String[NFILAS][NCOLUMNAS];
        for (int i = 0; i< NFILAS; i++){
            for (int j = 0; j < NCOLUMNAS; j++){
                tablero[i][j] = VACIO;
            }
        }
    }

    public void actualizarTablero(int fila, int columna, String valor){
        if (tablero[fila] [columna] == VACIO) {
            tablero[fila][columna] = valor;
        }

    }

    public boolean comprobar(int filaInicial, int colInicial, int filaFinal, int colFinal){
        if (filaInicial > NFILAS || filaInicial < 0 || colInicial > NCOLUMNAS || colInicial < 0 || filaFinal > NFILAS || filaFinal < 0 || colFinal > NCOLUMNAS || colFinal < 0){
            return false;
        }
        for (int i=filaInicial; i<filaFinal+1; i++) {
            for (int j = colInicial; j < colFinal+1; j++) {
                Log.d("coordenadas", i+" "+j+" = "+tablero[i] [j]);
                if (tablero[i] [j] != VACIO) {
                    return false;
                }
            }
        }
        return true;
    }

    public void insertaPalabra(String capital) {
        capital = capital.toLowerCase();
        int longitud = capital.length();
        int filInicial;
        int colInicial;
        int filFinal;
        int colFinal;
        int direccion;

        int saltofila = 0;
        int saltocolumna = 0;
        do {
            direccion = (int) Math.floor(Math.random() * 7);
            filInicial = (int) Math.floor(Math.random() * NFILAS);
            colInicial = (int) Math.floor(Math.random() * NCOLUMNAS);
            switch (direccion) {
                case 0:
                    filFinal = filInicial - longitud + 1;
                    colFinal = colInicial;
                    saltofila = -1;
                    saltocolumna = 0;
                    break;
                case 1:
                    filFinal = filInicial - longitud + 1;
                    colFinal = colInicial + longitud - 1;
                    saltofila = -1;
                    saltocolumna = +1;
                    break;
                case 2:
                    filFinal = filInicial;
                    colFinal = colInicial + longitud - 1;
                    saltofila = 0;
                    saltocolumna = +1;
                    break;
                case 3:
                    filFinal = filInicial + longitud - 1;
                    colFinal = colInicial + longitud - 1;
                    saltofila = +1;
                    saltocolumna = +1;
                    break;
                case 4:
                    filFinal = filInicial + longitud - 1;
                    colFinal = colInicial;
                    saltofila = +1;
                    saltocolumna = 0;
                    break;
                case 5:
                    filFinal = filInicial + longitud - 1;
                    colFinal = colInicial - longitud + 1;
                    saltofila = +1;
                    saltocolumna = -1;
                    break;
                case 6:
                    filFinal = filInicial;
                    colFinal = colInicial - longitud + 1;
                    saltofila = 0;
                    saltocolumna = -1;
                    break;
                case 7:
                    filFinal = filInicial - longitud + 1;
                    colFinal = colInicial - longitud + 1;
                    saltofila = -1;
                    saltocolumna = -1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direccion);
            }
        }while (!comprobar(filInicial, colInicial, filFinal, colFinal));

        int fila = filInicial;
        int col = colInicial;
        for (int i = 0; i < longitud; i++) {
            char letra = capital.charAt(i);
            tablero[col][fila] = Character.toString(letra);

            fila = (fila + saltofila);
            col = (col + saltocolumna);
        }
    }

}

