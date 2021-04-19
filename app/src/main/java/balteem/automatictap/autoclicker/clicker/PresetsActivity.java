package balteem.automatictap.autoclicker.clicker;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.utils.DataAdapterPresets;
import balteem.automatictap.autoclicker.clicker.utils.IntertizalAdd;

public class PresetsActivity extends AppCompatActivity {



    ImageView BackImage;
    RecyclerView presetsRecyclerView;

    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presets_layout);

        BackImage = findViewById(R.id.BackImage);
        presetsRecyclerView = findViewById(R.id.presetsRecyclerView);

        DataAdapterPresets adapter = new DataAdapterPresets(PresetsActivity.this, app_settings.getInstance().getPresetArrayList() );
        presetsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        presetsRecyclerView.setAdapter(adapter);

        BackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(PresetsActivity.this);
                finish();
            }
        });

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