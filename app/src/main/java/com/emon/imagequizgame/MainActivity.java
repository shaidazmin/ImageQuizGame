package com.emon.imagequizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView1, textView2, textView3, textView4;


    ImageView imageView;
    List<Model> list;
    Data data = new Data();
    Random random;
    int turn = 1;
    int score = 0;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        textView1 = findViewById(R.id.one);
        textView2 = findViewById(R.id.two);
        textView3 = findViewById(R.id.three);
        textView4 = findViewById(R.id.four);

        list = new ArrayList<>();
        random = new Random();

        for (int i = 0; i < data.name.length; i++) {
            list.add(new Model(data.name[i], data.image[i]));
        }

        //shuffle data
        Collections.shuffle(list);

        newQuiz(turn);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(textView1);

            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(textView2);

            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(textView3);

            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(textView4);
            }
        });




//        textView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkAns(textView4);
//            }
//        });

    }


    //setNewQuestion
    private void newQuiz(int number) {
        imageView.setImageResource(list.get(number - 1).getImage());

        int currect_ans = random.nextInt(4) + 1;

        int firstButton = number - 1;
        int secoundButton;
        int thirdButton;
        int forthButton;

        switch (currect_ans) {
            case 1:
                textView1.setText(list.get(firstButton).getName());
                do {
                    secoundButton = random.nextInt(list.size());
                } while (secoundButton == firstButton);
                do {
                    thirdButton = random.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secoundButton);
                do {
                    forthButton = random.nextInt(list.size());
                }
                while (forthButton == firstButton || forthButton == secoundButton || forthButton == thirdButton);

                textView2.setText(list.get(secoundButton).getName());
                textView3.setText(list.get(thirdButton).getName());
                textView4.setText(list.get(forthButton).getName());
                break;
            case 2:

                textView2.setText(list.get(firstButton).getName());
                do {
                    secoundButton = random.nextInt(list.size());
                } while (secoundButton == firstButton);
                do {
                    thirdButton = random.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secoundButton);
                do {
                    forthButton = random.nextInt(list.size());
                }
                while (forthButton == firstButton || forthButton == secoundButton || forthButton == thirdButton);

                textView1.setText(list.get(secoundButton).getName());
                textView3.setText(list.get(thirdButton).getName());
                textView4.setText(list.get(forthButton).getName());
                break;
            case 3:

                textView3.setText(list.get(firstButton).getName());
                do {
                    secoundButton = random.nextInt(list.size());
                } while (secoundButton == firstButton);
                do {
                    thirdButton = random.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secoundButton);
                do {
                    forthButton = random.nextInt(list.size());
                }
                while (forthButton == firstButton || forthButton == secoundButton || forthButton == thirdButton);

                textView2.setText(list.get(secoundButton).getName());
                textView1.setText(list.get(thirdButton).getName());
                textView4.setText(list.get(forthButton).getName());
                break;
            case 4:

                textView4.setText(list.get(firstButton).getName());
                do {
                    secoundButton = random.nextInt(list.size());
                } while (secoundButton == firstButton);
                do {
                    thirdButton = random.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secoundButton);
                do {
                    forthButton = random.nextInt(list.size());
                }
                while (forthButton == firstButton || forthButton == secoundButton || forthButton == thirdButton);

                textView2.setText(list.get(secoundButton).getName());
                textView3.setText(list.get(thirdButton).getName());
                textView1.setText(list.get(forthButton).getName());
                break;
        }
    }

    //checkAns
    private void checkAns(TextView textView) {
        if (textView.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())) {
            score++;
            //  Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            isFinished();
        } else {

            MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("errorsound", "raw", getPackageName()));
            mediaPlayer.start();
            //Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isFinished();
                }
            }, 1000);
        }
    }

    //checkFinished
    private void isFinished() {
        if (turn < list.size()) {
            turn++;
            newQuiz(turn);
        } else {
            // Toast.makeText(MainActivity.this, "Game Finished", Toast.LENGTH_SHORT).show();
            String sc = String.valueOf(score);
            //  Toast.makeText(this, sc, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("score", sc);
            startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

          //  finish();

        }
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }


}
