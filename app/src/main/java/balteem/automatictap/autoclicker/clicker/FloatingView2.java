package balteem.automatictap.autoclicker.clicker;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;

import org.json.JSONException;

import java.util.ArrayList;

import balteem.automatictap.autoclicker.clicker.info.SwipeCoord;
import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.info.preset_info;
import balteem.automatictap.autoclicker.clicker.info.preset_target_info;
import balteem.automatictap.autoclicker.clicker.info.settings_stop_info;
import balteem.automatictap.autoclicker.clicker.info.targets_info;
import balteem.automatictap.autoclicker.clicker.utils.Save;
import balteem.automatictap.autoclicker.clicker.utils.converterClass;

/**
 * Created by wilburnLee on 2019/4/22.
 */
public class FloatingView2 extends FrameLayout implements View.OnClickListener {
    private Context mContext;
    private View mView ;
    private ImageView mPlayView;
    private ImageView mStopView;
    private ImageView mDeleteView;
    private ImageView mCloseView;
    private  ImageView mTargetView;
    private  ImageView mAddView;
    private ImageView mSettigsnView;
    LayoutInflater mLayoutInflater;
    private int mTouchStartX, mTouchStartY;
    private int mTouchStartX2, mTouchStartY2;
    private int mTouchStartX3, mTouchStartY3;
    private int mTouchStartX4, mTouchStartY4;


    ArrayList <targets_info> targets_infos = new ArrayList<>();

    long millis;
    int quantity;
    int H , M , S ;

    private WindowManager.LayoutParams mParams;
    private WindowManager.LayoutParams mParams2;
    private FloatingManager mWindowManager;
    private FloatingManager mWindowManager2;
    CheckBox checkbox1, checkbox2 ,checkbox3;
    int flag ;
    EditText   EditTextQuantity;
    TextView   TimeTextView;
     String [] hours = new  String[24];
     String [] minsek = new String[60];

