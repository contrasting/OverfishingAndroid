package dating.overfishing.data;

import java.io.Serializable;

public class Conversation implements Serializable {

    private String mConversationID;
    private UserProfile mOtherProfile;
    private Long mLastMessageTime;
    private String mLastMessage;

    public Conversation(String conversationID, UserProfile otherProfile, Long lastMessageTime, String lastMessage) {
        mConversationID = conversationID;
        mOtherProfile = otherProfile;
        mLastMessageTime = lastMessageTime;
        mLastMessage = lastMessage;
    }

    public String getConversationID() {
        return mConversationID;
    }

    public Long getLastMessageTime() {
        return mLastMessageTime;
    }

    public String getOtherName() {
        return mOtherProfile.getName();
    }

    public String getLastMessage() {
        return mLastMessage;
    }

    public String getProfileImage() {
        return mOtherProfile.getProfileImages().get(0);
    }

    public UserProfile getOtherProfile() {
        return mOtherProfile;
    }
}
