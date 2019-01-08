package br.com.nobre.cronometro;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Chronometer cronometro;
    private Boolean seguirCronometro = false;
    private long tempoPausado;
    private FloatingActionButton fab;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        cronometro = findViewById(R.id.cronometro);
        btnReset = findViewById(R.id.btnResetar);
       // cronometro.setFormat("%M:%s");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seguirCronometro == false){
                    cronometro.setBase(SystemClock.elapsedRealtime() - tempoPausado);
                    cronometro.start();
                    seguirCronometro = true;
                    fab.setImageResource(R.drawable.ic_stop_white_24dp);
                }else if(seguirCronometro == true){
                    cronometro.stop();
                    tempoPausado = SystemClock.elapsedRealtime() - cronometro.getBase();
                    seguirCronometro = false;
                    fab.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                }
                Log.i("teste", "Tempo: " + cronometro.getText().toString());
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cronometro.setBase(SystemClock.elapsedRealtime());
                tempoPausado = 0;
            }
        });
    }

}
