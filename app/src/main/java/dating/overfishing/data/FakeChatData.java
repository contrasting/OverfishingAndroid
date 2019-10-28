package dating.overfishing.data;

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
}
