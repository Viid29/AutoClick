package balteem.automatictap.autoclicker.clicker.info;

import com.android.billingclient.api.SkuDetails;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Map;

public class app_settings {

    public String getShowAdd() {
        return showAdd;
    }

    public void setShowAdd(String showAdd) {
        this.showAdd = showAdd;
    }

    public String showAdd;

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    String addDate;

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    String interval ;

    public String getInterval2() {
        return interval2;
    }

    public void setInterval2(String interval2) {
        this.interval2 = interval2;
    }

    String interval2 ;


    public InterstitialAd getmIntertizalAdd() {
        return mIntertizalAdd;
    }

    public void setmIntertizalAdd(InterstitialAd mIntertizalAdd) {
        this.mIntertizalAdd = mIntertizalAdd;
    }

    InterstitialAd mIntertizalAdd;

    public ArrayList<int[]> getCoords() {
        return coords;
    }

    public void setCoords(ArrayList<int[]> coords) {
        this.coords = coords;
    }

    ArrayList <int[]> coords = new ArrayList<>();

    public String getInstructionFlag() {
        return InstructionFlag;
    }


    public void setInstructionFlag(String instructionFlag) {
        InstructionFlag = instructionFlag;
    }

    String InstructionFlag ;

    public String getSettingsFlag() {
        return SettingsFlag;
    }

    public void setSettingsFlag(String settingsFlag) {
        SettingsFlag = settingsFlag;
    }

    String SettingsFlag ;

    public int getSizeMenu() {
        return SizeMenu;
    }

    public void setSizeMenu(int sizeMenu) {
        SizeMenu = sizeMenu;
    }

    public int getSizeTarget() {
        return SizeTarget;
    }

    public void setSizeTarget(int sizeTarget) {
        SizeTarget = sizeTarget;
    }

    int SizeMenu;
    int SizeTarget;

    public String getYear_sub() {
        return year_sub;
    }

    public void setYear_sub(String year_sub) {
        this.year_sub = year_sub;
    }

    public String getMonth_sub() {
        return month_sub;
    }

    public void setMonth_sub(String month_sub) {
        this.month_sub = month_sub;
    }

    public String getDisplay_sub() {
        return display_sub;
    }

    public void setDisplay_sub(String display_sub) {
        this.display_sub = display_sub;
    }

    String year_sub;
    String month_sub;
    String display_sub;

    public Map<String, SkuDetails> getmSkuDetailsMap() {
        return mSkuDetailsMap;
    }

    public void setmSkuDetailsMap(Map<String, SkuDetails> mSkuDetailsMap) {
        this.mSkuDetailsMap = mSkuDetailsMap;
    }

    public Map<String, SkuDetails> mSkuDetailsMap ;





    public ArrayList<SwipeCoord> getCoordSwipe() {
        return CoordSwipe;
    }

    public void setCoordSwipe(ArrayList<SwipeCoord> coordSwipe) {
        CoordSwipe = coordSwipe;
    }

    ArrayList<SwipeCoord> CoordSwipe;

    public ArrayList<targets_info> getChoosenTargets() {
        return choosenTargets;
    }

    public void setChoosenTargets(ArrayList<targets_info> choosenTargets) {
        this.choosenTargets = choosenTargets;
    }

    ArrayList<targets_info> choosenTargets;


    public settings_stop_info getChoosenSettingStop1() {
        return ChoosenSettingStop1;
    }

    public void setChoosenSettingStop1(settings_stop_info choosenSettingStop1) {
        ChoosenSettingStop1 = choosenSettingStop1;
    }

    public settings_stop_info getChoosenSettingStop2() {
        return ChoosenSettingStop2;
    }

    public void setChoosenSettingStop2(settings_stop_info choosenSettingStop2) {
        ChoosenSettingStop2 = choosenSettingStop2;
    }

    settings_stop_info ChoosenSettingStop1;
    settings_stop_info ChoosenSettingStop2;


    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    String presetName ;


    public JSONArray getPresets() {
        return presets;
    }

    public void setPresets(JSONArray presets) {
        this.presets = presets;
    }

    JSONArray presets = new JSONArray();

    public ArrayList<preset_info> getPresetArrayList() {
        return presetArrayList;
    }

    public void setPresetArrayList(ArrayList<preset_info> presetArrayList) {
        this.presetArrayList = presetArrayList;
    }

    ArrayList<preset_info> presetArrayList = new ArrayList<>();


    public preset_info getChoosePreset() {
        return ChoosePreset;
    }

    public void setChoosePreset(preset_info choosePreset) {
        ChoosePreset = choosePreset;
    }

    preset_info ChoosePreset = new preset_info();

    public int getIndChoosenPreset() {
        return IndChoosenPreset;
    }

    public void setIndChoosenPreset(int indChoosenPreset) {
        IndChoosenPreset = indChoosenPreset;
    }

    int IndChoosenPreset;


    public String getTutorial_1() {
        return Tutorial_1;
    }

    public void setTutorial_1(String tutorial_1) {
        Tutorial_1 = tutorial_1;
    }

    public String getTutorial_2() {
        return Tutorial_2;
    }

    public void setTutorial_2(String tutorial_2) {
        Tutorial_2 = tutorial_2;
    }

    String Tutorial_1;
    String Tutorial_2;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    String locale;


    private static app_settings dataObject = null;
    public static app_settings getInstance() {
        if (dataObject == null)
            dataObject = new app_settings();
        return dataObject;
    }
    public app_settings() {
    }
}
