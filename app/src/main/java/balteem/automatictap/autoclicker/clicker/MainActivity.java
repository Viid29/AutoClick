package balteem.automatictap.autoclicker.clicker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.utils.DataAdapterChoosePreset;
import balteem.automatictap.autoclicker.clicker.utils.IntertizalAdd;


public class MainActivity extends AppCompatActivity {
    private Button mStart;
    private EditText mInterval;
    private RadioGroup mCheckMode;

    EditText EditTextInterval;
    TextView TextViewStart , TextViewStart2 , TextViewStart3 , TextViewHideAdd ;
    TextView  Text2 , Text3 , Text5 , Text6 , Text8 , Text9,
            Text10 , Text16 , Text12 ,Text13;
    String Interval ;
    int flag = 0;
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
    BillingClient billingClient;

    Map<String, SkuDetails> mSkuDetailsMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_layout_2);


        TextViewStart = findViewById(R.id.TextViewStart);
        TextViewStart2 = findViewById(R.id.TextViewStart2);
        TextViewStart3 = findViewById(R.id.TextViewStart3);
        EditTextInterval = findViewById(R.id.EditTextInterval);
        Text2 = findViewById(R.id.Text2);
        Text3 = findViewById(R.id.Text3);
        Text5 = findViewById(R.id.Text5);
        Text6 = findViewById(R.id.Text6);
        Text8 = findViewById(R.id.Text8);
        Text9 = findViewById(R.id.Text9);
        Text10= findViewById(R.id.Text10);
        Text16= findViewById(R.id.Text16);
        Text12= findViewById(R.id.Text12);
        Text13= findViewById(R.id.Text13);
        TextViewHideAdd= findViewById(R.id.TextViewHideAdd);


        Text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(MainActivity.this);
                app_settings.getInstance().setSettingsFlag("0");
                Intent intent = new Intent(MainActivity.this, SettingsActivity1.class);
                startActivity(intent);
            }});
        Text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(MainActivity.this);
               app_settings.getInstance().setInstructionFlag("0");
                Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                startActivity(intent);
            }});

        Text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(MainActivity.this);
                app_settings.getInstance().setSettingsFlag("1");
                Intent intent = new Intent(MainActivity.this, SettingsActivity1.class);
                startActivity(intent);
            }});
        Text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(MainActivity.this);
                app_settings.getInstance().setInstructionFlag("1");
                Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                startActivity(intent);
            }});


        Text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, SettingsAppActivity.class);
                startActivity(intent);
            }});
        Text9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(MainActivity.this);

                Intent intent = new Intent(MainActivity.this, NotWorkActivity.class);
                startActivity(intent);
            }});
        Text10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(MainActivity.this);

                Intent intent = new Intent(MainActivity.this, PresetsActivity.class);
                startActivity(intent);
            }});
        Text16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LanguageActivity.class);
                startActivity(intent);
            }});


       /* TextViewHideAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (app_settings.getInstance().getDisplay_sub() != null) {
                    if (app_settings.getInstance().getDisplay_sub().equals("1")) {
                        Intent intent = new Intent(MainActivity.this, AdsActivity1.class);
                        startActivity(intent);
                    }
                    if (app_settings.getInstance().getDisplay_sub().equals("2")) {
                        Intent intent = new Intent(MainActivity.this, AdsActivity2.class);
                        startActivity(intent);
                    }
                } else {
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
                    billingClient = BillingClient.newBuilder(MainActivity.this)
                            .setListener(purchaseUpdateListener)
                            .enablePendingPurchases()
                            .build();

                    mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
                    mFirebaseRemoteConfig.fetchAndActivate()
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<Boolean>() {
                                @Override
                                public void onComplete(@NonNull Task<Boolean> task) {
                                    if (task.isSuccessful()) {
                                        boolean updated = task.getResult();
                        /*    Log.d(TAG, "Config params updated: " + updated);
                            Toast.makeText(MainActivity.this, "Fetch and activate succeeded",
                               String welcomeMessage = mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY);     Toast.LENGTH_SHORT).show();*/
                    /*                    String display_sub = mFirebaseRemoteConfig.getString("display_sub");
                                        String year_sub = mFirebaseRemoteConfig.getString("year_sub");
                                        String month_sub = mFirebaseRemoteConfig.getString("month_sub");
                                        app_settings.getInstance().setDisplay_sub(display_sub);
                                        app_settings.getInstance().setYear_sub(year_sub);
                                        app_settings.getInstance().setMonth_sub(month_sub);
                                        startConection();
                                    } else {

                                    }

                                }
                            });
                }
            }});*/


        try {


        if (app_settings.getInstance().getTutorial_1().equals("0")) {
            LayoutInflater li = LayoutInflater.from(MainActivity.this);
            View promptsView = li.inflate(R.layout.tutorial_layout_1, null);
            AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            mDialogBuilder.setView(promptsView);

            TextView TextViewPurshape = promptsView.findViewById(R.id.TextOk);
            final AlertDialog alertDialog = mDialogBuilder.create();
            TextViewPurshape.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                    checkMainPerm();
                }
            });

            alertDialog.show();
        }
        } catch (Exception e) {}


        AdView mAdView = findViewById(R.id.adView);

       try {


        if (app_settings.getInstance().getShowAdd().equals("yes")) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }   else {
            mAdView.setVisibility(View.GONE);
        }

       }
       catch (Exception e)
       {
           mAdView.setVisibility(View.GONE);
       }


        TextViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 flag = 0;
                if (isAccessServiceEnabled(MainActivity.this , AutoService.class)) {
                    if (app_settings.getInstance().getInterval() != null) {
                        Interval = app_settings.getInstance().getInterval();
                    } else {
                        Interval = "1000";
                    }
                    checkPermission();
                }
                else {
                    checkMainPerm();
                }

            }});
        TextViewStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                if (isAccessServiceEnabled(MainActivity.this , AutoService.class)) {

                    if (app_settings.getInstance().getInterval2() != null) {
                        Interval = app_settings.getInstance().getInterval2();
                    } else {
                        Interval = "1000";
                    }
                    checkPermission();
                } else {
                    checkMainPerm();
                }
            }});


        TextViewStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 2;
                if (isAccessServiceEnabled(MainActivity.this , AutoService.class)) {
                    if (app_settings.getInstance().getInterval() != null) {
                        Interval = app_settings.getInstance().getInterval();
                    } else {
                        Interval = "1000";
                    }
                    checkPermission();
                }
                else {
                    checkMainPerm();
                }

            }});

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
           /* Purchase.PurchasesResult inapp = billingClient.queryPurchases(BillingClient.SkuType.INAPP);
            int inapp_size = 0;
            if (inapp != null && inapp.getPurchasesList() != null) inapp_size = inapp.getPurchasesList().size();*/

            int subs_size = 0;
            Purchase.PurchasesResult subs = billingClient.queryPurchases(BillingClient.SkuType.SUBS);
            if (subs != null && subs.getPurchasesList() != null )
            { subs_size = subs.getPurchasesList().size();}
            // Есть подписка

            if (subs_size>0){
                app_settings.getInstance().setShowAdd("no");
                // Save.JsonInfo(getActivity());
            }

            else
            {
                // Подписки нет
                app_settings.getInstance().setShowAdd("yes");
                //  Save.JsonInfo(getActivity());
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
                    if (app_settings.getInstance().getDisplay_sub()!=null && !mSkuDetailsMap.isEmpty()) {
                        if (app_settings.getInstance().getDisplay_sub().equals("1")) {
                            Intent intent = new Intent(MainActivity.this, AdsActivity1.class);
                            startActivity(intent);
                        }
                        if (app_settings.getInstance().getDisplay_sub().equals("2")) {
                            Intent intent = new Intent(MainActivity.this, AdsActivity2.class);
                            startActivity(intent);
                        }}
                }
            }

        });
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            } else
            {
                openWin();
            }
        }
        else
        {
            openWin();
        }
    }
  /*  public void checkPermission2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 2);
            }
        }*/

    public void openWin ()
    {


        if (flag == 1) {
            LayoutInflater li = LayoutInflater.from(MainActivity.this);
            View promptsView = li.inflate(R.layout.choose_presets_layout, null);
            android.app.AlertDialog.Builder mDialogBuilder = new android.app.AlertDialog.Builder(MainActivity.this);

            TextView NewPresetsTextView = promptsView.findViewById(R.id.NewPresetsTextView);
            RecyclerView recyclerview1 = promptsView.findViewById(R.id.recyclerview1);

            mDialogBuilder.setView(promptsView);

            final android.app.AlertDialog alertDialog = mDialogBuilder.create();

            DataAdapterChoosePreset adapter = new DataAdapterChoosePreset(MainActivity.this, app_settings.getInstance().getPresetArrayList() , alertDialog);
            recyclerview1.setLayoutManager(new LinearLayoutManager(this));
            recyclerview1.setAdapter(adapter);
            NewPresetsTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                    app_settings.getInstance().setIndChoosenPreset(-1);
                    Intent intent = new Intent(MainActivity.this, AutoService.class);
                    intent.putExtra(AutoService.ACTION, AutoService.MULTITAP);
                    intent.putExtra("interval",Integer.valueOf(Interval));
                    intent.putExtra(AutoService.MODE, AutoService.TAP);
                    startService(intent);
                    finish();
                }});
            alertDialog.show();

        }
        if (flag == 0)
        {
            Intent intent = new Intent(MainActivity.this, AutoService.class);
            intent.putExtra(AutoService.ACTION, AutoService.SHOW);
        intent.putExtra("interval",Integer.valueOf(Interval));
        intent.putExtra(AutoService.MODE, AutoService.TAP);
        startService(intent);
        finish();
        }
        if (flag == 2)
        {
            Intent intent = new Intent(MainActivity.this, AutoService.class);
            intent.putExtra(AutoService.ACTION, AutoService.SHOW);
            intent.putExtra("interval",Integer.valueOf(Interval));
            intent.putExtra(AutoService.MODE, AutoService.TAP);
            startService(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE)
        {
            int G = resultCode;
           // openWin();
        }
    }



    public void checkMainPerm ()
    {
     if (!isAccessServiceEnabled(MainActivity.this , AutoService.class))
     {
         LayoutInflater li = LayoutInflater.from(MainActivity.this);
         View promptsView = li.inflate(R.layout.alert_dialog, null);
         AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(MainActivity.this);
         mDialogBuilder.setView(promptsView);

         TextView TextViewPurshape = promptsView.findViewById(R.id.TextViewPurshape);
         final AlertDialog alertDialog = mDialogBuilder.create();
         TextViewPurshape.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 alertDialog.cancel();

                     Intent mSettingsIntent = mSettingsIntent = new Intent(Intent.ACTION_MAIN)
                             .setAction(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                     try {
                         startActivity(mSettingsIntent);
                     } catch (Exception ex) {
                         Log.w("ErrorLog", "Unable to launch app draw overlay settings " + mSettingsIntent, ex);
                     }
             }});

         alertDialog.show();
     }
    }
    public boolean isAccessServiceEnabled(Context context, Class accessibilityServiceClass)
    {
        String prefString1 = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
        String k = context.getPackageName() + "/" + accessibilityServiceClass.getName();
       if (prefString1!=null)
       {
          if (prefString1.contains(k))
          {
              return true;
          }
          else
          {

              return false;}
       }
       else
       {

        return false;}
    }

}
