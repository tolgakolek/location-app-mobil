package com.kortekslab.locationappmobil.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kortekslab.locationappmobil.R;

public class Splash extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView=findViewById(R.id.imageView);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.acilisanim);
        final Intent i=new Intent(this, LoginActivity.class);
        Thread zamanlama=new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        zamanlama.start();
    }
}
