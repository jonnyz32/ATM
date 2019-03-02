
import java.lang.Runnable;
import java.util.ArrayList;
import java.util.Scanner;

class TextInterface {

    String header;
    String footer;
    ArrayList<Action> actions;

    public TextInterface() {
        actions = new ArrayList<Action>();
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

    public void showMenu() {
        System.out.println(header);
        for(int i=0;i<actions.size();i++) {
            Action a = actions.get(i);
            System.out.println(a.text+"\t"+a.ID);
        }
        System.out.println(footer);

        Scanner in = new Scanner(System.in);
        Action a = a = getAction(in.nextInt());
        while(a==null) {
            System.out.println("Invalid Button. Try Again.");
            a = getAction(in.nextInt());
        }
        a.action.run();
    }



    //DIAGNOSTIC METHODS. TEMPORARY.
    String s;
    public void test() {
        System.out.println(s);
    }

    public static void main(String[] args) {
        TextInterface t = new TextInterface();
        t.header = "HeaderText";
        t.footer = "FooterText";
        t.s = "old";
        t.addAction(1, ()->t.test(),"TestOptionText");//Important note: Functions cannot recieve inputs.
        t.s = "new";
        t.showMenu();
    }
}