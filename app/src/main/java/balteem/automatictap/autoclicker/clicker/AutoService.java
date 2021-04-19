package balteem.automatictap.autoclicker.clicker;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.graphics.Path;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import java.util.ArrayList;

import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.info.targets_info;

/**
 * Created by wilburnLee on 2019/4/22.
 */
public class AutoService extends AccessibilityService {

    public static final String ACTION = "action";
    public static final String SHOW = "show";
    public static final String HIDE = "hide";
    public static final String PLAY = "play";
    public static final String STOP = "stop";
    public static final String PRESET = "preset";

    public static final String MODE = "mode";
    public static final String TAP = "tap";
    public static final String SWIPE = "swipe";
    public static final String MULTITAP = "multitap";
    public static final String MULTITAPHIDE = "multitaphide";
    public static final String MULTITAPPLAY = "multitapplay";
    public static final String MULTITAPSTOP = "multitapstop";






    public static int IndefNextTargetInfo = 0;
    public static int FlagStop ;
    public static long MillisStop;
    public static int QuantityStop;


    private FloatingView mFloatingView ;
    private FloatingView2   mFloatingView2;

    private int mInterval;
    private int mX;
    private int mY;
    private String mMode;
    ArrayList <int[]> coords = new ArrayList<>();
    ArrayList <targets_info> targets_infos = new ArrayList<>();
    private Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mFloatingView = new FloatingView(this);
        mFloatingView2 = new FloatingView2(this);

        HandlerThread handlerThread = new HandlerThread("auto-handler");
        handlerThread.start();


