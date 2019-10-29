package dating.overfishing.ui.main.chats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import dating.overfishing.data.ChatDataProvider;
import dating.overfishing.data.FakeChatData;
import dating.overfishing.data.Message;

public class ChatViewModel extends ViewModel {

    private ChatDataProvider mChatDataProvider = new FakeChatData();

    private MutableLiveData<Boolean> mIsTypingLiveData = new MutableLiveData<>(true);

    // paged list is content immutable, cannot use MutableLiveData
    // https://developer.android.com/topic/libraries/architecture/paging/ui
    public LiveData<PagedList<Message>> mMessageData;

    public void sendMessage(String message) {
        mChatDataProvider.sendMessage(message);
    }

    public LiveData<Boolean> getIsTypingLiveData() {
        return mIsTypingLiveData;
    }

    public ChatViewModel() {
        PagedList.Config pagingConfig = new PagedList.Config.Builder()
                .setPageSize(50)
                .setPrefetchDistance(150)
                .setEnablePlaceholders(false)
                .build();

        mMessageData = new LivePagedListBuilder<>(mChatDataProvider.getDataSourceFactory(),
                pagingConfig).build();
    }

}
