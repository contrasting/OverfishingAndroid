package dating.overfishing.ui.main.chats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dating.overfishing.data.ChatDataProvider;
import dating.overfishing.data.FakeChatData;

public class ChatViewModel extends ViewModel {

    private ChatDataProvider mChatDataProvider = new FakeChatData();

    private MutableLiveData<Boolean> mIsTypingLiveData = new MutableLiveData<>(true);

    public void sendMessage(String message) {
        mChatDataProvider.sendMessage(message);
    }

    public LiveData<Boolean> getIsTypingLiveData() {
        return mIsTypingLiveData;
    }

}
