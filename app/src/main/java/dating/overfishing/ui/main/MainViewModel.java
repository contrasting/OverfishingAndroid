package dating.overfishing.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dating.overfishing.data.FakeUserData;
import dating.overfishing.data.UserDataProvider;
import dating.overfishing.data.UserProfile;

public class MainViewModel extends ViewModel {

    private MutableLiveData<UserProfile> mCurrentProfile = new MutableLiveData<>();

    public MainViewModel() {
        mCurrentProfile.postValue(mDataProvider.getLast());
    }

    // TODO replace with real
    private UserDataProvider mDataProvider = new FakeUserData();

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

    // Note we do not expose MutableLiveData but only the super pointer
    public LiveData<UserProfile> getCurrentProfile() {
        return mCurrentProfile;
    }

    public void refresh() {

    }
}
