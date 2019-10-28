package dating.overfishing.data;

import java.util.ArrayList;
import java.util.List;

public class FakeChatData implements ChatDataProvider {
    @Override
    public List<Conversation> getConversations() {
        List<Conversation> conversations = new ArrayList<>();
        conversations.add(new Conversation("abc",
                1572120745000L,
                "Olivia",
                "Hi I miss you",
                "https://a.wattpad.com/cover/60596436-352-k319779.jpg"));
        conversations.add(new Conversation("abc",
                1572120775000L,
                "Adam",
                "lol hahaha you suck",
                "https://www.pitango.com//data/upl/team/Ezekiel-Guy-370X550_strip.png"));
        conversations.add(new Conversation("abc",
                1572120775000L,
                "Argha",
                "Hmm Indian girls yeah",
                "https://scontent-lht6-1.xx.fbcdn.net/v/t1.0-1/p200x200/67745945_10219547573225969_7196020350700748800_n.jpg?_nc_cat=102&_nc_oc=AQlLKJ5ZcqUfpLT34Ud7p6MyH3EYM_jyP9jLge40Ijcpun_Op1Q5F6V9gBcRGlm4JNeQ_bHKlKX-6io4csU0SmSF&_nc_ht=scontent-lht6-1.xx&oh=e502417b7c097acf25d0a68800c8a11d&oe=5E2630C3"));
        return conversations;
    }
}
