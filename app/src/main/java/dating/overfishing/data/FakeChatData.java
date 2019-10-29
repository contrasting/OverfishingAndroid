package dating.overfishing.data;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;

import java.util.ArrayList;
import java.util.List;

import static dating.overfishing.data.FakeUserData.getArgha;
import static dating.overfishing.data.FakeUserData.getFred;
import static dating.overfishing.data.FakeUserData.getLegoi;

public class FakeChatData implements ChatDataProvider {
    @Override
    public List<Conversation> getConversations() {
        List<Conversation> conversations = new ArrayList<>();
        conversations.add(new Conversation("abc",
                getLegoi(), 1572120745000L,
                "Hi I miss you"
        ));
        conversations.add(new Conversation("abc",
                getFred(), 1572120775000L,
                "lol hahaha you suck"
        ));
        conversations.add(new Conversation("abc",
                getArgha(), 1572120775000L,
                "Hmm Indian girls yeah"
        ));
        return conversations;
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public DataSource.Factory<String, Message> getDataSourceFactory() {
        return new FakeMessageDataSourceFactory();
    }

    /**
     * https://developer.android.com/reference/androidx/paging/DataSource
     *
     * If you have more granular update signals, such as a network API signaling an update to a
     * single item in the list, it's recommended to load data from network into memory. Then
     * present that data to the PagedList via a DataSource that wraps an in-memory snapshot.
     * Each time the in-memory copy changes, invalidate the previous DataSource, and a new one
     * wrapping the new state of the snapshot can be created.
     */

    private class FakeMessageDataSource extends ItemKeyedDataSource<String, Message> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<Message> callback) {
            List<Message> messages =  new ArrayList<>();
            messages.add(new Message("abc", "hi it's me", false, 1572120745000L));
            messages.add(new Message("bcd", "lol ok", false, 1572120755000L));
            messages.add(new Message(null, "wtf man", true, 1572120765000L));
            messages.add(new Message(null, "you suck", true, 1572120775000L));

            callback.onResult(messages);
        }

        @Override
        public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<Message> callback) {
            // ignore
        }

        @Override
        public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<Message> callback) {
            // should really be this one, load before params.key
        }

        @NonNull
        @Override
        public String getKey(@NonNull Message message) {
            return message.getKey();
        }
    }

    private class FakeMessageDataSourceFactory extends DataSource.Factory<String, Message> {

        @NonNull
        @Override
        public DataSource<String, Message> create() {
            return new FakeMessageDataSource();
        }
    }
}
