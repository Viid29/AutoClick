package balteem.automatictap.autoclicker.clicker.utils;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Calendar;

import balteem.automatictap.autoclicker.clicker.R;
import balteem.automatictap.autoclicker.clicker.info.app_settings;

public class IntertizalAdd {


    public void setAdd(Context ct)
    { try {
        if (app_settings.getInstance().getShowAdd().equals("yes")){
        String addDate = app_settings.getInstance().getAddDate();
        {
            long date = Long.parseLong(addDate);
            long now = Calendar.getInstance().getTimeInMillis();
            if (now - date > 150000)
            {
                addDate= String.valueOf(now);
                app_settings.getInstance().setAddDate(addDate);
                showAdd(ct , ct.getString(R.string.fullskreen_in_app));
            }
        }}

        }
    catch (Exception e)
    {}
    }


    public void showAdd (Context ct , String add) {

        InterstitialAd mInterstitialAd1 = app_settings.getInstance().getmIntertizalAdd();
       try {
           mInterstitialAd1.show();
       }
       catch (Exception e)
       {}





        final InterstitialAd mInterstitialAd = new InterstitialAd( ct);
        mInterstitialAd.setAdUnitId( add);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                app_settings.getInstance().setmIntertizalAdd(mInterstitialAd);
            }
        });
    }

    }
