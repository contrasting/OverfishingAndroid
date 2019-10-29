package dating.overfishing.data;

public class Message {

    private String key;
    private String message;
    private boolean isMe;
    private Long time;

    public Message(String key, String message, boolean isMe, Long time) {
        this.key = key;
        this.message = message;
        this.isMe = isMe;
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public boolean isMe() {
        return isMe;
    }

    public Long getTimestamp() {
        return time;
    }
}
