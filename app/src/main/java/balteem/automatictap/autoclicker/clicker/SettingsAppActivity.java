package balteem.automatictap.autoclicker.clicker;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.utils.Save;

public class SettingsAppActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private Button mStart;
    private EditText mInterval;
    private RadioGroup mCheckMode;

    SeekBar Seekbar1 , Seekbar2 ;
    ImageView Image1, Add , Play , Stop , Close;

    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_app_layout);
        Seekbar1 = findViewById(R.id.SeekBar1);
        Seekbar2 = findViewById(R.id.SeekBar2);
        Image1 = findViewById(R.id.Image1);
        Add = findViewById(R.id.add);
        Play = findViewById(R.id.play);
        Stop = findViewById(R.id.stop);
        Close = findViewById(R.id.close);
        init();

        Seekbar1.setOnSeekBarChangeListener(this);
        Seekbar2.setOnSeekBarChangeListener(this);

    }

    public void init ()
    {
        int progress = app_settings.getInstance().getSizeMenu();
        changeSize(Add,progress);
        changeSize(Play,progress);
        changeSize(Stop,progress);
        changeSize(Close,progress);
        Seekbar2.setProgress(progress);
        Seekbar1.setProgress(app_settings.getInstance().getSizeTarget());
        changeSizeTarget(Image1 , app_settings.getInstance().getSizeTarget());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar==Seekbar1)
        {
            changeSizeTarget(Image1 , progress);
            app_settings.getInstance().setSizeTarget(progress);
        }
        else
            {
            changeSize(Add,progress);
            changeSize(Play,progress);
            changeSize(Stop,progress);
            changeSize(Close,progress);
            app_settings.getInstance().setSizeMenu(progress);
        }
        Save.SaveJsonInfo(this);
    }

    public void  changeSizeTarget (ImageView im , int r)
    {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) im.getLayoutParams();

        if (r==0)
        {
            params.width=100;
            params.height=100;
        }
        if (r==1)
        {
            params.width=125;
            params.height=125;
        }
        if (r==2)
        {
            params.width=150;
            params.height=150;
        }
        if (r==3)
        {
            params.width=175;
            params.height=175;
        }
        im.setLayoutParams(params);
    }


    public void  changeSize (ImageView im , int r)
    {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) im.getLayoutParams();

        if (r==0)
        {
            params.width=70;
            params.height=70;
        }
        if (r==1)
        {
            params.width=80;
            params.height=80;
        }
        if (r==2)
        {
            params.width=90;
            params.height=90;
        }
        if (r==3)
        {
            params.width=100;
            params.height=100;
        }
         im.setLayoutParams(params);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}