        mHandler = new Handler(handlerThread.getLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getStringExtra(ACTION);
            if (MULTITAP.equals(action)) {
                mInterval = intent.getIntExtra("interval", 1000) ;
                mMode = intent.getStringExtra(MODE);
                mFloatingView2.show();
                mFloatingView2.showTarget(0,0,0);
                try {
                    if (app_settings.getInstance().getTutorial_1().equals("0")) {
                        mFloatingView2.showTutorial();}
                } catch (Exception e) {}

            }
            else if (MULTITAPHIDE.equals(action)) {
                mFloatingView2.hide();
                app_settings.getInstance().setChoosePreset(null);
                targets_infos = new ArrayList<>();
                app_settings.getInstance().setPresetName(null);
                app_settings.getInstance().setChoosenTargets(null);
                mHandler.removeCallbacksAndMessages(null);
            }
            else if (MULTITAPPLAY.equals(action)) {
                coords = app_settings.getInstance().getCoords();
                mMode = MULTITAP;
                try {
                    FlagStop = app_settings.getInstance().getChoosenSettingStop2().getFlag();
                    MillisStop = app_settings.getInstance().getChoosenSettingStop2().getMillis();
                    QuantityStop = app_settings.getInstance().getChoosenSettingStop2().getQuantity();
                }
                catch (Exception e)
                {
                    FlagStop = 1;
                    MillisStop = 30000;
                    QuantityStop = 10;
                }

                IndefNextTargetInfo=0;
                mFloatingView2.updt2();
                if (mRunnable == null) {
                    mRunnable = new IntervalRunnable();
                }
                mHandler.postDelayed(mRunnable, mInterval);
                Toast.makeText(getBaseContext(), getString(R.string.start), Toast.LENGTH_SHORT).show();
            }
            else if (MULTITAPSTOP.equals(action)) {
                mFloatingView2.updt1();
                mHandler.removeCallbacksAndMessages(null);
                Toast.makeText(getBaseContext(), getString(R.string.pause), Toast.LENGTH_SHORT).show();
            }
            else if (SHOW.equals(action)) {
                mInterval = intent.getIntExtra("interval", 1000) ;
                mMode = intent.getStringExtra(MODE);
                mFloatingView.show();
                mFloatingView.showTarget(0,0 , 0);
                try {
                    if (app_settings.getInstance().getTutorial_1().equals("0")) {
                        mFloatingView.showTutorial();
                    }
                } catch (Exception e) {}
            }
            else if (HIDE.equals(action)) {
                mFloatingView.hide();
                mHandler.removeCallbacksAndMessages(null);
            }
            else if (PLAY.equals(action)) {
                mX = intent.getIntExtra("x", 0);
                mY = intent.getIntExtra("y", 0);
                mFloatingView.updt2();
                try {
                    FlagStop = app_settings.getInstance().getChoosenSettingStop1().getFlag();
                    MillisStop = app_settings.getInstance().getChoosenSettingStop1().getMillis();
                    QuantityStop = app_settings.getInstance().getChoosenSettingStop1().getQuantity();
                } catch (Exception e)
                    {
                        FlagStop = 1;
                        MillisStop = 30000;
                        QuantityStop = 10;
                    }




                    if (mRunnable == null) {
                    mRunnable = new IntervalRunnable();
                }
                mRunnable.run();
                Toast.makeText(getBaseContext(), getString(R.string.start), Toast.LENGTH_SHORT).show();
            }
            else if (STOP.equals(action)) {
                mFloatingView.updt1();
                mHandler.removeCallbacksAndMessages(null);
                Toast.makeText(getBaseContext(), getString(R.string.pause), Toast.LENGTH_SHORT).show();
            }
            else if (PRESET.equals(action)) {
                mInterval = intent.getIntExtra("interval", 1000) ;
                mMode = intent.getStringExtra(MODE);
                mFloatingView2.loadPreset();
                mFloatingView2.show();

            }

        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void playTap(int x, int y) {
        Path path = new Path();

        path.moveTo(x, y);
        path.lineTo(x, y);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, 0, 100));
        GestureDescription gestureDescription = builder.build();
        dispatchGesture(gestureDescription,new GestureResultCallback() {
        @Override
        public void onCompleted(GestureDescription gestureDescription) {
            super.onCompleted(gestureDescription);

        }

        @Override
        public void onCancelled(GestureDescription gestureDescription) {
            super.onCancelled(gestureDescription);
            Toast.makeText(getBaseContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }, null);
        mHandler.postDelayed(mRunnable, mInterval);
    }

    private void playMultiTap(int x , int y) {

           Path path = new Path();
           path.moveTo(x, y);
           path.lineTo(x, y);
           GestureDescription.Builder builder = new GestureDescription.Builder();
           builder.addStroke(new GestureDescription.StrokeDescription(path, 0, 100));
           GestureDescription gestureDescription = builder.build();
           dispatchGesture(gestureDescription, new GestureResultCallback() {
               @Override
               public void onCompleted(GestureDescription gestureDescription) {
                   super.onCompleted(gestureDescription);

               }

               @Override
               public void onCancelled(GestureDescription gestureDescription) {
                   super.onCancelled(gestureDescription);
                   Toast.makeText(getBaseContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
               }
           }, null);
        mHandler.postDelayed(mRunnable, mInterval);
       }


    private void playSwipe(int fromX, int fromY, int toX, int toY) {
        Path path = new Path();
        path.moveTo(fromX, fromY);
        path.lineTo(toX, toY);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, 0, 400));
        GestureDescription gestureDescription = builder.build();
        dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);

            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                Toast.makeText(getBaseContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        }, null);
        mHandler.postDelayed(mRunnable, mInterval);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }

    @Override
    public void onInterrupt() {

    }

    private IntervalRunnable mRunnable;

    private class IntervalRunnable implements Runnable {
        @Override
        public void run() {
            if (TAP.equals(mMode)) {
                if (FlagStop == 1) {
                    playTap(mX, mY);
                }
                if (FlagStop == 2) {
                    if (MillisStop > mInterval) {
                        MillisStop = MillisStop - mInterval;
                        playTap(mX, mY);
                    }
                    else
                    {  mFloatingView.hideAlpha();
                        stop2();}
                }
                if (FlagStop == 3) {
                    if (QuantityStop > 0) {
                        QuantityStop = QuantityStop - 1;
                        playTap(mX, mY);
                    }
                    else
                    {  mFloatingView.hideAlpha();
                        stop2();
                    }
                }
            }


            if (MULTITAP.equals(mMode)) {

                if (FlagStop==1)
                {
                    StartMultiTap();
                }

                if (FlagStop == 2) {
                    if (MillisStop > mInterval) {
                        MillisStop = MillisStop - mInterval;
                        StartMultiTap();
                    }
                    else
                        {
                            mFloatingView2.hideAlpha();
                            stop();
                }
                }
                if (FlagStop == 3) {
                    if (QuantityStop > 0) {
                        StartMultiTap();
                    }
                    else
                    {
                        mFloatingView2.hideAlpha();
                        stop();
                         }
                }

            }
        }

    }
    public void StartMultiTap()
        {
            int r = getR();
            targets_info targets_info = nextTargetInfo();

            if (targets_info.getType().equals("tap")) {
                int[] location = new int[2];
                location = targets_info.getLocationTarget();
                playMultiTap(location[0] + r, location[1] + r);
            } else {
                int[] location1 = new int[2];
                location1 = targets_info.getLocationStart();
                int[] location2 = new int[2];
                location2 = targets_info.getLocationEnd();
                playSwipe(location1[0] + r+15, location1[1] + r+15, location2[0] + r+15, location2[1] + r+15);
            }
        }




    public targets_info nextTargetInfo ()
    {
        targets_infos = app_settings.getInstance().getChoosenTargets();
        if (targets_infos.size()>IndefNextTargetInfo)
        {

          targets_info targets_info =  targets_infos.get(IndefNextTargetInfo);
            IndefNextTargetInfo++;
            return targets_info;
        }
        else {
            if (FlagStop==3)
            {QuantityStop = QuantityStop - 1;}
            IndefNextTargetInfo = 0;
            targets_info targets_info =  targets_infos.get(IndefNextTargetInfo);
            IndefNextTargetInfo++;
            return targets_info;
        }

    }







public int getR () {
    int r = 0;
    if (app_settings.getInstance().getSizeTarget() == 0) {
        r = 50;

    }
    if (app_settings.getInstance().getSizeTarget() == 1) {
        r = 62;
    }
        if (app_settings.getInstance().getSizeTarget() == 2) {
            r = 75;
        }
        if (app_settings.getInstance().getSizeTarget() == 3) {
            r = 87;
        }
        return r;
    }


public void stop ()
{
    mFloatingView2.stop();
    Intent intent = new Intent(getBaseContext(), AutoService.class);
    intent.putExtra(AutoService.ACTION, AutoService.MULTITAPSTOP);
    startService(intent);

}

    public void stop2 ()
    {
        mFloatingView.stop();
        Intent intent = new Intent(getBaseContext(), AutoService.class);
        intent.putExtra(AutoService.ACTION, AutoService.STOP);
        startService(intent);
    }

    }







