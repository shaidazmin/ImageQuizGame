package com.emon.imagequizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class Main2Activity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    TextView highScore, newScore;
    GifImageView imageView;
    String score = "";
    String hsc = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        newScore = findViewById(R.id.newSc);
        highScore = findViewById(R.id.highSc);
        imageView = findViewById(R.id.finishImg);
        sharedPreferences = getApplicationContext().getSharedPreferences("score", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = new Intent();
        score = getIntent().getStringExtra("score");
        newScore.setText("New Score: " + score);


        hsc = sharedPreferences.getString("hiscore", "0");

        int a = Integer.valueOf(hsc);
        int b = Integer.valueOf(score);
        if (b >= a) {
            editor.putString("hiscore", score);
            editor.commit();
            highScore.setText("High Score: " + score);
        } else
            highScore.setText("High Score: " + hsc);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
