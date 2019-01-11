package com.example.gfx.treino;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bici, esteira, elip;
    private int numero;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bici =  findViewById(R.id.bici);
        esteira =  findViewById(R.id.esteira);
        elip =  findViewById(R.id.elip);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alerta);


        bici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TreinoAtual.class);
                intent.putExtra("layout",1);
                startActivity(intent);
            }
        });

        esteira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TreinoAtual.class);
                intent.putExtra("layout",2);
                startActivity(intent);
            }
        });

        elip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TreinoAtual.class);
                intent.putExtra("layout",3);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
}
