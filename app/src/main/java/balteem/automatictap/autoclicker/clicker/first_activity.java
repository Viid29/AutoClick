package balteem.automatictap.autoclicker.clicker;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import balteem.automatictap.autoclicker.clicker.info.ads_info;
import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.info.preset_info;
import balteem.automatictap.autoclicker.clicker.info.settings_stop_info;
import balteem.automatictap.autoclicker.clicker.utils.Save;
import balteem.automatictap.autoclicker.clicker.utils.converterClass;

public class first_activity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    LoadAdError LoadAdError;
    BillingClient billingClient;
    List<ads_info> ads_infos = new ArrayList<>();
    private Map<String, SkuDetails> mSkuDetailsMap = new HashMap<>();
    int fl = 0;
    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);


        if (Save.LoadInfo(this)==null)
        {

            app_settings.getInstance().setSizeTarget(1);
            app_settings.getInstance().setSizeMenu(1);
            app_settings.getInstance().setShowAdd("yes");
            settings_stop_info st1 = new settings_stop_info();
            st1.setQuantity(10);
            st1.setFlag(1);
            st1.setMillis(300000);
            settings_stop_info st2 = new settings_stop_info();
            st2.setQuantity(10);
            st2.setFlag(1);
            st2.setMillis(300000);


            app_settings.getInstance().setChoosenSettingStop1(st1);
            app_settings.getInstance().setChoosenSettingStop2(st2);

            app_settings.getInstance().setTutorial_1("0");


            app_settings.getInstance().setLocale("no");
            Save.SaveJsonInfo(this);

            app_settings.getInstance().setPresets(new JSONArray());
            app_settings.getInstance().setPresetArrayList(new ArrayList<preset_info>());
            prog();
        }
        else
        {
            JSONObject js = Save.LoadInfo(this);
            try {
                app_settings.getInstance().setSizeTarget(Integer.parseInt(js.getString("SizeTarget")));
                app_settings.getInstance().setSizeMenu(Integer.parseInt(js.getString("SizeMenu")));

                settings_stop_info st1 = new settings_stop_info();
                st1.setQuantity(Integer.parseInt(js.getString("Quantity1")));
                st1.setFlag(Integer.parseInt(js.getString("Flag1")));
                st1.setMillis(Long.parseLong(js.getString("Millis1")));
                app_settings.getInstance().setChoosenSettingStop1(st1);

                settings_stop_info st2 = new settings_stop_info();
                st2.setQuantity(Integer.parseInt(js.getString("Quantity2")));
                st2.setFlag(Integer.parseInt(js.getString("Flag2")));
                st2.setMillis(Long.parseLong(js.getString("Millis2")));
                String locale1 = js.getString("Locale");
                app_settings.getInstance().setLocale(locale1);
                app_settings.getInstance().setChoosenSettingStop2(st2);

                app_settings.getInstance().setShowAdd("yes"); // +
                Save.loadPresets(this);

                app_settings.getInstance().setTutorial_1("1");



                if (app_settings.getInstance().getPresets().length()==0) {
                    app_settings.getInstance().setPresetArrayList(new ArrayList<preset_info>());
                }
                else {
                    converterClass.convert();
                }

                if (!locale1.equals("no"))
                {
                        Locale locale = new Locale(locale1);
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getResources().updateConfiguration(
                                config,
                                getResources().getDisplayMetrics());
                    }


             //   prog();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Save.LoadInfo(this);
        }

        requestNewInterstitial();
        requestCashInterstitial();

        final FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings;
        configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build();
        PurchasesUpdatedListener purchaseUpdateListener = new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
                // To be implemented in a later section.
                // метод для отключения рекламы

            }
        };
        billingClient = BillingClient.newBuilder(this)
                .setListener(purchaseUpdateListener)
                .enablePendingPurchases()
                .build();

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            boolean updated = task.getResult();
                        /*    Log.d(TAG, "Config params updated: " + updated);
                            Toast.makeText(MainActivity.this, "Fetch and activate succeeded",
                               String welcomeMessage = mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY);     Toast.LENGTH_SHORT).show();*/
                            String display_sub = mFirebaseRemoteConfig.getString("display_sub");
                            String year_sub = mFirebaseRemoteConfig.getString("year_sub");
                            String month_sub = mFirebaseRemoteConfig.getString("month_sub");
                            app_settings.getInstance().setDisplay_sub(display_sub);
                            app_settings.getInstance().setYear_sub(year_sub);
                            app_settings.getInstance().setMonth_sub(month_sub);
                            startConection();
                        } else {
                            Toast.makeText(first_activity.this, "Fetch failed",
                                    Toast.LENGTH_SHORT).show();
                            whenLoadAdd ();
                        }

                    }
                });


        mInterstitialAd.setAdListener(new AdListener() {
                                          @Override
                                          public void onAdClosed() {
                                              Intent intent = new Intent(first_activity.this, MainActivity.class);
                                              startActivity(intent);
                                              finish();
                                          }
                                      }
        );
    }





    public void startConection ()
    {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() ==  BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    // querySkuDetails();
                    checkPurchase();
                    querySkuDetails();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }

        });
    }
    public void checkPurchase() {
        if (billingClient.isReady()) {
           Purchase.PurchasesResult inapp = billingClient.queryPurchases(BillingClient.SkuType.INAPP);
          /*  int inapp_size = 0;
            if (inapp != null && inapp.getPurchasesList() != null) inapp_size = inapp.getPurchasesList().size();*/

            int subs_size = 0;
            Purchase.PurchasesResult subs = billingClient.queryPurchases(BillingClient.SkuType.SUBS);
            if (subs != null && subs.getPurchasesList() != null )
            { subs_size = subs.getPurchasesList().size();}
            // Есть подписка

            if (subs_size>0){
                app_settings.getInstance().setShowAdd("no");
                Intent intent = new Intent(first_activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            else
            {
                // Подписки нет
                app_settings.getInstance().setShowAdd("yes");

                whenLoadAdd ();
            }

        }
    }
    private void querySkuDetails() {
        SkuDetailsParams.Builder skuDetailsParamsBuilder = SkuDetailsParams.newBuilder();
        List<String> skuList = new ArrayList<>();
        String m = app_settings.getInstance().getMonth_sub();
        String y = app_settings.getInstance().getYear_sub();
        skuList.add(m);
        skuList.add(y);
        skuDetailsParamsBuilder.setSkusList(skuList).setType(BillingClient.SkuType.SUBS);
        billingClient.querySkuDetailsAsync(skuDetailsParamsBuilder.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    for (SkuDetails skuDetails : list) {
                        mSkuDetailsMap.put(skuDetails.getSku(), skuDetails);

                    }

                    app_settings.getInstance().setmSkuDetailsMap(mSkuDetailsMap);
                }
            }

        });
    }


    public void whenLoadAdd ()
    {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else
                {
                    j++;
                    if (j<5)
                    {
                    whenLoadAdd();}
                    else
                    {   Intent intent = new Intent(first_activity.this, MainActivity.class);
                        startActivity(intent);
                        finish();}
                }
            } }, 1000);

    }




    public void prog ()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (fl == 4){
                    app_settings.getInstance().setAddDate(String.valueOf(Calendar.getInstance().getTimeInMillis()));

                if (app_settings.getInstance().getShowAdd().equals("yes"))
                {  if (mInterstitialAd.isLoaded())
                {
                mInterstitialAd.show();
               } else
                {
                    Intent intent = new Intent(first_activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }   else
            {
                Intent intent = new Intent(first_activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
                }
            else {
                fl = fl+1;
                prog();
            }
            }
        }, 1000);
    }

    public void requestNewInterstitial()
    {
        mInterstitialAd = new InterstitialAd( this);
        mInterstitialAd.setAdUnitId( getString(R.string.fullskreen_after_splash));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void requestCashInterstitial() {
        final InterstitialAd mInterstitialAd1 = new InterstitialAd(this);
        mInterstitialAd1.setAdUnitId(getString(R.string.fullskreen_in_app));
        mInterstitialAd1.loadAd(new AdRequest.Builder().build());
        mInterstitialAd1.setAdListener(
                new AdListener() {
                    @Override
                    public void onAdLoaded() {
                       app_settings.getInstance().setmIntertizalAdd(mInterstitialAd1);// Code to be e
                    }
                }
        );

    }
}
