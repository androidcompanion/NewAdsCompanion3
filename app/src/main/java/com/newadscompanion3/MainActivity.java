package com.newadscompanion3;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.newadscompanion3.AdsConfig.NativeAdCompanion1;

import java.util.concurrent.Callable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadInterstitial1();

        checkAppService("TEST", BuildConfig.VERSION_NAME);

    }

    public void showAd(View view) {
//        showInterstitial1(true, new Callable<Void>() {
//            @Override
//            public Void call() throws Exception {
//                return null;
//            }
//        });

        showInter1AdonClosed(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                return null;
            }
        });
    }

    public void showBannerAd(View view) {

        showBannerAd(0,0);
    }

    public void showNativeAd(View view) {

        new NativeAdCompanion1(this).loadNativeAd((CardView) findViewById(R.id.native_ad_cardview), (CardView) findViewById(R.id.native_ad_container));

    }

    public void showNativeBannerAd(View view) {
        showNativeBannerAd(0,0);
    }
}