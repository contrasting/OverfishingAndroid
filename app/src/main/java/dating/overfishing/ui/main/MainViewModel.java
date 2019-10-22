package dating.overfishing.ui.main;

import androidx.lifecycle.ViewModel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

import dating.overfishing.data.FakeUserData;
import dating.overfishing.data.UserDataProvider;
import dating.overfishing.data.UserProfile;

public class MainViewModel extends ViewModel {

    // TODO replace with real
    private UserDataProvider mDataProvider = new FakeUserData();

    public UserProfile getLatestUserProfile() {
        return mDataProvider.getLast();
    }

    public void passUser(String userId) {
        nextUser();
        mDataProvider.passUserWithId();
    }

    public void likeUser(String userId) {
        nextUser();
        mDataProvider.likeUserWithId();
    }

    public void pinUser(String userId) {
        nextUser();
        mDataProvider.pinUserWithId();
    }

    private void nextUser() {
        mDataProvider.moveOnToNext();
    }
}
