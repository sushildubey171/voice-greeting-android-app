package com.sd.sushil.voicegreeting;

import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class Greet extends AppCompatActivity {
    SharedPreferences sh2;
    SharedPreferences.Editor ed2;
    Button b2;
    TextView t;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sh2=getSharedPreferences("mypreffile",0); // instantiating preference file
        setContentView(R.layout.activity_greet);

        t=(TextView)findViewById(R.id.textView);
        b2=(Button)findViewById(R.id.button2);

        // initialize the texttospeech object
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=sh2.getString("NameKey","sushil"); //retrieving name from sharedprefrence fil
                String tm;
                Date d= new Date();
                Calendar c= Calendar.getInstance();
                c.setTime(d);
                int hour=c.get(Calendar.HOUR_OF_DAY);
                if(hour>=3 && hour<12)
                tm="Good Morning";
                else if(12<=hour && hour<18)
                    tm="Good AfterNoon";//afternoon
                else if(hour>=18&& hour<22)
                    tm="Good Evening";//evening;
                else
                     tm="good Night";//night
                t.setText(tm+" "+s);

                Toast.makeText(getApplicationContext(),tm+" "+s, Toast.LENGTH_SHORT).show();
                t1.speak(tm+" "+s,TextToSpeech.QUEUE_FLUSH,null,"speak1");

            }
        });


    }
}
