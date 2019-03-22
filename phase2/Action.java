
import java.lang.Runnable;

class Action {
    String text;
    int ID;
    Runnable action;

    public Action(int i, Runnable a, String t) {
        ID = i;
        action = a;
        text = t;
    }
}