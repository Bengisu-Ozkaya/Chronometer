package com.example.counter;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView text;
    int number;
    Runnable runnable;
    Handler handler;
    Button button;

    Button button2;
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

        text = findViewById(R.id.text);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        number = 1;

        // Basit Sayaç
        /*new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                text.setText("Son: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
            text.setText("Süre bitti");
            Toast.makeText(MainActivity.this,"Süre bitti",Toast.LENGTH_SHORT).show();
            }
        }.start();*/
    }

    public void start(View view){
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                text.setText("Time: " + number);
                number++;
                handler.postDelayed(runnable,1000);
            }
        };

        handler.post(runnable);
        button.setEnabled(false);
        button2.setEnabled(true);

    }

    public void pause(View view){
        handler.removeCallbacks(runnable);
        button.setEnabled(true);
        button2.setEnabled(false);
        text.setText("Time: " + number);
        Toast.makeText(MainActivity.this,"Paused",Toast.LENGTH_SHORT).show();
    }

    public void stop(View view){
        handler.removeCallbacks(runnable);
        button.setEnabled(true);
        button2.setEnabled(true);
        number = 0;
        text.setText("Time: " + number);
    }
}