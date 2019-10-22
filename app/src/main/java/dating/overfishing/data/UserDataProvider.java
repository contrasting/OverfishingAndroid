package dating.overfishing.data;

public interface UserDataProvider {

    void moveOnToNext();

    UserProfile getLast();

    void passUserWithId();

    void likeUserWithId();

    void pinUserWithId(); // can be done locally
}
