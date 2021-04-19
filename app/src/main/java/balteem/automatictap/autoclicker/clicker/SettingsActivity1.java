package balteem.automatictap.autoclicker.clicker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.info.settings_stop_info;
import balteem.automatictap.autoclicker.clicker.utils.IntertizalAdd;

public class SettingsActivity1 extends AppCompatActivity {
    private Button mStart;
    private EditText mInterval;
    private RadioGroup mCheckMode;
    int H , M , S ;
    int H1 , M1  , S1  ;

    EditText EditTextInterval , EditTextQuantity;
    ImageView BackImage;
    TextView TextViewSave , TimeTextView  , Text1;
    CheckBox checkbox1, checkbox2 ,checkbox3;


    String Setings ;
    int flag ;
    long millis;
    int quantity;
    String [] hours = new  String[24];
    String [] minsek = new String[60];
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout_1);
        EditTextInterval = findViewById(R.id.EditTextInterval);
        BackImage = findViewById(R.id.BackImage);
        TextViewSave = findViewById(R.id.TextViewSave);
        EditTextQuantity = findViewById(R.id.EditTextQuantity);
        TimeTextView = findViewById(R.id.TimeTextView);
        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        checkbox3 = findViewById(R.id.checkbox3);
        Text1 = findViewById(R.id.Text1);



        for (int i = 0 ; i <minsek.length ; i++)
        {
            if (i<10)
            {
                hours [i] = "0" + i;
                minsek [i] = "0" + i;
            }
            else
            {
                minsek [i] = String.valueOf(i) ;
                if (i<24)   hours [i] = String.valueOf(i) ;
            }
        }
        Setings  = app_settings.getInstance().getSettingsFlag();

        try {
        if (Setings.equals("0")) {

            Text1.setText(getString(R.string.settings_single_tap));
             flag =  app_settings.getInstance().getChoosenSettingStop1().getFlag();

                if (app_settings.getInstance().getInterval() !=null)
                {
                    EditTextInterval.setText(app_settings.getInstance().getInterval());
                }
                millis = app_settings.getInstance().getChoosenSettingStop1().getMillis();
                quantity = app_settings.getInstance().getChoosenSettingStop1().getQuantity();
            } else
            {
                Text1.setText(getString(R.string.settings_multy_tap));
                 flag =  app_settings.getInstance().getChoosenSettingStop2().getFlag();
                if (app_settings.getInstance().getInterval2() !=null)
                {
                    EditTextInterval.setText(app_settings.getInstance().getInterval2());
            }
                millis = app_settings.getInstance().getChoosenSettingStop2().getMillis();
                quantity = app_settings.getInstance().getChoosenSettingStop2().getQuantity();
        }
    }  catch (Exception e)
        {
            app_settings.getInstance().setSettingsFlag("0");
            Setings  = app_settings.getInstance().getSettingsFlag();

            if (app_settings.getInstance().getInterval() !=null)
            {
                EditTextInterval.setText(app_settings.getInstance().getInterval());
            }
            settings_stop_info st1 = new settings_stop_info();
            st1.setQuantity(10);
            st1.setFlag(1);
            st1.setMillis(300000);
            app_settings.getInstance().setChoosenSettingStop1(st1);
            millis = app_settings.getInstance().getChoosenSettingStop1().getMillis();
            quantity = app_settings.getInstance().getChoosenSettingStop1().getQuantity();
        }
        init();

        TimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(SettingsActivity1.this);
                View promptsView = li.inflate(R.layout.time_layout, null);
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(SettingsActivity1.this);
                mDialogBuilder.setView(promptsView);

                TextView TextViewPurshape = promptsView.findViewById(R.id.TextViewPurshape);
                final NumberPicker Num1 = promptsView.findViewById(R.id.Num1);
                final NumberPicker Num2 = promptsView.findViewById(R.id.Num2);
                final NumberPicker Num3 = promptsView.findViewById(R.id.Num3);


                Num1.setMinValue(0);
                Num1.setMaxValue(23);
                Num1.setDisplayedValues(hours);

                Num2.setMinValue(0);
                Num2.setMaxValue(59);
                Num2.setDisplayedValues(minsek);

                Num3.setMinValue(0);
                Num3.setMaxValue(59);
                Num3.setDisplayedValues(minsek);

                Num1.setValue(H);
                Num2.setValue(M);
                Num3.setValue(S);
                final AlertDialog alertDialog = mDialogBuilder.create();
                TextViewPurshape.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        H = Num1.getValue();
                        M = Num2.getValue();
                        S = Num3.getValue();
                        TimeTextView.setText(hours[H] + ":" + minsek[M] + ":"  + minsek[S]);
                        millis =(H * 3600000) + (M * 60000) + S*1000;

                    }});

                alertDialog.show();
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





        TextViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String inter = EditTextInterval.getText().toString();
            if (Integer.parseInt(inter)>1)
            {

                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(SettingsActivity1.this);
                if (Setings.equals("0")) {
                    app_settings.getInstance().setInterval(inter);
                    app_settings.getInstance().getChoosenSettingStop1().setMillis(millis);
                    if (!EditTextQuantity.getText().toString().equals("")){
                    app_settings.getInstance().getChoosenSettingStop1().setQuantity(Integer.parseInt(EditTextQuantity.getText().toString()));}
                    app_settings.getInstance().getChoosenSettingStop1().setFlag(flag);
                }
                else
                {  app_settings.getInstance().setInterval2(inter);
                    app_settings.getInstance().getChoosenSettingStop2().setMillis(millis);
                    if (!EditTextQuantity.getText().toString().equals("")){
                    app_settings.getInstance().getChoosenSettingStop2().setQuantity(Integer.parseInt(EditTextQuantity.getText().toString()));}
                    app_settings.getInstance().getChoosenSettingStop2().setFlag(flag);
                }
                finish();
            }
            }});

        BackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd ad = new IntertizalAdd();
                ad.setAdd(SettingsActivity1.this);
                finish();
            }
            });

        checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              clear();
              flag=1;
              checkbox1.setChecked(true);
            }
        });
        checkbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                flag=2;
                checkbox2.setChecked(true);
            }
        });
        checkbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                flag=3;
                checkbox3.setChecked(true);
            }
        });


    }




    public void init ()
    {
        clear();
        if (flag==1) checkbox1.setChecked(true);
        if (flag==2) checkbox2.setChecked(true);
        if (flag==3) checkbox3.setChecked(true);
        millisToTime();
        EditTextQuantity.setText(String.valueOf(quantity));

    }

    public void clear ()
    {
        checkbox1.setChecked(false);
        checkbox2.setChecked(false);
        checkbox3.setChecked(false);
    }

    public void millisToTime ()
    {
        S  = (int) (millis / 1000) % 60 ;
        M = (int) ((millis / (1000*60)) % 60);
        H  = (int) ((millis / (1000*60*60)) % 24);
        TimeTextView.setText(hours[H] + ":" + minsek[M] + ":"  + minsek[S]);
    }
}