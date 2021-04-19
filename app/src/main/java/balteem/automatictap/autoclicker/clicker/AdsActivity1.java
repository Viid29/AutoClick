package balteem.automatictap.autoclicker.clicker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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


public class AdsActivity1  extends AppCompatActivity {

  RelativeLayout Rel1 , SubRel;
  int flag = 0;
  TextView FreeTextView , Promo_1,Promo_2 , TextFree2 , PriseTextView , StarvationStartTextView ;
  ImageView Image1 , Image2  ;
    public Map<String, SkuDetails> mSkuDetailsMap = new HashMap<>();
    BillingClient billingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads_layout);
        Rel1 = findViewById(R.id.Rel1);
        SubRel = findViewById(R.id.SubRel);
        FreeTextView = findViewById(R.id.FreeTextView);
        Promo_1 = findViewById(R.id.Promo_1);
        Promo_2 = findViewById(R.id.Promo_2);

        TextFree2 = findViewById(R.id.TextFree2);
        PriseTextView = findViewById(R.id.PriseTextView);
        StarvationStartTextView = findViewById(R.id.StarvationStartTextView);
        Image1 = findViewById(R.id.Image1);
        Image2 = findViewById(R.id.Image2);

        mSkuDetailsMap = app_settings.getInstance().getmSkuDetailsMap();
        final String month_sub = app_settings.getInstance().getMonth_sub();
        String year_sub = app_settings.getInstance().getYear_sub();


        SkuDetails details = mSkuDetailsMap.get(month_sub);
        String prise = details.getPrice();
        String trial = details.getFreeTrialPeriod();
        String subPeriod = details.getSubscriptionPeriod();

        if (trial.equals("P3D"))
        {
            TextFree2.setText(getString(R.string.day_3));
        }
        if (trial.equals("P1W"))
        {
            TextFree2.setText(getString(R.string.day_7));
        }
        if (subPeriod.equals("P1M")) {
            PriseTextView.setText(getString(R.string.after) + " " + prise + " /" + getString(R.string.month));
        }
        if (subPeriod.equals("P1Y")) {
            PriseTextView.setText(getString(R.string.after) + " " + prise + " /" + getString(R.string.year));
        }
        SubRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==1)
                {
                    Rel1.setBackground(ContextCompat.getDrawable(AdsActivity1.this, R.drawable.ic_ads_1_im));
                    Promo_1.setPaintFlags(0);
                    Promo_2.setPaintFlags(0);

                    Promo_1.setText(getString(R.string.ads_promo_1_1));
                    Promo_2.setText(getString(R.string.ads_promo_1_2));
                    Image1.setImageResource(R.drawable.ic_galka);
                    Image2.setImageResource(R.drawable.ic_galka);
                    flag = 0;
                }
            }
        });

        FreeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==0)
                {
                    Rel1.setBackground(ContextCompat.getDrawable(AdsActivity1.this, R.drawable.ic_ads_2_im));
                    Promo_1.setPaintFlags(Promo_1.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    Promo_2.setPaintFlags(Promo_2.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    Promo_1.setText(getString(R.string.ads_promo_1_1));
                    Promo_2.setText(getString(R.string.ads_promo_1_2));
                    Image1.setImageResource(R.drawable.ic_cansel);
                    Image2.setImageResource(R.drawable.ic_cansel);

                    flag = 1;
                }
            }
        });

        final AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
            @Override
            public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {
                //Подтверждение
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    Intent intent = new Intent(AdsActivity1.this, MainActivity.class);
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
                  //  Save.JsonInfo(AdsActivity1.this);
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
                if (flag == 0) {
                 startConection(month_sub);
                }
                else
                {
                    Intent intent = new Intent(AdsActivity1.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
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
                    launchBilling(skuId , AdsActivity1.this);
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

