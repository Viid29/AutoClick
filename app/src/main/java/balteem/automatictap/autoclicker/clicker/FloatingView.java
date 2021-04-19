package balteem.automatictap.autoclicker.clicker;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;

import balteem.automatictap.autoclicker.clicker.info.app_settings;

public class FloatingView extends FrameLayout implements View.OnClickListener {
    private Context mContext;
    private View mView , mTarget;
    private ImageView mPlayView;
    private ImageView mStopView;
    private ImageView mCloseView;
    private  ImageView mTargetView;
    private int mTouchStartX, mTouchStartY;
    private int mTouchStartX2, mTouchStartY2;
    private int mTouchStartX3, mTouchStartY3;



    private WindowManager.LayoutParams mParams;
    private WindowManager.LayoutParams mParams2;
    private FloatingManager mWindowManager;
    private FloatingManager mWindowManager2;
    private String mCurState;
    public FloatingView(Context context) {
        super(context);
        mContext = context.getApplicationContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        mView = mLayoutInflater.inflate(R.layout.floating_view, null);
        mTarget =  mLayoutInflater.inflate(R.layout.target_view, null); //
        mTargetView = (ImageView) mTarget.findViewById(R.id.target);

        mPlayView = (ImageView) mView.findViewById(R.id.play);
        mStopView = (ImageView) mView.findViewById(R.id.stop);
        mCloseView = (ImageView) mView.findViewById(R.id.close);
        mPlayView.setOnClickListener(this);
        mStopView.setOnClickListener(this);
        mCloseView.setOnClickListener(this);




        mView.setOnTouchListener(mOnTouchListener);
        mTarget.setOnTouchListener(mOnTouchListener2);
        mWindowManager = FloatingManager.getInstance(mContext);
        mWindowManager2 = FloatingManager.getInstance(mContext);
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

    public void showAlpha ()
    {
        mPlayView.setAlpha((float) 0.5);
    }

    public void hideAlpha ()
    {
        mPlayView.setAlpha((float) 1.0);
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

    public void showTutorial ()
    {
        LayoutInflater li1 = LayoutInflater.from(mView.getContext());
        View promptsView = li1.inflate(R.layout.tutorial_layout_3, null);
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


    public void showTarget(int  x , int y , int flag)
    {
        mParams2 = new WindowManager.LayoutParams();
        changeSizeTarget (mTargetView , app_settings.getInstance().getSizeTarget());
       /* if (flag == 1){
            mParams2.gravity = Gravity.NO_GRAVITY;
            mParams2.horizontalMargin = x ;
            mParams2.verticalMargin = y;
        }
        else {*/
            mParams2.gravity =Gravity.CENTER;
       // }
       /* mParams2. = x;
        mParams2.y = y;*/
//        mParams.x = 0;
//        mParams.y = 300;
        //Всегда отображаться в окне приложения
        if (Build.VERSION.SDK_INT >= 26) {
            mParams2.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mParams2.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        //Установите формат изображения, эффект - прозрачный фон
        mParams2.format = PixelFormat.RGBA_8888;
        mParams2.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mParams2.width = LayoutParams.WRAP_CONTENT;
        mParams2.height = LayoutParams.WRAP_CONTENT;
        boolean result = mWindowManager2.addView(mTarget, mParams2);
     //   Toast.makeText(getContext(), "Отображение плавающего окна:" + result, Toast.LENGTH_LONG).show();
        //Покадровая анимация
//        AnimationDrawable animationDrawable=(AnimationDrawable)mImageView.getDrawable();
//        animationDrawable.start();
    }

    public void show() {
        mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.CENTER;
        mParams.x = 0;
        mParams.y = 300;
        changeSize(mPlayView , app_settings.getInstance().getSizeMenu());
        changeSize(mStopView , app_settings.getInstance().getSizeMenu());
        changeSize(mCloseView , app_settings.getInstance().getSizeMenu());
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
        mWindowManager.removeView(mView);
        mWindowManager2.removeView(mTarget);
    }
    public void updt1() {
        mParams2.flags =  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mWindowManager2.updateView(mTarget, mParams2);
    }
    public void updt2() {
        try {
            mParams2.flags =  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
            mWindowManager2.updateView(mTarget, mParams2);
        }
        catch (Exception e)
        {
            Intent intent = new Intent(getContext(), AutoService.class);
            intent.putExtra(AutoService.ACTION, AutoService.HIDE);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Intent appMain = new Intent(getContext(), MainActivity.class);
            appMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(appMain);
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
            String ind = indef.getText().toString();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchStartX2 = (int) event.getRawX();
                    mTouchStartY2 = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!AutoService.PLAY.equals(mCurState)) {
                        mParams2.x += (int) event.getRawX() - mTouchStartX2 ;
                        mParams2.y += (int) event.getRawY() - mTouchStartY2 ;//Относительно левого верхнего угла экрана
                        mWindowManager.updateView(mTarget, mParams2);
                        mTouchStartX2 = (int) event.getRawX();
                        mTouchStartY2 = (int) event.getRawY();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    };


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), AutoService.class);
        switch (view.getId()) {
            case R.id.play:
                mCurState = AutoService.PLAY;
                int[] location = new int[2];
                mTarget.getLocationOnScreen(location);
                showAlpha();
                intent.putExtra(AutoService.ACTION, AutoService.PLAY);
                if (app_settings.getInstance().getSizeTarget()==0)
                {
                intent.putExtra("x", location[0] + 50);
                intent.putExtra("y", location[1] + 50);}
                if (app_settings.getInstance().getSizeTarget()==1)
                {
                    intent.putExtra("x", location[0] + 62);
                    intent.putExtra("y", location[1] + 62);}
                if (app_settings.getInstance().getSizeTarget()==2)
                {
                    intent.putExtra("x", location[0] + 75);
                    intent.putExtra("y", location[1] + 75);}
                if (app_settings.getInstance().getSizeTarget()==3)
                {
                    intent.putExtra("x", location[0] + 87);
                    intent.putExtra("y", location[1] + 87);}
                break;
            case R.id.stop:
                mCurState = AutoService.STOP;
                intent.putExtra(AutoService.ACTION, AutoService.STOP);
                break;
            case R.id.close:
                intent.putExtra(AutoService.ACTION, AutoService.HIDE);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent appMain = new Intent(getContext(), MainActivity.class);
                appMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(appMain);
                break;
        }
        getContext().startService(intent);
    }
    public void stop () {
        mCurState = AutoService.STOP;
    }
}
