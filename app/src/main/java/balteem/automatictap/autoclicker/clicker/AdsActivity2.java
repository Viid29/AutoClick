package balteem.automatictap.autoclicker.clicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import balteem.automatictap.autoclicker.clicker.info.app_settings;


public class AdsActivity2  extends AppCompatActivity {

    RelativeLayout Rel1 ;
    int flag = 0;
    TextView  StarvationStartTextView , TrialTetView1 ,TrialTetView2 , PriceTextView1 , PriceTextView2 ,StartBuy1 ,StartBuy2  ;
    public Map<String, SkuDetails> mSkuDetailsMap = new HashMap<>();
    BillingClient billingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads_layout_2);
        Rel1 = findViewById(R.id.Rel1);
        TrialTetView1 = findViewById(R.id.TrialTetView1);
        TrialTetView2 = findViewById(R.id.TrialTetView2);
        PriceTextView1 = findViewById(R.id.PriceTextView1);
        PriceTextView2 = findViewById(R.id.PriceTextView2);
        StartBuy1 = findViewById(R.id.StartBuy1);
        StartBuy2 = findViewById(R.id.StartBuy2);
        StarvationStartTextView = findViewById(R.id.StarvationStartTextView);

        mSkuDetailsMap = app_settings.getInstance().getmSkuDetailsMap();
        if (mSkuDetailsMap.isEmpty()){finish();}
        final String month_sub = app_settings.getInstance().getMonth_sub();
        final String year_sub = app_settings.getInstance().getYear_sub();
        SkuDetails details = mSkuDetailsMap.get(month_sub);
        String prise = details.getPrice();
        String trial = details.getFreeTrialPeriod();
        String subPeriod = details.getSubscriptionPeriod();
        if (trial.equals("P3D"))
        {
            TrialTetView1.setText(getString(R.string.day_3));
        }
        if (trial.equals("P1W"))
        {
            TrialTetView1.setText(getString(R.string.day_7));
        }
        if (subPeriod.equals("P1M")) {
            PriceTextView1.setText(getString(R.string.after) + " " + prise + " /" + getString(R.string.month));
        }
        if (subPeriod.equals("P1Y")) {
            PriceTextView1.setText(getString(R.string.after) + " " + prise + " /" + getString(R.string.year));
        }
        details = mSkuDetailsMap.get(year_sub);
         prise = details.getPrice();
         trial = details.getFreeTrialPeriod();
         subPeriod = details.getSubscriptionPeriod();
        if (trial.equals("P3D"))
        {
            TrialTetView2.setText(getString(R.string.day_3));
        }
        if (trial.equals("P1W"))
        {
            TrialTetView2.setText(getString(R.string.day_7));
        }
        if (subPeriod.equals("P1M")) {
            PriceTextView2.setText(getString(R.string.after) + " " + prise + " /" + getString(R.string.month));
        }
        if (subPeriod.equals("P1Y")) {
            PriceTextView2.setText(getString(R.string.after) + " " + prise + " /" + getString(R.string.year));
        }





        final AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
            @Override
            public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {
                //Подтверждение
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    Intent intent = new Intent(AdsActivity2.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };

        PurchasesUpdatedListener purchaseUpdateListener = new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
                // To be implemented in a later section.
                // метод для отключения рекламы
                String rez = billingResult.getDebugMessage();
                String k = billingResult.getDebugMessage();

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    // querySkuDetails();
                    app_settings.getInstance().setShowAdd("no");
               //     Save.JsonInfo(AdsActivity2.this);
                    Purchase purchase = purchases.get(0);
                    String l= purchase.toString();
                    String ggg = purchase.getPurchaseToken();

                    if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                        if (!purchase.isAcknowledged()) {
                            AcknowledgePurchaseParams acknowledgePurchaseParams =
                                    AcknowledgePurchaseParams.newBuilder()
                                            .setPurchaseToken(purchase.getPurchaseToken())
                                            .build();
                            billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                        }
                    }

                }


            }
        };




        billingClient = BillingClient.newBuilder(this)
                .setListener(purchaseUpdateListener)
                .enablePendingPurchases()
                .build();



        StarvationStartTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdsActivity2.this, MainActivity.class);
                startActivity(intent);
                    finish();
            }});
        StartBuy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startConection(month_sub);
            }});
        StartBuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startConection(year_sub);
            }});



    }
    public void startConection (final String skuId)
    {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() ==  BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    // querySkuDetails();
                    launchBilling(skuId , AdsActivity2.this);
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }

        });
    }
    public void launchBilling(String skuId, Activity activity) {
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(mSkuDetailsMap.get(skuId))
                .build();
        billingClient.launchBillingFlow(activity, billingFlowParams);
    }




}

