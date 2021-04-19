package balteem.automatictap.autoclicker.clicker.info;

public class preset_target_info
{
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int[] getLocationTarget() {
        return LocationTarget;
    }

    public void setLocationTarget(int[] locationTarget) {
        LocationTarget = locationTarget;
    }

    public int[] getLocationStart() {
        return LocationStart;
    }

    public void setLocationStart(int[] locationStart) {
        LocationStart = locationStart;
    }

    public int[] getLocationEnd() {
        return LocationEnd;
    }

    public void setLocationEnd(int[] locationEnd) {
        LocationEnd = locationEnd;
    }

    String Type;
    int [] LocationTarget = new int[2];
    int [] LocationStart = new int[2];
    int [] LocationEnd = new int[2];


    public preset_target_info() {
    }
}
