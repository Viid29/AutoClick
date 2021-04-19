package balteem.automatictap.autoclicker.clicker.info;

import java.util.ArrayList;

public class preset_info {

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int flag) {
        Flag = flag;
    }

    public long getMillis() {
        return Millis;
    }

    public void setMillis(long millis) {
        Millis = millis;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    String Name;
    int Flag;
    long Millis;
    int Quantity;

    public ArrayList<preset_target_info> getPreset_targets() {
        return preset_targets;
    }

    public void setPreset_targets(ArrayList<preset_target_info> preset_targets) {
        this.preset_targets = preset_targets;
    }

    ArrayList <preset_target_info> preset_targets = new ArrayList<>();

    public preset_info() {
    }

}


