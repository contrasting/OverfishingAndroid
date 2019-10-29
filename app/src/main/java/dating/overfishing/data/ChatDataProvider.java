package dating.overfishing.data;

import androidx.paging.DataSource;

import java.util.List;

public interface ChatDataProvider {

    List<Conversation> getConversations();

    void sendMessage(String message);

    DataSource.Factory<String, Message> getDataSourceFactory();
}
