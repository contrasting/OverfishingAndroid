package dating.overfishing.data;

import java.io.Serializable;

public class Conversation implements Serializable {

    private String mConversationID;
    private String mOtherID;
    private Long mLastMessageTime;
    private String mOtherName;
    private String mLastMessage;
    private String mProfileImage;

    public Conversation(String conversationID, String otherID, Long lastMessageTime, String otherName, String lastMessage, String profileImage) {
        mConversationID = conversationID;
        mOtherID = otherID;
        mLastMessageTime = lastMessageTime;
        mOtherName = otherName;
        mLastMessage = lastMessage;
        mProfileImage = profileImage;
    }

    public String getConversationID() {
        return mConversationID;
    }

    public Long getLastMessageTime() {
        return mLastMessageTime;
    }

    public String getOtherName() {
        return mOtherName;
    }

    public String getLastMessage() {
        return mLastMessage;
    }

    public String getProfileImage() {
        return mProfileImage;
    }

    public String getOtherID() {
        return mOtherID;
    }
}
