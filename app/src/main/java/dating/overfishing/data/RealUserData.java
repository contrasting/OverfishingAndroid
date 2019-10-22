package dating.overfishing.data;

import java.util.ArrayDeque;
import java.util.Queue;

public class RealUserData implements UserDataProvider {

    private Queue<UserProfile> mUserProfiles = new ArrayDeque<>();

    @Override
    public void moveOnToNext() {

    }

    @Override
    public UserProfile getLast() {
        return null;
    }

    @Override
    public void passUserWithId() {

    }

    @Override
    public void likeUserWithId() {

    }

    @Override
    public void pinUserWithId() {

    }
}
