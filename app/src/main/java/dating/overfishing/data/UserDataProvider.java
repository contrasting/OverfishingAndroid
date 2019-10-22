package dating.overfishing.data;

public interface UserDataProvider {

    // if no action, retrieve the last
    UserProfile getLast();

    // if action from user, retrieve the next
    UserProfile moveOnToNext();

    void passUserWithId(String id);

    void likeUserWithId(String id);

    void pinUserWithId(String id); // can be done locally
}
