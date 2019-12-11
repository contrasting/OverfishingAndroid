package dating.overfishing.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dating.overfishing.data.ChatDataProvider;
import dating.overfishing.data.Conversation;
import dating.overfishing.data.FakeChatData;
import dating.overfishing.data.FakeUserData;
import dating.overfishing.data.Filters;
import dating.overfishing.data.RealUserData;
import dating.overfishing.data.UserDataProvider;
import dating.overfishing.data.UserProfile;

public class MainViewModel extends AndroidViewModel implements UserDataProvider.Listener {

    private UserDataProvider mUserDataProvider = new RealUserData(this);
    private ChatDataProvider mChatDataProvider = new FakeChatData();

    // TODO need to check whether network connection

    private MutableLiveData<UserProfile> mCurrentProfile = new MutableLiveData<>();

    public MainViewModel(Application application) {
        super(application);
        // mCurrentProfile.postValue(mUserDataProvider.getLast());
        mUserDataProvider.getProfileFromId(RealUserData.ARGHA_ID);
    }

    public void passUser(String userId) {
        mUserDataProvider.passUserWithId(userId);
        nextUser();
    }

    public void likeUser(String userId) {
        mUserDataProvider.likeUserWithId(userId, null);
        nextUser();
    }

    public void pinUser(String userId) {
        mUserDataProvider.pinUserWithId(userId);
        nextUser();
    }

    private void nextUser() {
        mCurrentProfile.setValue(mUserDataProvider.moveOnToNext());
    }

    public UserProfile getOwnProfile() {
        return mUserDataProvider.getOwnProfile();
    }

    // Note we do not expose MutableLiveData but only LiveData
    public LiveData<UserProfile> getCurrentProfile() {
        return mCurrentProfile;
    }

    public void refresh() {
        mUserDataProvider.getMoreUsers(new Filters(getApplication()));
    }

    @Override
    public void onMoreUsersFound(UserProfile profile) {
        mCurrentProfile.setValue(profile);
    }

    @Override
    public void onUserWithIdFound(UserProfile profile) {
        mCurrentProfile.postValue(profile);
    }

    public UserProfile getPinnedProfile() {
        return mUserDataProvider.getPinnedUser();
    }

    public List<UserProfile> getLikedUsers() {
        return mUserDataProvider.getLikedUsers();
    }

    public List<Conversation> getChats() {
        return mChatDataProvider.getConversations();
    }

    public void getProfileFromId(String otherID) {
        mUserDataProvider.getProfileFromId(otherID);
    }
}
