package balteem.automatictap.autoclicker.clicker;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.utils.IntertizalAdd;
import balteem.automatictap.autoclicker.clicker.utils.Save;


public class LanguageActivity extends AppCompatActivity {
    RelativeLayout Rel1 , Rel2 , Rel3 , Rel4 , Rel5 , Rel6 , Rel7 , Rel8 ,Rel9 ,Rel10 ,Rel11 ;



    ImageView Arrow1 , Arrow2 ,Arrow3 ,Arrow4 ,Arrow5 ,Arrow6 ,Arrow7 ,Arrow8 , Arrow9 , Arrow10 , Arrow11 ;

    ImageView BackImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.language_layout);
        Rel1 = findViewById(R.id.Rel1);
        Rel2 = findViewById(R.id.Rel2);
        Rel3 = findViewById(R.id.Rel3);
        Rel4 = findViewById(R.id.Rel4);
        Rel5 = findViewById(R.id.Rel5);
        Rel6 = findViewById(R.id.Rel6);
        Rel7 = findViewById(R.id.Rel7);
        Rel8 = findViewById(R.id.Rel8);
        Rel9 = findViewById(R.id.Rel9);
        Rel10 = findViewById(R.id.Rel10);
        Rel11 = findViewById(R.id.Rel11);



        Arrow1 = findViewById(R.id.Arrow1);
        Arrow2 = findViewById(R.id.Arrow2);
        Arrow3 = findViewById(R.id.Arrow3);
        Arrow4 = findViewById(R.id.Arrow4);
        Arrow5 = findViewById(R.id.Arrow5);
        Arrow6 = findViewById(R.id.Arrow6);
        Arrow7 = findViewById(R.id.Arrow7);
        Arrow8 = findViewById(R.id.Arrow8);
        Arrow9 = findViewById(R.id.Arrow9);
        Arrow10 = findViewById(R.id.Arrow10);
        Arrow11 = findViewById(R.id.Arrow11);


       /* MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });*/



        BackImage = findViewById(R.id.BackImage);

        BackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntertizalAdd init1 = new IntertizalAdd();
                init1.setAdd(LanguageActivity.this);
                Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }});

        Rel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(1);
            }});
        Rel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(2);
            }});
        Rel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(3);
            }});
        Rel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(4);
            }});
        Rel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(5);
            }});
        Rel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(6);
            }});
        Rel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(7);
            }});
        Rel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(8);
            }});
        Rel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(9);
            }});
        Rel10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(10);
            }});
        Rel11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedInfo(11);
            }});

    }


    public void selectedInfo (int i)
    {
            Arrow1.setImageDrawable(null);
            Arrow2.setImageDrawable(null);
            Arrow3.setImageDrawable(null);
            Arrow4.setImageDrawable(null);
            Arrow5.setImageDrawable(null);
            Arrow6.setImageDrawable(null);
            Arrow7.setImageDrawable(null);
            Arrow8.setImageDrawable(null);
            Arrow9.setImageDrawable(null);
            Arrow10.setImageDrawable(null);
            Arrow11.setImageDrawable(null);

            if (i == 1)
            {Arrow1.setImageResource(R.drawable.ic_checkmark);
                setLocale (LanguageActivity.this , "ru" );
            }
        if (i == 2)
        {Arrow2.setImageResource(R.drawable.ic_checkmark);
            setLocale (LanguageActivity.this , "en" );
        }
        if (i == 3)
        {Arrow3.setImageResource(R.drawable.ic_checkmark);
            setLocale (LanguageActivity.this , "ja" );

        }
        if (i == 4)
        {Arrow4.setImageResource(R.drawable.ic_checkmark);
            setLocale (LanguageActivity.this , "ko" );

        }
        if (i ==5)
        {Arrow5.setImageResource(R.drawable.ic_checkmark);
            setLocale (LanguageActivity.this , "es" );

        }
        if (i == 6)
        {Arrow6.setImageResource(R.drawable.ic_checkmark);
            setLocale (LanguageActivity.this , "pt" );

        }
        if (i == 7)
        {Arrow7.setImageResource(R.drawable.ic_checkmark);
            setLocale (LanguageActivity.this , "fr" );

        }
        if (i == 8)
        {Arrow8.setImageResource(R.drawable.ic_checkmark);
            setLocale (LanguageActivity.this , "de" );
        }

        if (i == 9)
        {Arrow9.setImageResource(R.drawable.ic_checkmark);
            setLocale (LanguageActivity.this , "tr" );
        }

        if (i == 10)
        {Arrow10.setImageResource(R.drawable.ic_checkmark);
            setLocale (LanguageActivity.this , "ar" );
        }

        if (i == 11)
        {Arrow11.setImageResource(R.drawable.ic_checkmark);
            setLocale (LanguageActivity.this , "vi" );
        }


    }

    public static void setLocale(Activity context , String langCode) {
        Locale locale;

        //Log.e("Lan",session.getLanguage());
       /* locale = new Locale(langCode);
        Configuration config = new Configuration(context.getResources().getConfiguration());
        Locale.setDefault(locale);
        config.setLocale(locale);

        context.getBaseContext().getResources().updateConfiguration(config,
                context.getBaseContext().getResources().getDisplayMetrics());*/
       locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(
                config,
                context.getResources().getDisplayMetrics());

        app_settings.getInstance().setLocale(langCode);
        Save.SaveJsonInfo(context);

        Intent intent = new Intent(context, LanguageActivity.class);
        context.startActivity(intent);
        context.finish();
    }
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
    /*    try {
            if (app_settings.getInstance().getLocale().equals("no")){String l ="";}
        }
        catch (Exception e)
        {
            Intent intent = new Intent(LanguageActivity.this, first_activity.class);
            startActivity(intent);
            finish();
        }*/

    }
}
