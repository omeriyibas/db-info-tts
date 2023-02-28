package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView dataList;
    List<String> btn_titles;
    List<String> titles;
    List<Integer> colors;
    float pitch=1.0f;
    float speed=1.0f;

    Adapter adapter;
    static TextToSpeech textToSpeech;

    SeekBar pitch_seekbar;
    SeekBar speed_seekbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();



        pitch_seekbar=findViewById(R.id.seek_bar_pitch);
        speed_seekbar=findViewById(R.id.seek_bar_speed);

        pitch_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                pitch=(float) (seekBar.getProgress()*2)/100;
                textToSpeech.setPitch(pitch);


                Toast.makeText(MainActivity.this, "Pitch = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "Pitch = " + pitch, Toast.LENGTH_SHORT).show();



            }
        });

        speed_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                speed=(float) (seekBar.getProgress()*2)/100;
                textToSpeech.setSpeechRate(speed);


                Toast.makeText(MainActivity.this,"Speed = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();


            }
        });


        dataList = findViewById(R.id.dataList);

        btn_titles = new ArrayList<>();
        titles = new ArrayList<>();
        colors = new ArrayList<>();


        btn_titles.add("15");
        colors.add(Color.rgb(56,167,0));
        titles.add("insanoğlunun Algılayabiliceği en düşük ses seviyesidir");

        btn_titles.add("25");
        colors.add(Color.rgb(124,255,58));
        titles.add("fısıltı ile aynı ses sevisiyidir");

        btn_titles.add("35");
        colors.add(Color.rgb(203,255,102));
        titles.add("Sağanak yağış ile aynı ses seviyesidir");

        btn_titles.add("45");
        colors.add(Color.rgb(255,255,0));
        titles.add("Konuşma sesi ile aynı ses seviyesidir");

        btn_titles.add("55");
        colors.add(Color.rgb(249,185,60));
        titles.add("Konuşma sesi ile aynı ses seviyesidir");

        btn_titles.add("65");
        colors.add(Color.rgb(255,151,0));
        titles.add("Çamaşır Makinesi Konuşma sesi ile aynı ses seviyesidir");

        btn_titles.add("75");
        colors.add(Color.rgb(195,90,16));
        titles.add("Saç Kurutma makinesi ile Konuşma sesi ile aynı ses seviyesidir");

        btn_titles.add("85");
        colors.add(Color.rgb(255,28,28));
        titles.add("Otomobilin içindeki gürültü ile aynı ses seviyesidir, Dikkat bu ses seviyesine 8 saaten fazla maruz kalmak kalıcı işitme kaybına neden olabilir");

        btn_titles.add("95");
        colors.add(Color.rgb(233,0,0));
        titles.add("Çim biçme aleti veya traktör ile aynı ses seviyesidir, Dikkat bu ses seviyesine 3 saaten fazla maruz kalmak kalıcı işitme kaybına neden olabilir");

        btn_titles.add("105");
        colors.add(Color.rgb(199,38,0));
        titles.add("motorbisiklet ile aynı ses seviyesidir, Dikkat bu ses seviyesine 47 dakikadan fazla maruz kalmak kalıcı işitme kaybına neden olabilir");

        btn_titles.add("115");
        colors.add(Color.rgb(213,71,71));
        titles.add("Çok yüksek sesli bir müzik ile aynı ses seviyesidir, Dikkat bu ses seviyesine 28 saniyeden fazla maruz kalmak kalıcı işitme kaybına neden olabilir");

        btn_titles.add("120");
        colors.add(Color.rgb(175,34,0));
        titles.add("Siren sesi ile aynı ses seviyesidir, Dikkat bu ses seviyesine 9 saniyeden fazla maruz kalmak kalıcı işitme kaybına neden olabilir");


        adapter = new Adapter(this,btn_titles,titles,colors);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);



        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(new Locale("tr", "TR"));
                    textToSpeech.setSpeechRate(speed);
                    textToSpeech.setPitch(pitch);

                    if (lang == TextToSpeech.LANG_MISSING_DATA || lang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(MainActivity.this, "Language is not supported", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


}

    public void reset(View view) {

//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.toast, findViewById(R.id.card));
//
//        TextView text = layout.findViewById(R.id.text);
//        text.setText("asd");
//
//        Toast toast = new Toast(getApplicationContext());
//        toast.setGravity(Gravity.BOTTOM, 0, 40);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(layout);
//        toast.show();

        pitch=1.0f;
        speed=1.0f;
        textToSpeech.setSpeechRate(speed);
        textToSpeech.setPitch(pitch);
        speed_seekbar.setProgress(50);
        pitch_seekbar.setProgress(50);
    }
}