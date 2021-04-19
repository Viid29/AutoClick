package balteem.automatictap.autoclicker.clicker.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.info.preset_info;
import balteem.automatictap.autoclicker.clicker.info.preset_target_info;

public class converterClass {


    public static void convert () throws JSONException {
        JSONArray presets = app_settings.getInstance().getPresets();
        ArrayList<preset_info> presetArrayList = new ArrayList<>();


        for (int i = 0 ; i< presets.length() ; i++)
        {
            preset_info preset_info = new preset_info();
            JSONObject preset = presets.getJSONObject(i);
            preset_info.setName(preset.getString("Name"));
            preset_info.setMillis(preset.getLong("Millis"));
            preset_info.setFlag(preset.getInt("Flag"));
            preset_info.setQuantity(preset.getInt("Quantity"));

            JSONArray targets = preset.getJSONArray("targets");

           ArrayList <preset_target_info> preset_target_infos = new ArrayList<>();

            for (int y = 0 ; y< targets.length() ; y++)
            {
                JSONObject target = targets.getJSONObject(y);
                preset_target_info preset_target_info = new preset_target_info();

                preset_target_info.setType(target.getString("Type"));
                String [] gh = target.getString("LocationTarget").split(",");
                preset_target_info.setLocationTarget(new int[]{Integer.parseInt(gh[0]), Integer.parseInt(gh[1])});

                String [] gh2 = target.getString("LocationStart").split(",");
                preset_target_info.setLocationStart(new int[]{Integer.parseInt(gh2[0]), Integer.parseInt(gh2[1])});

                String [] gh3 = target.getString("LocationEnd").split(",");
                preset_target_info.setLocationEnd(new int[]{Integer.parseInt(gh3[0]), Integer.parseInt(gh3[1])});

                preset_target_infos.add(preset_target_info);
            }
            preset_info.setPreset_targets(preset_target_infos);
        presetArrayList.add(preset_info);
        }
        app_settings.getInstance().setPresetArrayList(presetArrayList);
    }


    public static void unconvert () throws JSONException {
        JSONArray presets = new JSONArray();
        ArrayList<preset_info> presetArrayList = app_settings.getInstance().getPresetArrayList();


        for (int i = 0 ; i< presetArrayList.size() ; i++)
        {
            preset_info preset_info = presetArrayList.get(i);
            JSONObject preset = new JSONObject();
            preset.put("Flag" , preset_info.getFlag());
            preset.put("Millis" , preset_info.getMillis());
            preset.put("Quantity" , preset_info.getQuantity());
            preset.put("Name" , preset_info.getName());



            JSONArray targets = new JSONArray();
            ArrayList <preset_target_info> preset_target_infos = preset_info.getPreset_targets();

            for (int y = 0 ; y< preset_target_infos.size() ; y++)
            {

                JSONObject target = new JSONObject();
                preset_target_info preset_target_info = preset_target_infos.get(y);


                target.put("Type" , preset_target_info.getType());



                String  gh = preset_target_info.getLocationTarget() [0] + "," + preset_target_info.getLocationTarget() [1];
                target.put("LocationTarget",gh);

                String  gh1 = preset_target_info.getLocationStart() [0] + "," + preset_target_info.getLocationStart() [1];
                target.put("LocationStart",gh);


                String  gh2 = preset_target_info.getLocationEnd() [0] + "," + preset_target_info.getLocationEnd() [1];
                target.put("LocationEnd",gh);

                targets.put(target);
            }
          preset.put("targets", targets);
            presets.put(preset);
        }
        app_settings.getInstance().setPresets(presets);
    }



    public static void addPreset ()
    {
        JSONArray presets = app_settings.getInstance().getPresets();
        JSONObject preset = new JSONObject();
        try {
            preset.put("Flag", app_settings.getInstance().getChoosenSettingStop2().getFlag());
            preset.put("Millis", app_settings.getInstance().getChoosenSettingStop2().getMillis());
            preset.put("Quantity", app_settings.getInstance().getChoosenSettingStop2().getQuantity());
            preset.put("Name" , app_settings.getInstance().getPresetName());

            JSONArray targets = new JSONArray();

            for (int i = 0; i < app_settings.getInstance().getChoosenTargets().size(); i++) {
                JSONObject target = new JSONObject();
                target.put("Type", app_settings.getInstance().getChoosenTargets().get(i).getType());
                String x = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationTarget()[0]);
                String y = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationTarget()[1]);
                target.put("LocationTarget", x + "," + y);

                x = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationStart()[0]);
                y = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationStart()[1]);
                target.put("LocationStart", x + "," + y);
                x = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationEnd()[0]);
                y = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationEnd()[1]);
                target.put("LocationEnd", x + "," + y);
                targets.put(i, target);
            }

            preset.put("targets", targets);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        presets.put(preset);
        app_settings.getInstance().setPresets(presets);
    }




    public  static  void changePreset (int ind)
    {
        JSONArray presets = app_settings.getInstance().getPresets();
        JSONObject preset = new JSONObject();
        JSONArray pr = new JSONArray();
        try {
            preset.put("Flag", app_settings.getInstance().getChoosenSettingStop2().getFlag());
            preset.put("Millis", app_settings.getInstance().getChoosenSettingStop2().getMillis());
            preset.put("Quantity", app_settings.getInstance().getChoosenSettingStop2().getQuantity());
            preset.put("Name" , app_settings.getInstance().getPresetName());

            JSONArray targets = new JSONArray();

            for (int i = 0; i < app_settings.getInstance().getChoosenTargets().size(); i++) {
                JSONObject target = new JSONObject();
                target.put("Type", app_settings.getInstance().getChoosenTargets().get(i).getType());
                String x = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationTarget()[0]);
                String y = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationTarget()[1]);
                target.put("LocationTarget", x + "," + y);

                x = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationStart()[0]);
                y = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationStart()[1]);
                target.put("LocationStart", x + "," + y);
                x = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationEnd()[0]);
                y = String.valueOf(app_settings.getInstance().getChoosenTargets().get(i).getLocationEnd()[1]);
                target.put("LocationEnd", x + "," + y);
                targets.put(i, target);
            }

            preset.put("targets", targets);


        for (int r = 0 ; r <presets.length() ; r++ )
        {
            if (ind==r)
            {
                pr.put(preset);
            }
            else
            {
                pr.put(presets.get(r));
            }
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
        app_settings.getInstance().setPresets(pr);
    }

    public static void changeName (int ind , String name) {
        ArrayList<preset_info> presetArrayList = app_settings.getInstance().getPresetArrayList();
        presetArrayList.get(ind).setName(name);
        app_settings.getInstance().setPresetArrayList(presetArrayList);
    }




    public static void deletePreset (int ind)
    {
        ArrayList<preset_info> presetArrayList = app_settings.getInstance().getPresetArrayList();
        if (presetArrayList.size()==1)
        {
            presetArrayList=new ArrayList<>();
        }
        else
        {
        presetArrayList.remove(ind);}
        app_settings.getInstance().setPresetArrayList(presetArrayList);
    }

}
