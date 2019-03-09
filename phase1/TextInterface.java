
import java.lang.Runnable;
import java.util.ArrayList;
import java.util.Scanner;

class TextInterface {

    private String header;
    private String footer;
    private ArrayList<Action> actions;
    boolean active;
    private TextInterface previous;

    private static Scanner in = new Scanner(System.in);

    public TextInterface(TextInterface previous) {
        this.previous = previous;
        actions = new ArrayList<Action>();
        addAction(0, ()->exit(), "Back");
    }

    public void addAction(int i, Runnable a, String t) {
        actions.add(new Action(i,a,t));
    }

    public Action getAction(int ID) {
        for(int i=0;i<actions.size();i++) {
            Action a = actions.get(i);
            if(a.ID==ID) {
                return a;
            }
        }
        return null;
    }

    public void exit() {
        previous.showMenu();
        active = false;
    }

    void showMenu() {
        active = true;
        System.out.println(header);
        for(int i=0;i<actions.size();i++) {
            Action a = actions.get(i);
            System.out.println(a.text+"\t"+a.ID);
        }
        System.out.println(footer);

        Scanner in = new Scanner(System.in);
        while(active) {
            Action a = a = getAction(in.nextInt());
            while (a == null) {
                System.out.println("Invalid Button. Try Again.");
                a = getAction(in.nextInt());
            }
            a.action.run();
        }
    }



    public static String nextLine() {
        return in.nextLine();
    }
    public static int nextInt() {
        return in.nextInt();
    }
    public static double nextDouble() {
        return in.nextDouble();
    }
}