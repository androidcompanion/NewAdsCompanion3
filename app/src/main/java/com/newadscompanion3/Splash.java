package com.newadscompanion3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.Callable;

public class Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadSplashInterstitial();

        proceedWithDelay(4000, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                startActivity(new Intent(Splash.this, MainActivity.class));
                showSplashInterstitial();

                finish();
                return null;
            }
        });

    }
}