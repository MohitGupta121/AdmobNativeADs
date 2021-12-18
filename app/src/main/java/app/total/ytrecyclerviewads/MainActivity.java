package app.total.ytrecyclerviewads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.total.ytrecyclerviewads.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainAdapter adapter;
    private List<Main> mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainList = new ArrayList<>();

        mainList.add(new Main("Tech360Zone", new Date()));
        mainList.add(new Main("Do SUBSCRIBE", new Date()));
        mainList.add(new Main("DO LIKE", new Date()));
        mainList.add(new Main("DO Comment", new Date()));
        mainList.add(new Main("Do Share", new Date()));
        mainList.add(new Main("Tech360Zone", new Date()));
        mainList.add(new Main("Do SUBSCRIBE", new Date()));
        mainList.add(new Main("DO LIKE", new Date()));
        mainList.add(new Main("DO Comment", new Date()));
        mainList.add(new Main("Do Share", new Date()));
        mainList.add(new Main("Tech360Zone", new Date()));
        mainList.add(new Main("Do SUBSCRIBE", new Date()));
        mainList.add(new Main("DO LIKE", new Date()));
        mainList.add(new Main("DO Comment", new Date()));
        mainList.add(new Main("Do Share", new Date()));
        mainList.add(new Main("Tech360Zone", new Date()));
        mainList.add(new Main("Do SUBSCRIBE", new Date()));
        mainList.add(new Main("DO LIKE", new Date()));
        mainList.add(new Main("DO Comment", new Date()));
        mainList.add(new Main("Do Share", new Date()));
        mainList.add(new Main("Tech360Zone", new Date()));
        mainList.add(new Main("Do SUBSCRIBE", new Date()));
        mainList.add(new Main("DO LIKE", new Date()));
        mainList.add(new Main("DO Comment", new Date()));
        mainList.add(new Main("Do Share", new Date()));
        mainList.add(new Main("Tech360Zone", new Date()));
        mainList.add(new Main("Do SUBSCRIBE", new Date()));
        mainList.add(new Main("DO LIKE", new Date()));
        mainList.add(new Main("DO Comment", new Date()));
        mainList.add(new Main("Do Share", new Date()));

        binding.rvMain.setHasFixedSize(true);
        binding.rvMain.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this, mainList);
        binding.rvMain.setAdapter(adapter);

        AdLoader.Builder builder = new AdLoader.Builder(MainActivity.this, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {
                        NativeAdView nativeAdView = (NativeAdView) MainActivity.this.getLayoutInflater().inflate(R.layout.layout_native_ad, null);
                        populateNativeADView(nativeAd, nativeAdView);
                        binding.nativeAdLayout.removeAllViews();
                        binding.nativeAdLayout.addView(nativeAdView);
                    }
                });

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Toast.makeText(MainActivity.this, loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void populateNativeADView(NativeAd nativeAd, NativeAdView adView) {
        // Set the media view.
        adView.setMediaView(adView.findViewById(R.id.ad_media));

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);
    }
}