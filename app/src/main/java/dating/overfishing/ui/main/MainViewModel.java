package dating.overfishing.ui.main;

import androidx.lifecycle.ViewModel;

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
        mDataProvider.passUserWithId(userId);
    }

    public void likeUser(String userId) {
        mDataProvider.likeUserWithId(userId);
    }

    public void pinUser(String userId) {
        mDataProvider.pinUserWithId(userId);
    }

    public UserProfile nextUser() {
        return mDataProvider.moveOnToNext();
    }
}
