
public class ClosedGroup extends Group {

    public ClosedGroup(String name, String description) {
        super(name, description);
    }

    public void printInfo(GUIConsole console) {
        super.printInfo(console);
    }

    /**
     * Adds a user to the group @Override the Group method
     * @param aUser
     * @param console
     */
    public void addToGroup(User aUser, GUIConsole console) {

        boolean isHeInGroupFlag = false;

        //Checks if the user is already in the group
        for (User u: arrayGroup) {
            if (u.equals(aUser)) {
                console.setTextArea(u.getName() + " is already enrolled in the Exam Solutions group.");
                isHeInGroupFlag = true;
                break;
            }
        }

        //If the group is empty the first user enters anyway
        if (this.arrayGroup.isEmpty()) {
            this.arrayGroup.add(aUser);
            console.setTextArea(aUser.getName() + " has successfully enrolled in group " + this.name);
        }
        //If the group is not empty
        else {
            //If the user is not in the group:
            if (!isHeInGroupFlag) {
                User tempUser;
                boolean mutualFriendFlag = false;
                int sizeOfArray = this.arrayGroup.size();

                //Checks if the user who wants to enter have a mutual friend in the group
                for (int i = 0; i < sizeOfArray; i++) {
                    tempUser = arrayGroup.get(i);

                    if (tempUser.isHeMyFriend(aUser)) {
                        this.arrayGroup.add(aUser);
                        console.setTextArea(aUser.getName() + " has successfully enrolled in group " + this.name);
                        mutualFriendFlag = true;
                        break;
                    }
                }

                //If the user don't have a mutual in the group
                if (!mutualFriendFlag)
                    console.setTextArea(aUser.getName() + " don't have a mutual friend, so she can't join the Exam Solution group.");
            }

        }
    }
}