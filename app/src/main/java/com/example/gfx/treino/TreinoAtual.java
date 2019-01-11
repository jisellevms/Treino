package com.example.gfx.treino;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TreinoAtual extends AppCompatActivity {
    private int numero;
    private TextView segundo, minuto;
    private Button parar;
    private int MINUTO;
    private MediaPlayer mediaPlayer;
    private MinhaThread tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        int layout = bundle.getInt("layout");

        int l_aux;
        switch (layout) {
            case 1:
                l_aux = R.layout.bicicleta;
                break;
            case 2:
                l_aux = R.layout.esteira;
                break;
            case 3:
                l_aux = R.layout.eliptico;
                break;

            default:
                l_aux = R.layout.activity_treino_atual;


        }
        setContentView(l_aux);
        segundo = findViewById(R.id.segundo);
        minuto = findViewById(R.id.minuto);
        parar = findViewById(R.id.parar);
        mediaPlayer = MediaPlayer.create(TreinoAtual.this, R.raw.alerta);
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        if (layout == 1) {
            MINUTO = 14;
        } else if (layout == 2) {
            MINUTO = 24;
        } else if (layout == 3) {
            MINUTO = 10;
        }
        tt = new MinhaThread();
        tt.start();
        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tt.interrupt();
                }catch (Exception e){
                    Log.e("Thread", "parar Thread");
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                finish();
                tt.interrupt();
            }
        });
    }
    @Override
    protected void onDestroy() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop(); //parar m usica
            mediaPlayer.release(); // liberar recursos utilizados pela musica
        }
        super.onDestroy();
    }


    class MinhaThread extends Thread {
        private int i;

        @Override
        public void run() {
            super.run();
                for (i = 60; i >= 0; i--) {
                    numero = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (numero != 0) {
                                if (numero < 10 && MINUTO < 10) {
                                    segundo.setText("0" + numero);
                                    minuto.setText("0" + MINUTO);
                                } else if (numero < 10) {
                                    segundo.setText("0" + numero);
                                } else if (MINUTO < 10) {
                                    minuto.setText("0" + MINUTO);

                                    if (numero >= 10) {
                                        segundo.setText("" + numero);
                                    } else {
                                        segundo.setText("0" + numero);
                                    }

                                } else {
                                    segundo.setText("" + numero);
                                    minuto.setText("" + MINUTO);
                                }
                                if (numero == 1) {
                                    MINUTO--;
                                    i = 60;

                                }

                                if (numero == 1 && MINUTO == 0) {
                                    i = 0;
                                    segundo.setText("00");
                                    minuto.setText("00");
                                    mediaPlayer.start();
                                }
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }



