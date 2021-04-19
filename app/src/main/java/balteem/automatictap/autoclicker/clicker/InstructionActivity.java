package balteem.automatictap.autoclicker.clicker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.utils.IntertizalAdd;

public class InstructionActivity extends AppCompatActivity {



    ImageView BackImage;
    TextView TextViewPurshape2 , TextViewPurshape  ;
    RelativeLayout R0, R5;

    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction_layout_1);
        TextViewPurshape = findViewById(R.id.TextViewPurshape);
        TextViewPurshape2 = findViewById(R.id.TextViewPurshape2);
        R0  = findViewById(R.id.R0);
        R5 = findViewById(R.id.R5);
        BackImage = findViewById(R.id.BackImage);

        if (app_settings.getInstance().getInstructionFlag().equals("0"))
        {
            R0.setVisibility(View.GONE);
            R5.setVisibility(View.GONE);
        }

        BackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(InstructionActivity.this);
                finish();
            }
        });
        TextViewPurshape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS), 0);
            }});
        TextViewPurshape2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(InstructionActivity.this);
                if (!Settings.canDrawOverlays(InstructionActivity.this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
                }
            }});
        AdView mAdView = findViewById(R.id.adView);

        try {
            if (app_settings.getInstance().getShowAdd().equals("yes")) {
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);
            }
            else {
                mAdView.setVisibility(View.GONE);
            }
        }
        catch (Exception e)
        {
            mAdView.setVisibility(View.GONE);
        }


    }
}