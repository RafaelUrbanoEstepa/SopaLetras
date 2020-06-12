package com.example.pruebabd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Game game;
    TextView textView;
    RequestQueue queue;
    String URL = "http://192.168.1.105/php/Sopa_de_letras/apiLetras.php";

    String[] letras = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private final int ids[][] = {
            {R.id.ficha1_1, R.id.ficha1_2, R.id.ficha1_3, R.id.ficha1_4, R.id.ficha1_5, R.id.ficha1_6, R.id.ficha1_7, R.id.ficha1_8, R.id.ficha1_9, R.id.ficha1_10},
            {R.id.ficha2_1, R.id.ficha2_2, R.id.ficha2_3, R.id.ficha2_4, R.id.ficha2_5, R.id.ficha2_6, R.id.ficha2_7, R.id.ficha2_8, R.id.ficha2_9, R.id.ficha2_10},
            {R.id.ficha3_1, R.id.ficha3_2, R.id.ficha3_3, R.id.ficha3_4, R.id.ficha3_5, R.id.ficha3_6, R.id.ficha3_7, R.id.ficha3_8, R.id.ficha3_9, R.id.ficha3_10},
            {R.id.ficha4_1, R.id.ficha4_2, R.id.ficha4_3, R.id.ficha4_4, R.id.ficha4_5, R.id.ficha4_6, R.id.ficha4_7, R.id.ficha4_8, R.id.ficha4_9, R.id.ficha4_10},
            {R.id.ficha5_1, R.id.ficha5_2, R.id.ficha5_3, R.id.ficha5_4, R.id.ficha5_5, R.id.ficha5_6, R.id.ficha5_7, R.id.ficha5_8, R.id.ficha5_9, R.id.ficha5_10},
            {R.id.ficha6_1, R.id.ficha6_2, R.id.ficha6_3, R.id.ficha6_4, R.id.ficha6_5, R.id.ficha6_6, R.id.ficha6_7, R.id.ficha6_8, R.id.ficha6_9, R.id.ficha6_10},
            {R.id.ficha7_1, R.id.ficha7_2, R.id.ficha7_3, R.id.ficha7_4, R.id.ficha7_5, R.id.ficha7_6, R.id.ficha7_7, R.id.ficha7_8, R.id.ficha7_9, R.id.ficha7_10},
            {R.id.ficha8_1, R.id.ficha8_2, R.id.ficha8_3, R.id.ficha8_4, R.id.ficha8_5, R.id.ficha8_6, R.id.ficha8_7, R.id.ficha8_8, R.id.ficha8_9, R.id.ficha8_10},
            {R.id.ficha9_1, R.id.ficha9_2, R.id.ficha9_3, R.id.ficha9_4, R.id.ficha9_5, R.id.ficha9_6, R.id.ficha9_7, R.id.ficha9_8, R.id.ficha9_9, R.id.ficha9_10},
            {R.id.ficha10_1, R.id.ficha10_2, R.id.ficha10_3, R.id.ficha10_4, R.id.ficha10_5, R.id.ficha10_6, R.id.ficha10_7, R.id.ficha10_8, R.id.ficha10_9, R.id.ficha10_10},
    };

    private int rng = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();

        queue = Volley.newRequestQueue(this);

        textView = (TextView) findViewById(R.id.prueba);

        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String respuesta = response.replaceAll("\\[", "");
                respuesta = respuesta.replaceAll("\\]", "");
                respuesta = respuesta.replaceAll("\"", "");
                String[] capitales = respuesta.split(",");
                textView.setText(capitales[0]);
                for (int i=0; i<5; i++) {
                    game.insertaPalabra(capitales[i]);
                }

                for (int i=0; i<10; i++) {
                    for (int j = 0; j < 10; j++) {
                        findViewById(ids[j][i]).setBackgroundResource(getResources().getIdentifier(game.tablero[i][j], "drawable", getPackageName()));
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        });
        queue.add(request);

        String tableroLetras[][] = game.tablero;

        for (int i=0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                rng = (int) Math.floor(Math.random() * 25);
                game.actualizarTablero(i,j,letras[rng]);
                //game.actualizarTablero(i,j,"z");

            }
        }


    }

}
