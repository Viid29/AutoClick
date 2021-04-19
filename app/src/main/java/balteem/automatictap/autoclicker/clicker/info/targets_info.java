package balteem.automatictap.autoclicker.clicker.info;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class targets_info {

    public View getTarget() {
        return Target;
    }

    public void setTarget(View target) {
        Target = target;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
    public WindowManager.LayoutParams getTapParam() {
        return TapParam;
    }

    public void setTapParam(WindowManager.LayoutParams tapParam) {
        TapParam = tapParam;
    }






    View Target ;
    String Type ;
    View StartViewSwipe;
    View EndViewSwipe;

    WindowManager.LayoutParams TapParam;
    WindowManager.LayoutParams StartSwipe ;
    WindowManager.LayoutParams EndSwipe;

    int [] locationStart = new int [2];
    int [] locationEnd = new int [2];
    int [] locationTarget = new int [2];


    public int[] getLocationTarget() {
        return locationTarget;
    }

    public void setLocationTarget(int[] locationTarget) {
        this.locationTarget = locationTarget;
    }










    public WindowManager.LayoutParams getStartSwipe() {
        return StartSwipe;
    }

    public void setStartSwipe(WindowManager.LayoutParams startSwipe) {
        StartSwipe = startSwipe;
    }

    public WindowManager.LayoutParams getEndSwipe() {
        return EndSwipe;
    }

    public void setEndSwipe(WindowManager.LayoutParams endSwipe) {
        EndSwipe = endSwipe;
    }



    public View getStartViewSwipe() {
        return StartViewSwipe;
    }

    public void setStartViewSwipe(View startViewSwipe) {
        StartViewSwipe = startViewSwipe;
    }

    public View getEndViewSwipe() {
        return EndViewSwipe;
    }

    public void setEndViewSwipe(View endViewSwipe) {
        EndViewSwipe = endViewSwipe;
    }



    public int[] getLocationStart() {
        return locationStart;
    }

    public void setLocationStart(int[] locationStart) {
        this.locationStart = locationStart;
    }

    public int[] getLocationEnd() {
        return locationEnd;
    }

    public void setLocationEnd(int[] locationEnd) {
        this.locationEnd = locationEnd;
    }



}
