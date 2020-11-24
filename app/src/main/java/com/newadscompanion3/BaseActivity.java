package com.newadscompanion3;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.newadscompanion3.AdsConfig.DefaultIds;
import com.newadscompanion3.BaseUtils.BaseClass;

public class BaseActivity extends BaseClass {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DefaultIds defaultIds = new DefaultIds(this);
        defaultIds.setDefaults("TEST", "1", "1", "1", "0", "0", "0", "ca-app-pub-3940256099942544~3347511713", "ca-app-pub-3940256099942544/6300978111",
                "ca-app-pub-3940256099942544/1033173712", "ca-app-pub-3940256099942544/1033173712", "ca-app-pub-3940256099942544/2247696110",
                "ca-app-pub-3940256099942544/2247696110", "ca-app-pub-3940256099942544/5224354917", "YOUR_PLACEMENT_ID", "YOUR_PLACEMENT_ID",
                "YOUR_PLACEMENT_ID", "YOUR_PLACEMENT_ID", "YOUR_PLACEMENT_ID", "YOUR_PLACEMENT_ID", "YOUR_PLACEMENT_ID",
                "na",
                "b195f8dd8ded45fe847ad89ed1d016da", "24534e1901884e398f1253216226017e", "24534e1901884e398f1253216226017e", "11a17b188668469fb0412708c3d16813",
                "11a17b188668469fb0412708c3d16813",
                "920b6145fb1546cf8b5cf2ac34638bb7",
                "000000 ", "DefaultBanner", "DefaultInterstitial", "Interstitial2", "na", "DefaultRewardedVideo",
                "76582a29162d470d9b58b99204312f7f", "000000", "000000", "000000", "0", "0", "000000",

                "1", "1", "1", "1", "000000", "2", true,
                false, true, true, true, getResources().getColor(R.color.white));


    }
}
