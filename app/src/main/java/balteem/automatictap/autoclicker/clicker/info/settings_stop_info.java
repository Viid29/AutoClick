package balteem.automatictap.autoclicker.clicker.info;

public class settings_stop_info {

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

    int Flag;
    long Millis;
    int Quantity;
}

