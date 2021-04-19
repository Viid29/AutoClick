package balteem.automatictap.autoclicker.clicker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class NotWorkActivity extends AppCompatActivity {
    private Button mStart;
    private EditText mInterval;
    private RadioGroup mCheckMode;

    EditText EditTextInterval;
    ImageView BackImage;
    TextView TextViewPurshape , TextViewPurshape2;

    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_work_layout);
        TextViewPurshape = findViewById(R.id.TextViewPurshape);
        TextViewPurshape2 = findViewById(R.id.TextViewPurshape2);

        TextViewPurshape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   if (!Settings.canDrawOverlays(MainActivity.this)) {
                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, 0);
                }*/
                startActivityForResult(new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS), 0);
            }});

        TextViewPurshape2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
          //      intent.addCategory(Intent.ACTION_VIEW_PERMISSION_USAGE);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }});




    }

}