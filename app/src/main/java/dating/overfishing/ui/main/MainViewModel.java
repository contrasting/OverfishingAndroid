package dating.overfishing.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dating.overfishing.data.FakeUserData;
import dating.overfishing.data.UserDataProvider;
import dating.overfishing.data.UserProfile;

public class MainViewModel extends ViewModel implements UserDataProvider.Listener {

    // TODO need to check whether network connection

    private MutableLiveData<UserProfile> mCurrentProfile = new MutableLiveData<>();

    public MainViewModel() {
        mCurrentProfile.postValue(mDataProvider.getLast());
    }

    // TODO replace with real
    private UserDataProvider mDataProvider = new FakeUserData(this);

    public void passUser(String userId) {
        mDataProvider.passUserWithId(userId);
        nextUser();
    }

    public void likeUser(String userId) {
        mDataProvider.likeUserWithId(userId);
        nextUser();
    }

    public void pinUser(String userId) {
        mDataProvider.pinUserWithId(userId);
        nextUser();
    }

    private void nextUser() {
        mCurrentProfile.setValue(mDataProvider.moveOnToNext());
    }

    public UserProfile getOwnProfile() {
        return mDataProvider.getOwnProfile();
    }

    // Note we do not expose MutableLiveData but only LiveData
    public LiveData<UserProfile> getCurrentProfile() {
        return mCurrentProfile;
    }

    public void refresh() {
        mDataProvider.getMoreUsers(null);
    }

    @Override
    public void onMoreUsersFound(UserProfile profile) {
        mCurrentProfile.setValue(profile);
    }
}
