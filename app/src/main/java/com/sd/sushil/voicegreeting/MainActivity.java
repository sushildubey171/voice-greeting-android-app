package com.sd.sushil.voicegreeting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sh;
    SharedPreferences.Editor ed1;
    boolean completed;
    Button b;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sh=getSharedPreferences("mypreffile", 0);

        setContentView(R.layout.activity_main);
        completed=sh.getBoolean("complete",false);
        if(completed==true) // already entered start greetuser activity
        {
            Intent intent=new Intent(MainActivity.this,Greet.class);
            startActivity(intent);
            finish();
        }

        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText na=(EditText)findViewById(R.id.editTextName);
                EditText phon=(EditText)findViewById(R.id.editTextPhone);
                String name=na.getText().toString();
                String p=phon.getText().toString();

                ed1=sh.edit();
                ed1.putBoolean("complete", true);
                ed1.putString("NameKey", name);
                ed1.putString("phoneKey", p);

                ed1.commit();

                Intent intent=new Intent(MainActivity.this,Greet.class);
                startActivity(intent);
            }
        });

    }
}
