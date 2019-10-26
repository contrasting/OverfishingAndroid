package dating.overfishing.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dating.overfishing.data.FakeUserData;
import dating.overfishing.data.Filters;
import dating.overfishing.data.UserDataProvider;
import dating.overfishing.data.UserProfile;

public class MainViewModel extends AndroidViewModel implements UserDataProvider.Listener {

    // TODO need to check whether network connection

    private MutableLiveData<UserProfile> mCurrentProfile = new MutableLiveData<>();

    public MainViewModel(Application application) {
        super(application);
        mCurrentProfile.postValue(mDataProvider.getLast());
    }

    // TODO replace with real
    private UserDataProvider mDataProvider = new FakeUserData(this);

    public void passUser(String userId) {
        mDataProvider.passUserWithId(userId);
        nextUser();
    }

    public void likeUser(String userId) {
        mDataProvider.likeUserWithId(userId, null);
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
        mDataProvider.getMoreUsers(new Filters(getApplication()));
    }

    @Override
    public void onMoreUsersFound(UserProfile profile) {
        mCurrentProfile.setValue(profile);
    }

    public UserProfile getPinnedProfile() {
        return mDataProvider.getPinnedUser();
    }

    public List<UserProfile> getLikedUsers() {
        return mDataProvider.getLikedUsers();
    }
}
