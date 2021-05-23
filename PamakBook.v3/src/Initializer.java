import java.util.ArrayList;

public class Initializer {

    private static Group g1       = new Group("WebGurus","A group for web passionates");
    private static ClosedGroup g2 = new ClosedGroup("ExamSolutions","Solutions to common exam questions");

    public static ArrayList<User> initialize(GUIConsole console) {

        ArrayList<User> pamakBookUsers = new ArrayList<>();

        User u1 = new User("Makis", "it1698@uom.edu.gr", console);
        User u2 = new User("Petros", "it1624@uom.edu.gr", console);
        User u3 = new User("Maria", "it16112@uom.edu.gr", console);
        User u4 = new User("Gianna", "it16133@uom.edu.gr", console);
        User u5 = new User("Nikos", "it1658@uom.edu.gr", console);
        User u6 = new User("Babis", "it16104@uom.edu.gr", console);

        u2.addToArrayListOfUserPost("06/01/2017 17:14:56, Petros \nΠολύ χιόνισε σήμερα!");
        u3.addToArrayListOfUserPost("05/01/2017 16:15:42, Maria \nΞέρουμε αν θα λειτουργήσει το Πανεπιστήμιο με τέτοιο κρύο;");
        u5.addToArrayListOfUserPost("07/01/2017 16:15:15, Nikos \nΕπιτέλους είδαμε άσπρη μέρα");

        pamakBookUsers.add(u1);
        pamakBookUsers.add(u2);
        pamakBookUsers.add(u3);
        pamakBookUsers.add(u4);
        pamakBookUsers.add(u5);
        pamakBookUsers.add(u6);

        return pamakBookUsers;
    }

    public static Group getGroup1() {
        return g1;
    }

    public static Group getGroup2() {
        return g2;
    }
}
