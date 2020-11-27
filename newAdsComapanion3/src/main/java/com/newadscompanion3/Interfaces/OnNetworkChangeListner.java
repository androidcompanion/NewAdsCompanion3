package com.newadscompanion3.Interfaces;

import com.newadscompanion3.BaseUtils.BaseClass;

import java.util.concurrent.Callable;

public interface OnNetworkChangeListner {
    void onInternetConnected();
    void onInternetDisconnected();
    void onAdDataDownloaded();
}
