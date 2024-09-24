package com.example.democaro2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.media.MediaPlayer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CheDo extends AppCompatActivity {
    Button bt3, bt5, bt10;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        overridePendingTransition(R.anim.slide1, R.anim.slide2);
        setContentView(R.layout.activity_che_do);
        bt3 = findViewById(R.id.bt3);
        bt5 = findViewById(R.id.bt5);
        bt10 = findViewById(R.id.bt10);

        bt3.setOnClickListener(v -> {
            Intent intent = new Intent(CheDo.this, caro33.class);
            startActivity(intent);
        });

        bt5.setOnClickListener(v -> {
            Intent intent = new Intent(CheDo.this, caro55.class);
            startActivity(intent);
        });

        bt10.setOnClickListener(v -> {
            Intent intent = new Intent(CheDo.this, MainActivity.class);
            startActivity(intent);
        });
    }






}