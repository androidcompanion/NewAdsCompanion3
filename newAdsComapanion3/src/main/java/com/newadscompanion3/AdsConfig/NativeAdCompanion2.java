package com.newadscompanion3.AdsConfig;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.newadscompanion3.BaseUtils.BaseClass;
import com.newadscompanion3.Interfaces.InhouseNativeListener;
import com.newadscompanion3.ModelsCompanion.AdsPrefernce;
import com.newadscomapanion3.R;

import java.util.ArrayList;
import java.util.List;

public class NativeAdCompanion2 {

    private ImageView imageView;
    private TextView textView, rating, description;
    private ProgressBar progressBar;
    private Button button;
    private ArrayList<View> viewArrayList;

    private Context context;
    AdsPrefernce adsPrefernce;
    DefaultIds defaultIds;
    AdLoader.Builder builder;

    public NativeAdCompanion2(Context context) {
        this.context = context;
    }

    public void loadNativeAd(final CardView cardView, final CardView nativeAdContainer) {
        defaultIds = new DefaultIds(context);
        adsPrefernce = new AdsPrefernce(context);
        AdLoader.Builder builder;
        if (BaseClass.isNetworkAvailable(context)) {
            if (BaseClass.isAdsAvailable) {
                if (!adsPrefernce.isMediationActive()) {
                    if (adsPrefernce.showgNative2()) {
                        MobileAds.initialize(context, adsPrefernce.gAppId());
                        builder = new AdLoader.Builder(this.context, adsPrefernce.gNativeId2());
                        builder.forUnifiedNativeAd(new OnUnifiedNativeAdLoadedListener() {
                            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                                NativeAdCompanion2.this.inflateNativeAdGoogle(unifiedNativeAd, cardView);
                            }
                        });
                        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
                        builder.withAdListener(new AdListener() {
                            public void onAdFailedToLoad(int i) {
                                if (context instanceof BaseClass){
                                    ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                                        @Override
                                        public void onAdLoaded() {
                                            nativeAdContainer.setVisibility(View.VISIBLE);
                                            cardView.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onAdShowFailed() {
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onAdLoaded() {
                                super.onAdLoaded();
                                nativeAdContainer.setVisibility(View.VISIBLE);
                                cardView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdImpression() {
                                super.onAdImpression();
                                nativeAdContainer.setVisibility(View.VISIBLE);
                                cardView.setVisibility(View.VISIBLE);
                            }
                        }).build().loadAd(new Builder().build());
                    } else if (adsPrefernce.showfbNative2()) {
                        final NativeAd nativeAd;
                        nativeAd = new NativeAd(context, adsPrefernce.fbNativeId2());
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (context instanceof BaseClass){
                                    ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                                        @Override
                                        public void onAdLoaded() {
                                            nativeAdContainer.setVisibility(View.VISIBLE);
                                            cardView.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onAdShowFailed() {
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAd != ad) {
                                    return;
                                }
                                nativeAdContainer.setVisibility(View.VISIBLE);
                                cardView.setVisibility(View.VISIBLE);
                                // Inflate Native Ad into Container
                                inflateNativeAdFacebook(nativeAd, cardView);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                nativeAdContainer.setVisibility(View.VISIBLE);
                                cardView.setVisibility(View.VISIBLE);
                            }
                        };
                        nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
                    }

                } else {
                    if (adsPrefernce.showgNative1()) {
                        if (!BaseClass.isGN1Shown) {
                            showGNative1(cardView, nativeAdContainer);
                        } else {
                            showFbNative1(cardView, nativeAdContainer);
                        }
                    } else {
                        showFbNative1(cardView, nativeAdContainer);
                    }
                }


            } else {
                if (context instanceof BaseClass){
                    ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                        @Override
                        public void onAdLoaded() {
                            nativeAdContainer.setVisibility(View.VISIBLE);
                            cardView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAdShowFailed() {
                        }
                    });
                }
            }
        }else {
            if (context instanceof BaseClass){
                ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                    @Override
                    public void onAdLoaded() {
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdShowFailed() {
                    }
                });
            }
        }

    }

    public void inflateNativeAdGoogle(UnifiedNativeAd unifiedNativeAd, CardView cardView) {
        cardView.setVisibility(View.VISIBLE);
        UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) LayoutInflater.from(this.context).inflate(R.layout.native_ad_layout_google, null);
        cardView.removeAllViews();
        cardView.addView(unifiedNativeAdView);
        TextView ad_body_view = unifiedNativeAdView.findViewById(R.id.ad_body);
        TextView ad_price_view = unifiedNativeAdView.findViewById(R.id.ad_price);
        TextView ad_store_view = unifiedNativeAdView.findViewById(R.id.ad_store);
        TextView ad_headline_view = unifiedNativeAdView.findViewById(R.id.ad_headline);
        ad_body_view.setSelected(true);
        ad_price_view.setSelected(true);
        ad_store_view.setSelected(true);
        ad_headline_view.setSelected(true);

        unifiedNativeAdView.setMediaView((MediaView) unifiedNativeAdView.findViewById(R.id.ad_media));
        unifiedNativeAdView.setHeadlineView(unifiedNativeAdView.findViewById(R.id.ad_headline));
        unifiedNativeAdView.setBodyView(unifiedNativeAdView.findViewById(R.id.ad_body));
        unifiedNativeAdView.setCallToActionView(unifiedNativeAdView.findViewById(R.id.ad_call_to_action));
        unifiedNativeAdView.setIconView(unifiedNativeAdView.findViewById(R.id.ad_app_icon));
        unifiedNativeAdView.setPriceView(unifiedNativeAdView.findViewById(R.id.ad_price));
        unifiedNativeAdView.setStarRatingView(unifiedNativeAdView.findViewById(R.id.ad_stars));
        unifiedNativeAdView.setStoreView(unifiedNativeAdView.findViewById(R.id.ad_store));
        unifiedNativeAdView.setAdvertiserView(unifiedNativeAdView.findViewById(R.id.ad_advertiser));
        ((TextView) unifiedNativeAdView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());
        unifiedNativeAdView.getMediaView().setMediaContent(unifiedNativeAd.getMediaContent());
        if (unifiedNativeAd.getBody() == null) {
            unifiedNativeAdView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            unifiedNativeAdView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) unifiedNativeAdView.getBodyView()).setText(unifiedNativeAd.getBody());
        }
        if (unifiedNativeAd.getCallToAction() == null) {
            unifiedNativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            unifiedNativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) unifiedNativeAdView.getCallToActionView()).setText(unifiedNativeAd.getCallToAction());
        }
        if (unifiedNativeAd.getIcon() == null) {
            unifiedNativeAdView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) unifiedNativeAdView.getIconView()).setImageDrawable(unifiedNativeAd.getIcon().getDrawable());
            unifiedNativeAdView.getIconView().setVisibility(View.VISIBLE);
        }
        if (unifiedNativeAd.getStarRating() == null) {
            unifiedNativeAdView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) unifiedNativeAdView.getStarRatingView()).setRating(unifiedNativeAd.getStarRating().floatValue());
            unifiedNativeAdView.getStarRatingView().setVisibility(View.VISIBLE);
        }
        if (unifiedNativeAd.getAdvertiser() == null) {
            unifiedNativeAdView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) unifiedNativeAdView.getAdvertiserView()).setText(unifiedNativeAd.getAdvertiser());
            unifiedNativeAdView.getAdvertiserView().setVisibility(View.VISIBLE);
        }
        unifiedNativeAdView.setNativeAd(unifiedNativeAd);
        unifiedNativeAd.getVideoController();
    }

    private void inflateNativeAdFacebook(NativeAd nativeAd, CardView cardView) {
        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        NativeAdLayout nativeAdLayout = new NativeAdLayout(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout adViews = (RelativeLayout) inflater.inflate(R.layout.native_ad_layout_facebook, cardView, false);
        cardView.addView(adViews);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = adViews.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        com.facebook.ads.MediaView nativeAdIcon = adViews.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adViews.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adViews.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adViews.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adViews.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adViews.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adViews.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adViews,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);
    }

    public void showGNative1(final CardView cardView, final CardView nativeAdContainer) {
        MobileAds.initialize(context, adsPrefernce.gAppId());
        builder = new AdLoader.Builder(this.context, adsPrefernce.gNativeId1());
        builder.forUnifiedNativeAd(new OnUnifiedNativeAdLoadedListener() {
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                NativeAdCompanion2.this.inflateNativeAdGoogle(unifiedNativeAd, cardView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                BaseClass.isGN1Shown = true;
                showFbNative1(cardView, nativeAdContainer);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                BaseClass.isGN1Shown = true;
                nativeAdContainer.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                nativeAdContainer.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }
        }).build().loadAd(new Builder().build());
    }

    public void showFbNative1(final CardView cardView, final CardView nativeAdContainer) {
        if (adsPrefernce.showfbNative1()) {
            if (!BaseClass.isFbN1Shown) {
                final NativeAd nativeAd;
                nativeAd = new NativeAd(context, adsPrefernce.fbNativeId1());

                NativeAdListener nativeAdListener = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        BaseClass.isFbN1Shown = true;
                        showGNative2(cardView, nativeAdContainer);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        BaseClass.isFbN1Shown = true;
                        if (nativeAd != ad) {
                            return;
                        }
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                        // Inflate Native Ad into Container
                        inflateNativeAdFacebook(nativeAd, cardView);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }
                };
                nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
            } else {
                showGNative2(cardView, nativeAdContainer);
            }
        } else {
            showGNative2(cardView, nativeAdContainer);
        }
    }

    public void showGNative2(final CardView cardView, final CardView nativeAdContainer) {

        if (adsPrefernce.showgNative2()) {
            if (!BaseClass.isGN2Shown) {
                MobileAds.initialize(context, adsPrefernce.gAppId());
                builder = new AdLoader.Builder(this.context, adsPrefernce.gNativeId2());
                builder.forUnifiedNativeAd(new OnUnifiedNativeAdLoadedListener() {
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        NativeAdCompanion2.this.inflateNativeAdGoogle(unifiedNativeAd, cardView);
                    }
                });
                builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
                builder.withAdListener(new AdListener() {
                    public void onAdFailedToLoad(int i) {
                        BaseClass.isGN2Shown = true;
                        showFbNative2(cardView, nativeAdContainer);
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        BaseClass.isGN2Shown = true;
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }
                }).build().loadAd(new Builder().build());
            } else {
                showFbNative2(cardView, nativeAdContainer);
            }
        } else {
            showFbNative2(cardView, nativeAdContainer);
        }


    }

    public void showFbNative2(final CardView cardView, final CardView nativeAdContainer) {
        if (adsPrefernce.showfbNative2()) {
            if (!BaseClass.isFbN2Shown) {
                final NativeAd nativeAd;
                nativeAd = new NativeAd(context, adsPrefernce.fbNativeId2());

                NativeAdListener nativeAdListener = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        BaseClass.isFbN2Shown = true;
                        resetNativeShownBoolean();
                        if (context instanceof BaseClass){
                            ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                                @Override
                                public void onAdLoaded() {
                                    nativeAdContainer.setVisibility(View.VISIBLE);
                                    cardView.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onAdShowFailed() {
                                }
                            });
                        }
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        BaseClass.isFbN2Shown = true;
                        if (nativeAd != ad) {
                            return;
                        }
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                        // Inflate Native Ad into Container
                        inflateNativeAdFacebook(nativeAd, cardView);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }
                };
                nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
            } else {
                resetNativeShownBoolean();
                if (context instanceof BaseClass){
                    ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                        @Override
                        public void onAdLoaded() {
                            nativeAdContainer.setVisibility(View.VISIBLE);
                            cardView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAdShowFailed() {
                        }
                    });
                }
            }
        } else {
            resetNativeShownBoolean();
            if (context instanceof BaseClass){
                ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                    @Override
                    public void onAdLoaded() {
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdShowFailed() {
                    }
                });
            }
        }
    }

    public void resetNativeShownBoolean() {
        BaseClass.isGN1Shown = false;
        BaseClass.isFbN1Shown = false;
        BaseClass.isGN2Shown = false;
        BaseClass.isFbN2Shown = false;
    }
}
