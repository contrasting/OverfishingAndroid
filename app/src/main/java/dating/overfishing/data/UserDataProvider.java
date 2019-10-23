package dating.overfishing.data;

public interface UserDataProvider {

    // if no action, retrieve the last
    UserProfile getLast();

    // if action from user, retrieve the next
    UserProfile moveOnToNext();

    // network call
    void passUserWithId(String id);

    // network call
    void likeUserWithId(String id);

    // can be done locally
    void pinUserWithId(String id);

    UserProfile getOwnProfile();

    // network call
    void getMoreUsers(Filter filter);

    // callback to host
    interface Listener {
        void onMoreUsersFound(UserProfile profile);
    }
}