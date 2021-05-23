import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String name;
    private String email;
    private ArrayList<User> arrayListOfUserFriends = new ArrayList<>();
    private ArrayList<String> arrayListOfUserPost  = new ArrayList<>();

    public User(String name, String email, GUIConsole console) {
        this.name = name;
        this.email = email;
        console.setTextArea("User " + this.name + " has been created");
    }

    public User(){
    }


    /**
     * Check if the user: A is in a friend list of another user: B
     * @param aUser
     * @return
     */
    public boolean isHeMyFriend(User aUser) {
        boolean isHeMyFriendFlag = false;

        //Checks if the user(aUser) is not the same with this.user
        if(!this.equals(aUser)) {
            //Checks if the user (aUser) is in this.user friend list
            for(User u: this.arrayListOfUserFriends) {
                if(u.equals(aUser)) {
                    isHeMyFriendFlag = true;
                    break;
                }
            }
        }

        return isHeMyFriendFlag;
    }

    /**
     * Add user to another user friend list
     * @param aUser
     * @param console
     */
    public void addFriend(User aUser, GUIConsole console) {
        //Checks if the user is already in his friend list
        if(this.isHeMyFriend(aUser))
            console.setTextArea("You already have this friend in your list.");
        //Checks the user try to add himself in his friend list
        else {
            if(this.equals(aUser)) {
                console.setTextArea("You can't add yourself in your friend list.");
                return;
            }
            else{
                this.arrayListOfUserFriends.add(aUser);
                aUser.arrayListOfUserFriends.add(this);
            }
            console.setTextArea(this.getName() + " and " + aUser.getName() + " are now friends!");
        }
    }

    /**
     * Finds the mutual friends of two users
     * @param aUser
     * @param console
     * @return
     */
    public ArrayList<User> mutualFriends(User aUser, GUIConsole console) {
        ArrayList<User> temp = new ArrayList<>();

        int count = 0;
        String text = "";
        text += "Mutual friends of " + this.getName() + " and " + aUser.getName() + "\n";

        //Checks if the user has selected himself
        if(this.name.equals(aUser.getName()))
            console.setTextArea("You selected yourself!");
        else {
            //Find the mutual
            for(User u1 : this.arrayListOfUserFriends)
                for(User u2 : aUser.arrayListOfUserFriends) {
                    if(u1.getName().equals(u2.getName()) && u1.getEmail().equals(u2.getEmail())) {
                        temp.add(u2);
                        count ++;
                        text += count + ": " +u2.getName() + "\n";
                        break;
                    }
                }
            console.setTextArea(text);
        }

        return temp;
    }

    /**
     * It prints the friends of the user
     * @param console
     */
    public void printFriends(GUIConsole console) {
        String text = "";
        text += this.getName() + " is friend with: " + "\n";
        for(User u : this.arrayListOfUserFriends)
            text += u.getName() + "\n";

        console.setTextArea(text);
    }

    /**
     * Returns the list of user friends
     * @return
     */
    public ArrayList<User> getArrayListOfUserFriends() {
        return arrayListOfUserFriends;
    }

    public ArrayList<String> getArrayListOfUserPost() {
        return arrayListOfUserPost;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void addToArrayListOfUserPost(String message) {
        this.arrayListOfUserPost.add(message);
    }
}
