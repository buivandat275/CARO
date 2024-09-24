package com.example.democaro2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Start_game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start_game);
        //enimation
        overridePendingTransition(R.anim.slide1, R.anim.slide2);

        VideoView videoView = findViewById(R.id.videoView2);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nhachay3);

        videoView.setVideoURI(video);
        videoView.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            videoView.start();
        });

        Button startButton = findViewById(R.id.button2);
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(Start_game.this, CheDo.class);
            startActivity(intent);
        });
    }
}