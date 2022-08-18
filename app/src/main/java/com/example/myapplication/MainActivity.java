package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    float speed=0.6f;
    TextToSpeech t1;
    EditText ed1;
    Button b1;
    Button b2;
    Locale language=Locale.US;
    Button slow;
    Button normal;
    Button fast;
    Button american;
    Button british;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.speech);
        b2=(Button)findViewById(R.id.clear);
        slow=(Button)findViewById(R.id.slow);
        normal=(Button)findViewById(R.id.normal);
        fast=(Button)findViewById(R.id.fast);
        american=(Button)findViewById(R.id.american);
        british=(Button)findViewById(R.id.british);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(language);
                    t1.setSpeechRate(speed);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = ed1.getText().toString();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed1.setText("");
            }
        });

        slow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speed=0.3f;
                t1.setSpeechRate(speed);
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speed=0.9f;
                t1.setSpeechRate(speed);
            }
        });
        fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speed=1.2f;
                t1.setSpeechRate(speed);
            }
        });
        american.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language=Locale.US;
                t1.setLanguage(language);
            }
        });
        british.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language=Locale.UK;
                t1.setLanguage(language);
            }
        });
    }

    @Override
    public void onPause() {
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(language);
                    t1.setSpeechRate(speed);
                }
            }
        });
        super.onResume();
    }
}