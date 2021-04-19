package balteem.automatictap.autoclicker.clicker.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import balteem.automatictap.autoclicker.clicker.info.app_settings;

public class Save {

    public static void SaveJsonInfo( Context ct) {

        String json = "{";
        json = json + "\"SizeTarget\":\""+ String.valueOf(app_settings.getInstance().getSizeTarget())+"\",";
        json = json + "\"Flag1\":\""+ String.valueOf(app_settings.getInstance().getChoosenSettingStop1().getFlag())+"\",";
        json = json + "\"Millis1\":\""+ String.valueOf(app_settings.getInstance().getChoosenSettingStop1().getMillis())+"\",";
        json = json + "\"Quantity1\":\""+ String.valueOf(app_settings.getInstance().getChoosenSettingStop1().getQuantity())+"\",";
        json = json + "\"Flag2\":\""+ String.valueOf(app_settings.getInstance().getChoosenSettingStop2().getFlag())+"\",";
        json = json + "\"Millis2\":\""+ String.valueOf(app_settings.getInstance().getChoosenSettingStop2().getMillis())+"\",";
        json = json + "\"Quantity2\":\""+ String.valueOf(app_settings.getInstance().getChoosenSettingStop2().getQuantity())+"\",";
        json = json + "\"SizeMenu\":\""+ String.valueOf(app_settings.getInstance().getSizeMenu())+"\",";
        json = json + "\"Locale\":\""+ app_settings.getInstance().getLocale()+"\"";
        json= json+ "}";
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(json);
            SharedPreferences sPref = (SharedPreferences) Objects.requireNonNull(ct).getSharedPreferences("INFO" , Context.MODE_PRIVATE);
            SharedPreferences.Editor ed;
            String json1 = jsonObject.toString();
            json1 = json1.replace("null","\"null\"");
            ed = sPref.edit();
            ed.remove("profile").apply();
            ed.putString("profile", json);
            ed.commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public static JSONObject LoadInfo(Context ct) {
        SharedPreferences sPref = (SharedPreferences) Objects.requireNonNull(ct).getSharedPreferences("INFO" , Context.MODE_PRIVATE);
        String savedText = sPref.getString("profile", "");
        savedText = savedText.replace("\"nameValuePairs\":{","");
        savedText = savedText.replace("\"null\"","null");
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(savedText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject ;
    }




    public static void savePresets( Context ct) {

            JSONArray presets = app_settings.getInstance().getPresets();

            SharedPreferences sPref = (SharedPreferences) Objects.requireNonNull(ct).getSharedPreferences("PRESETS" , Context.MODE_PRIVATE);
            SharedPreferences.Editor ed;
            String json1 = presets.toString();
            json1 = json1.replace("null","\"null\"");
            ed = sPref.edit();
            ed.remove("presets").apply();
            ed.putString("presets", json1);
            ed.commit();
    }

    public static void  loadPresets (Context ct) {
        JSONArray jsonArray= new JSONArray();
        SharedPreferences sPref = (SharedPreferences) Objects.requireNonNull(ct).getSharedPreferences("PRESETS" , Context.MODE_PRIVATE);
        String savedText = sPref.getString("presets", "");
        savedText = savedText.replace("\"nameValuePairs\":{","");
        savedText = savedText.replace("\"null\"","null");
        if (savedText.equals("") )
        {
            app_settings.getInstance().setPresets(jsonArray);
        }
        else
        {
        try {
            jsonArray = new JSONArray(savedText);
            app_settings.getInstance().setPresets(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }}



}
