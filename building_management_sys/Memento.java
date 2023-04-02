import java.util.ArrayList;

public class Memento {
    private ArrayList old_info;

    public Memento(ArrayList old_info) {
        this.old_info = old_info;
    }

    public ArrayList get_old_info() {
        return old_info;
    }
}