    ArrayList <WindowManager.LayoutParams> params = new ArrayList<>();
    ArrayList <View> targets = new ArrayList<>();
    private String mCurState;
    public FloatingView2(Context context) {
        super(context);
        mContext = context.getApplicationContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        mView = mLayoutInflater.inflate(R.layout.floating_view_2, null);


       // Indef = mTarget.findViewById(R.id.Indef);
        mAddView = (ImageView) mView.findViewById(R.id.add);
        mDeleteView = (ImageView) mView.findViewById(R.id.delete);
        mPlayView = (ImageView) mView.findViewById(R.id.play);
        mStopView = (ImageView) mView.findViewById(R.id.stop);
        mCloseView = (ImageView) mView.findViewById(R.id.close);
        mSettigsnView = (ImageView) mView.findViewById(R.id.settings);
        mAddView.setOnClickListener(this);
        mPlayView.setOnClickListener(this);
        mStopView.setOnClickListener(this);
        mCloseView.setOnClickListener(this);

        mView.setOnTouchListener(mOnTouchListener);

        mWindowManager = FloatingManager.getInstance(mContext);
        mWindowManager2 = FloatingManager.getInstance(mContext);



        mAddView.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                //your stuff
                if (mCurState != AutoService.MULTITAPPLAY)
                { showTarget2(0,0, 0 , 0, 0);}
                return true;
            }
        });
        mAddView.setOnClickListener(new OnClickListener() {
            @Override
           public void onClick (View view){
                //your stuff
                if (mCurState != AutoService.MULTITAPPLAY)
                    { showTarget(0,0,0);}
            }
        });


        mDeleteView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick (View view){
                //your stuff
                if (mCurState != AutoService.MULTITAPPLAY)
                { if (targets_infos.size()>0)
                {
                    if (targets_infos.get(targets_infos.size()-1).getType().equals("tap"))
                    {
                        mWindowManager2.removeView(targets_infos.get(targets_infos.size()-1).getTarget());
                    }
                    else
                    {
                        mWindowManager2.removeView(targets_infos.get(targets_infos.size()-1).getStartViewSwipe());
                        mWindowManager2.removeView(targets_infos.get(targets_infos.size()-1).getEndViewSwipe());
                    }

                    targets_infos.remove(targets_infos.size()-1);
                }
                    }
            }
        });


        mSettigsnView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick (View view) {
                if (mCurState != AutoService.MULTITAPPLAY) {
                    final LayoutInflater li = LayoutInflater.from(mContext);
                    final View promptsView = li.inflate(R.layout.save_preset_layout, null);
                    final android.app.AlertDialog.Builder mDialogBuilder = new android.app.AlertDialog.Builder(mContext);

                    TextView TextViewSave = promptsView.findViewById(R.id.TextViewSave);
                    final EditText NameEditText = promptsView.findViewById(R.id.NameEditText);

                    EditTextQuantity = promptsView.findViewById(R.id.EditTextQuantity);
                    TimeTextView = promptsView.findViewById(R.id.TimeTextView);
                    checkbox1 = promptsView.findViewById(R.id.checkbox1);
                    checkbox2 = promptsView.findViewById(R.id.checkbox2);
                    checkbox3 = promptsView.findViewById(R.id.checkbox3);


                    if (app_settings.getInstance().getPresetName() != null) {
                        NameEditText.setText(app_settings.getInstance().getPresetName());
                    }


                    for (int i = 0; i < minsek.length; i++) {
                        if (i < 10) {
                            hours[i] = "0" + i;
                            minsek[i] = "0" + i;
                        } else {
                            minsek[i] = String.valueOf(i);
                            if (i < 24) hours[i] = String.valueOf(i);
                        }
                    }
                    try {
                        millis = app_settings.getInstance().getChoosenSettingStop2().getMillis();
                        quantity = app_settings.getInstance().getChoosenSettingStop2().getQuantity();
                        flag = app_settings.getInstance().getChoosenSettingStop2().getFlag();
                    }
                    catch (Exception e )
                    {
                        settings_stop_info st2 = new settings_stop_info();
                        st2.setQuantity(10);
                        st2.setFlag(1);
                        st2.setMillis(300000);
                        app_settings.getInstance().setChoosenSettingStop2(st2);
                        millis = app_settings.getInstance().getChoosenSettingStop2().getMillis();
                        quantity = app_settings.getInstance().getChoosenSettingStop2().getQuantity();
                        flag = app_settings.getInstance().getChoosenSettingStop2().getFlag();
                    }
                    init();

                    checkbox1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clear();
                            flag = 1;
                            checkbox1.setChecked(true);
                        }
                    });
                    checkbox2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clear();
                            flag = 2;
                            checkbox2.setChecked(true);
                        }
                    });
                    checkbox3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clear();
                            flag = 3;
                            checkbox3.setChecked(true);
                        }
                    });

                    mDialogBuilder.setView(promptsView);

                   final android.app.AlertDialog alertDialog = mDialogBuilder.create();
                    alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

                    TimeTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LayoutInflater li1 = LayoutInflater.from(mContext);
                            View promptsView1 = li1.inflate(R.layout.time_layout, null);
                             android.app.AlertDialog.Builder mDialogBuilder1 = new android.app.AlertDialog.Builder(mContext);
                            mDialogBuilder1.setView(promptsView1);

                            TextView TextViewPurshape = promptsView1.findViewById(R.id.TextViewPurshape);
                            final NumberPicker Num1 = promptsView1.findViewById(R.id.Num1);
                            final NumberPicker Num2 = promptsView1.findViewById(R.id.Num2);
                            final NumberPicker Num3 = promptsView1.findViewById(R.id.Num3);


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

                            final android.app.AlertDialog  alertDialog12 = mDialogBuilder1.create();
                            alertDialog12.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

                            TextViewPurshape.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog12.cancel();
                                    H = Num1.getValue();
                                    M = Num2.getValue();
                                    S = Num3.getValue();
                                    TimeTextView.setText(hours[H] + ":" + minsek[M] + ":" + minsek[S]);
                                    millis = (H * 3600000) + (M * 60000) + S * 1000;

                                }
                            });

                            alertDialog12.show();
                        }
                    });


                    TextViewSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                                for (int i = 0; i < targets_infos.size(); i++) {
                                    if (targets_infos.get(i).getType().equals("tap")) {
                                        int[] loc = new int[2];
                                        targets_infos.get(i).getTarget().getLocationOnScreen(loc);
                                        targets_infos.get(i).setLocationTarget(loc);
                                    } else {
                                        int[] loc = new int[2];
                                        targets_infos.get(i).getStartViewSwipe().getLocationOnScreen(loc);
                                        targets_infos.get(i).setLocationStart(loc);
                                        int[] loc1 = new int[2];
                                        targets_infos.get(i).getEndViewSwipe().getLocationOnScreen(loc1);
                                        targets_infos.get(i).setLocationEnd(loc1);
                                    }
                                }
                                app_settings.getInstance().setChoosenTargets(targets_infos);
                                app_settings.getInstance().getChoosenSettingStop2().setMillis(millis);
                                if (!EditTextQuantity.getText().toString().equals("")) {
                                    app_settings.getInstance().getChoosenSettingStop2().setQuantity(Integer.parseInt(EditTextQuantity.getText().toString()));
                                }
                                app_settings.getInstance().getChoosenSettingStop2().setFlag(flag);


                                if (NameEditText.getText().toString().equals(""))
                                {
                                    String name = "Config " + app_settings.getInstance().getPresetArrayList().size();
                                    if (name.equals("")) name = "Config 0";
                                    NameEditText.setText(name);
                                }
                            app_settings.getInstance().setPresetName(NameEditText.getText().toString());
                                if (app_settings.getInstance().getIndChoosenPreset()<0) {
                                    converterClass.addPreset();
                                    try {
                                        converterClass.convert();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Save.savePresets(mContext);
                                    int l =  app_settings.getInstance().getPresetArrayList().size();
                                    app_settings.getInstance().setChoosePreset(app_settings.getInstance().getPresetArrayList().get(l-1));
                                    app_settings.getInstance().setIndChoosenPreset(l-1);
                            }
                                else
                                {
                                    converterClass.changePreset(app_settings.getInstance().getIndChoosenPreset());
                                    try {
                                        converterClass.convert();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Save.savePresets(mContext);
                                }
                            alertDialog.cancel();
                        }
                    });

                    try {
                        alertDialog.show();
                    }
                    catch (Exception er) {}


                }
            }});





    }

    public void showAlpha ()
    {
       mPlayView.setAlpha((float) 0.5);
       mDeleteView.setAlpha((float) 0.5);


       mAddView.setAlpha((float) 0.5);
       mSettigsnView.setAlpha((float) 0.5);
    }

    public void hideAlpha ()
    {
        mPlayView.setAlpha((float) 1.0);
        mDeleteView.setAlpha((float) 1.0);

        mAddView.setAlpha((float) 1.0);
        mSettigsnView.setAlpha((float) 1.0);
    }



   public void showTutorial ()
   {
       LayoutInflater li1 = LayoutInflater.from(mView.getContext());
       View promptsView = li1.inflate(R.layout.tutorial_layout_2, null);
       AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AppTheme));
       mDialogBuilder.setView(promptsView);

       TextView TextViewPurshape = promptsView.findViewById(R.id.TextOk);
       final AlertDialog alertDialog3 = mDialogBuilder.create();
       alertDialog3.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
       TextViewPurshape.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               alertDialog3.cancel();
           }});

       alertDialog3.show();

   }




    public void showTarget(int x , int y , int flag)
    {
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View target = mLayoutInflater.inflate(R.layout.target_view, null); //
        TextView Indef = target.findViewById(R.id.Indef);
        mTargetView = (ImageView) target.findViewById(R.id.target);
        changeSizeTarget(mTargetView , app_settings.getInstance().getSizeTarget());

        targets_info info = new targets_info();
        info.setTarget(target);
        info.setType("tap");
   //     targets.add(target);

        WindowManager.LayoutParams mParams2 = new WindowManager.LayoutParams();
        mParams2.gravity =Gravity.CENTER;
        if (Build.VERSION.SDK_INT >= 26) {
            mParams2.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mParams2.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        mParams2.format = PixelFormat.RGBA_8888;
        mParams2.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mParams2.width = LayoutParams.WRAP_CONTENT;
        mParams2.height = LayoutParams.WRAP_CONTENT;

        if (flag == 1){
            mParams2.gravity = Gravity.TOP|Gravity.LEFT;
            mParams2.x = x ;
            mParams2.y = y;

        }
        if (targets_infos.size()==0)
        {
            Indef.setText("1");
            info.setTapParam(mParams2);
        }
        else
        {
            String c = String.valueOf(targets_infos.size() + 1);
            Indef.setText(c);
            info.setTapParam(mParams2);
        }

        target.setOnTouchListener(mOnTouchListener2);
        boolean result = mWindowManager2.addView(target, mParams2);



        int [] loc = new int[2];
        target.getLocationOnScreen(loc);
        info.setLocationTarget(loc);
        targets_infos.add(info);

        app_settings.getInstance().setChoosenTargets(targets_infos);




    }


    public void showTarget2(int x , int y ,int x1 , int y1 , int flag)
    {
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View target1 = mLayoutInflater.inflate(R.layout.target_topswipe_view, null); //
        LayoutInflater mLayoutInflater2 = LayoutInflater.from(mContext);
        View target2 = mLayoutInflater2.inflate(R.layout.target_downswipe_view, null); //

        ImageView mTargetView = (ImageView) target1.findViewById(R.id.target);
        ImageView mTargetView2 = (ImageView) target2.findViewById(R.id.target);
        TextView Indef1 = target1.findViewById(R.id.Indef);
        TextView Indef2 = target2.findViewById(R.id.Indef);


        ArrayList<ImageView> r = new ArrayList<>();
        r.add(mTargetView);
        r.add(mTargetView2);


        WindowManager.LayoutParams mParams2 = new WindowManager.LayoutParams();
        WindowManager.LayoutParams mParams3 = new WindowManager.LayoutParams();
        targets_info info = new targets_info();
        info.setEndViewSwipe(target2);
        info.setStartViewSwipe(target1);
        info.setType("swipe");



        if (Build.VERSION.SDK_INT >= 26) {
            mParams2.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            mParams3.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        } else {
            mParams2.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            mParams3.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        mParams2.format = PixelFormat.RGBA_8888;
        mParams2.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mParams2.width = LayoutParams.WRAP_CONTENT;
        mParams2.height = LayoutParams.WRAP_CONTENT;

        mParams3.format = PixelFormat.RGBA_8888;
        mParams3.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mParams3.width = LayoutParams.WRAP_CONTENT;
        mParams3.height = LayoutParams.WRAP_CONTENT;
        if (flag == 1){
            mParams2.gravity= Gravity.TOP|Gravity.LEFT;
            mParams2.x = x ;
            mParams2.y = y;
            info.setStartSwipe(mParams2);
            mParams3.gravity = Gravity.TOP|Gravity.LEFT;
            mParams3.x = x1 ;
            mParams3.y = y1;
            info.setEndSwipe(mParams3);
        }
        else {
            mParams2.gravity = Gravity.CENTER;
            mParams2.x = 0;
            mParams2.y = -200;
            info.setStartSwipe(mParams2);

            mParams3.gravity = Gravity.CENTER;
            mParams3.x = 0;
            mParams3.y = 100;
            info.setEndSwipe(mParams3);
        }
        if (targets_infos.isEmpty())
        {
            Indef1.setText("1");
            Indef2.setText("1");
        }
        else
        {
            Indef1.setText(String.valueOf(targets_infos.size() +1 ));
            Indef2.setText(String.valueOf(targets_infos.size() +1 ));
        }



        target1.setOnTouchListener(mOnTouchListener3);
        target2.setOnTouchListener(mOnTouchListener4);


        boolean result = mWindowManager2.addView(target1, info.getStartSwipe());
        boolean result2 = mWindowManager2.addView(target2, info.getEndSwipe());


   /*    int [] loc = new int[2];
        target1.getLocationOnScreen(loc);
        info.setLocationStart(loc);
         loc = new int[2];
        target2.getLocationOnScreen(loc);
        info.setLocationEnd(loc);*/
        targets_infos.add(info);
        app_settings.getInstance().setChoosenTargets(targets_infos);
        rotateImages(targets_infos.size()-1);



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


    public void show() {
        mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.CENTER;
        mParams.x = 0;
        mParams.y = 300;
        changeSize(mAddView , app_settings.getInstance().getSizeMenu());
        changeSize(mPlayView , app_settings.getInstance().getSizeMenu());
        changeSize(mStopView , app_settings.getInstance().getSizeMenu());
        changeSize(mCloseView , app_settings.getInstance().getSizeMenu());
        changeSize(mDeleteView , app_settings.getInstance().getSizeMenu());
        changeSize(mSettigsnView , app_settings.getInstance().getSizeMenu());

        //Всегда отображаться в окне приложения
        if (Build.VERSION.SDK_INT >= 26) {
            mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        //Установите формат изображения, эффект - прозрачный фон
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mParams.width = LayoutParams.WRAP_CONTENT;
        mParams.height = LayoutParams.WRAP_CONTENT;
        boolean result = mWindowManager.addView(mView, mParams);
       // Toast.makeText(getContext(), "Отображение плавающего окна:" + result, Toast.LENGTH_LONG).show();
        //Покадровая анимация
//        AnimationDrawable animationDrawable=(AnimationDrawable)mImageView.getDrawable();
//        animationDrawable.start();
    }

    public void hide() {
        mWindowManager2.removeView(mView);
        for (;targets_infos.size()>0;) {
                if (targets_infos.get(targets_infos.size() - 1).getType().equals("tap")) {
                    mWindowManager2.removeView(targets_infos.get(targets_infos.size() - 1).getTarget());
                } else {
                    mWindowManager2.removeView(targets_infos.get(targets_infos.size() - 1).getStartViewSwipe());
                    mWindowManager2.removeView(targets_infos.get(targets_infos.size() - 1).getEndViewSwipe());
                }

                targets_infos.remove(targets_infos.size() - 1);

        }
    }

    public void loadPreset ()
    {
        preset_info preset = app_settings.getInstance().getChoosePreset();



        app_settings.getInstance().getChoosenSettingStop2().setFlag(preset.getFlag());
        app_settings.getInstance().getChoosenSettingStop2().setQuantity(preset.getQuantity());
        app_settings.getInstance().getChoosenSettingStop2().setMillis(preset.getMillis());
        app_settings.getInstance().setPresetName(preset.getName());


        ArrayList <preset_target_info> t = preset.getPreset_targets();
        for (int i = 0 ; i<t.size() ; i++)
        {
            String type = t.get(i).getType();
            if (type.equals("tap"))
            {
                showTarget(t.get(i).getLocationTarget()[0] , t.get(i).getLocationTarget()[1] , 1);
            }
            else
            {
                showTarget2(t.get(i).getLocationStart()[0] , t.get(i).getLocationStart()[1] , t.get(i).getLocationEnd()[0] , t.get(i).getLocationEnd()[1] ,1);
                rotateImages2(i, t.get(i).getLocationStart()[0] , t.get(i).getLocationStart()[1] , t.get(i).getLocationEnd()[0] , t.get(i).getLocationEnd()[1] );
            }
        }


    }


    public void updt1() {
        for (int i = 0; i<targets_infos.size() ; i++) {
            if (targets_infos.get(i).getType().equals("tap")) {
                View target = targets_infos.get(i).getTarget();

                WindowManager.LayoutParams Param = targets_infos.get(i).getTapParam();
                Param.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
                mWindowManager2.updateView(target, Param);

            }
            else
            {
                View targetStart = targets_infos.get(i).getStartViewSwipe();
                View targetEnd = targets_infos.get(i).getEndViewSwipe();

                WindowManager.LayoutParams Param = targets_infos.get(i).getStartSwipe();
                Param.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
                mWindowManager2.updateView(targetStart, Param);


                WindowManager.LayoutParams Param2 = targets_infos.get(i).getEndSwipe();
                Param2.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
                mWindowManager2.updateView(targetEnd, Param2);
            }
        }
    }
    public void updt2() {
        for (int i = 0; i<targets_infos.size() ; i++) {


            if (targets_infos.get(i).getType().equals("tap")) {
                View target = targets_infos.get(i).getTarget();

                WindowManager.LayoutParams Param = targets_infos.get(i).getTapParam();
                Param.flags =  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
                mWindowManager2.updateView(target, Param);

            }
            else
            {
                View targetStart = targets_infos.get(i).getStartViewSwipe();
                View targetEnd = targets_infos.get(i).getEndViewSwipe();

                WindowManager.LayoutParams Param = targets_infos.get(i).getStartSwipe();
                Param.flags =  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
                mWindowManager2.updateView(targetStart, Param);


                WindowManager.LayoutParams Param2 = targets_infos.get(i).getEndSwipe();
                Param2.flags =  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
                mWindowManager2.updateView(targetEnd, Param2);
            }

        }
    }



    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchStartX = (int) event.getRawX();
                    mTouchStartY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!AutoService.PLAY.equals(mCurState)) {
                        mParams.x += (int) event.getRawX() - mTouchStartX;
                        mParams.y += (int) event.getRawY() - mTouchStartY;//Относительно левого верхнего угла экрана
                        mWindowManager.updateView(mView, mParams);
                        mTouchStartX = (int) event.getRawX();
                        mTouchStartY = (int) event.getRawY();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    };



    private OnTouchListener mOnTouchListener2 = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            View v = view;
            TextView indef = view.findViewById(R.id.Indef);
            int ind = Integer.parseInt(indef.getText().toString());
            WindowManager.LayoutParams Param = targets_infos.get(ind-1).getTapParam();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchStartX2 = (int) event.getRawX();
                    mTouchStartY2 = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!AutoService.MULTITAPPLAY.equals(mCurState)) {
                        Param.x += (int) event.getRawX() - mTouchStartX2 ;
                        Param.y += (int) event.getRawY() - mTouchStartY2 ;//Относительно левого верхнего угла экрана
                        mWindowManager.updateView(v, Param);

                        targets_infos.get(ind-1).setTapParam(Param);
                        mTouchStartX2 = (int) event.getRawX();
                        mTouchStartY2 = (int) event.getRawY();
                        int [] loc = new int[2];
                        v.getLocationOnScreen(loc);
                        targets_infos.get(ind-1).setLocationTarget(loc);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    };

    private OnTouchListener mOnTouchListener3 = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            View v = view;
            WindowManager.LayoutParams StartParam = null, EndParam = null, ChooseParam = null;
            int ti =-1;
            int ind =-1;

            for (int i = 0 ; i<targets_infos.size() ; i++)
            {
                if ((v==targets_infos.get(i).getStartViewSwipe()))
                {
                    ti=1;
                    ind = i;
                    StartParam = targets_infos.get(i).getStartSwipe();
                    EndParam = targets_infos.get(i).getEndSwipe();
                }
            }


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchStartX3 = (int) event.getRawX();
                        mTouchStartY3 = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!AutoService.MULTITAPPLAY.equals(mCurState)) {
                            StartParam.x += (int) event.getRawX() - mTouchStartX3;
                            StartParam.y += (int) event.getRawY() - mTouchStartY3;//Относительно левого верхнего угла экрана
                            mWindowManager.updateView(v, StartParam);
                            targets_infos.get(ind).setStartSwipe(StartParam);
                            mTouchStartX3 = (int) event.getRawX();
                            mTouchStartY3 = (int) event.getRawY();
                            rotateImages(ind);
                            int [] loc = new int[2];
                            v.getLocationOnScreen(loc);
                            targets_infos.get(ind).setLocationStart(loc);

                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }


            return true;
        }
    };

    private OnTouchListener mOnTouchListener4 = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            View v = view;
            WindowManager.LayoutParams StartParam = null, EndParam = null, ChooseParam = null;
            int ti =-1;
            int ind =-1;

            for (int i = 0 ; i<targets_infos.size() ; i++)
            {

                if (v==targets_infos.get(i).getEndViewSwipe())
                {
                    ind = i;
                    EndParam = targets_infos.get(i).getEndSwipe();

                }
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchStartX4 = (int) event.getRawX();
                    mTouchStartY4 = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!AutoService.MULTITAPPLAY.equals(mCurState)) {
                        EndParam.x += (int) event.getRawX() - mTouchStartX4;
                        EndParam.y += (int) event.getRawY() - mTouchStartY4;//Относительно левого верхнего угла экрана
                        mWindowManager.updateView(v, EndParam);
                        targets_infos.get(ind).setEndSwipe(EndParam);
                        mTouchStartX4 = (int) event.getRawX();
                        mTouchStartY4 = (int) event.getRawY();
                        rotateImages(ind);
                        int [] loc = new int[2];
                        v.getLocationOnScreen(loc);
                        targets_infos.get(ind).setLocationEnd(loc);

                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    };

    private float rotation(int x1 , int x2 , int y1 ,  int y2) {
        double delta_x = (x1 - x2);
        double delta_y = (y1 - y2);
        double radians = Math.atan2(delta_y, delta_x);

        return (float) Math.toDegrees(radians);
    }
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getContext(), AutoService.class);
        switch (view.getId()) {
            case R.id.play:
                if (mCurState != AutoService.MULTITAPPLAY) {
                    showAlpha();
                    mCurState = AutoService.MULTITAPPLAY;
                    ArrayList<int[]> locations = new ArrayList<>();
                    ArrayList<SwipeCoord> locswipe = new ArrayList<>();
                    for (int i = 0; i < targets_infos.size(); i++) {
                        int[] location = new int[2];
                        if (targets_infos.get(i).getType().equals("tap")) {
                            int[] loc = new int[2];
                            targets_infos.get(i).getTarget().getLocationOnScreen(loc);
                            targets_infos.get(i).setLocationTarget(loc);

                   /*     if (app_settings.getInstance().getSizeTarget()==0)
                        {
                            location [0] = location[0] + 50;
                            location [1] =location[1] + 50;
                        }
                        if (app_settings.getInstance().getSizeTarget()==1)
                        {
                            location [0] = location[0] + 62;
                            location [1] =location[1] + 62;}
                        if (app_settings.getInstance().getSizeTarget()==2)
                        {
                            location [0] = location[0] + 75;
                            location [1] =location[1] + 75;}
                        if (app_settings.getInstance().getSizeTarget()==3)
                        {
                            location [0] = location[0] + 87;
                            location [1] =location[1] + 87;}
                        locations.add(location);*/
                        } else {

                            int[] loc = new int[2];
                            targets_infos.get(i).getStartViewSwipe().getLocationOnScreen(loc);
                            targets_infos.get(i).setLocationStart(loc);
                            int[] loc1 = new int[2];
                            targets_infos.get(i).getEndViewSwipe().getLocationOnScreen(loc1);
                            targets_infos.get(i).setLocationEnd(loc1);
                        }
                    }

                    app_settings.getInstance().setChoosenTargets(targets_infos);

                    intent.putExtra(AutoService.ACTION, AutoService.MULTITAPPLAY);
                }
                    break;
                    case R.id.stop:
                        mCurState = AutoService.MULTITAPSTOP;
                        hideAlpha();
                        intent.putExtra(AutoService.ACTION, AutoService.MULTITAPSTOP);
                        break;
                    case R.id.close:
                        intent.putExtra(AutoService.ACTION, AutoService.MULTITAPHIDE);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Intent appMain = new Intent(getContext(), MainActivity.class);
                        hideAlpha();
                        appMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getContext().startActivity(appMain);
                        break;
                }
                getContext().startService(intent);

    }


    public void stop () {
        mCurState = AutoService.MULTITAPSTOP;
    }


   public void rotateImages (int ind)
   {
       View target1 = targets_infos.get(ind).getStartViewSwipe();
       View target2 = targets_infos.get(ind).getEndViewSwipe();
       WindowManager.LayoutParams Param1 = targets_infos.get(ind).getStartSwipe();
       WindowManager.LayoutParams Param2 = targets_infos.get(ind).getEndSwipe();
       ImageView Start = target1.findViewById(R.id.target);
       ImageView End = target2.findViewById(R.id.target);
       int [] location1 = new int[2];
       int [] location2 = new int[2];
       target1.getLocationOnScreen(location1);
       target2.getLocationOnScreen(location2);
       int x1 = location1 [0];
       int y1 = location1 [1];
       int x2 =location2 [0];
       int y2 = location2[1];
       float c = 0, b = 0;



       if (x1 == x2)
       {
          if (y2>y1)
           {
               Start.setRotation(180);
               End.setRotation(180);
           }
       }
       else {
       if (y1 == y2 )
       {
           if (x1>x2)
           { Start.setRotation(270);
               End.setRotation(270);
           }
           if (x2>x1)
           {
               Start.setRotation(90);
               End.setRotation(90);
           }
       }
        else {



           float degrees = rotation(x1,x2 , y1, y2);
           int d = Math.round(degrees) ;
           Start.setRotation(d + 90);
           End.setRotation(d + 90);
       }}
   }


    public void rotateImages2 (int ind , int x1 , int y1 , int x2 , int y2)
    {
        View target1 = targets_infos.get(ind).getStartViewSwipe();
        View target2 = targets_infos.get(ind).getEndViewSwipe();
        WindowManager.LayoutParams Param1 = targets_infos.get(ind).getStartSwipe();
        WindowManager.LayoutParams Param2 = targets_infos.get(ind).getEndSwipe();
        ImageView Start = target1.findViewById(R.id.target);
        ImageView End = target2.findViewById(R.id.target);
      /*  int [] location1 = new int[2];
        int [] location2 = new int[2];
        target1.getLocationOnScreen(location1);
        target2.getLocationOnScreen(location2);
        int x1 = location1 [0];
        int y1 = location1 [1];
        int x2 =location2 [0];
        int y2 = location2[1];*/
        float c = 0, b = 0;



       /* if (x1 == x2)
        {
            if (y2>y1)
            {
                Start.setRotation(180);
                End.setRotation(180);
            }
        }
        else {
            if (y1 == y2 )
            {
                if (x1>x2)
                { Start.setRotation(270);
                    End.setRotation(270);
                }
                if (x2>x1)
                {
                    Start.setRotation(90);
                    End.setRotation(90);
                }
            }
            else {*/



                float degrees = rotation(x1,x2 , y1, y2);
                int d = Math.round(degrees) ;
                Start.setRotation(d + 90);
                End.setRotation(d + 90);
        //   }}
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
