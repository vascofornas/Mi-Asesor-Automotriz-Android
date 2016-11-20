package com.webjuarez.miasesorautomotriz;

/**
 * Created by modestovascofornas on 11/11/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.pushbots.push.Pushbots;

/**
 * Created by modestovascofornas on 10/23/15.
 */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Pushbots.sharedInstance().init(this);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, Inicio.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}