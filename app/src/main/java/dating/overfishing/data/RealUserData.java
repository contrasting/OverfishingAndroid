package dating.overfishing.data;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class RealUserData implements UserDataProvider {

    private Queue<UserProfile> mUserProfiles = new ArrayDeque<>();

    @Override
    public UserProfile moveOnToNext() {
        return null;
    }

    @Override
    public UserProfile getLast() {
        return null;
    }

    @Override
    public void passUserWithId(String id) {

    }

    @Override
    public void likeUserWithId(String id, String message) {

    }

    @Override
    public void pinUserWithId(String id) {

    }

    @Override
    public UserProfile getOwnProfile() {
        return null;
    }

    @Override
    public void getMoreUsers(Filters filters) {

    }

    @Override
    public UserProfile getPinnedUser() {
        return null;
    }

    @Override
    public List<UserProfile> getLikedUsers() {
        return null;
    }

    @Override
    public UserProfile getProfileFromId(String otherID) {
        return null;
    }
}
