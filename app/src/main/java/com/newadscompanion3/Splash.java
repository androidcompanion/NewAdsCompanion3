package com.newadscompanion3;

import android.content.Intent;
import android.os.Bundle;

import com.newadscompanion3.Interfaces.OnNetworkChangeListner;

import java.util.concurrent.Callable;

public class Splash extends BaseActivity implements OnNetworkChangeListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadSplashInterstitial();

        proceedWithDelay(1000, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                startActivity(new Intent(Splash.this, MainActivity.class));
                showSplashInterstitial(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        return null;
                    }
                });

                finish();
                return null;
            }
        });

    }

    @Override
    public void onInternetConnected() {

    }

    @Override
    public void onInternetDisconnected() {

    }

    @Override
    public void onAdDataDownloaded() {

    }
}