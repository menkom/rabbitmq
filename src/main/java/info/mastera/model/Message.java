package info.mastera.model;

public class Message {

    private String message;
    private int count;

    public String getMessage() {
        return message;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Message setCount(int count) {
        this.count = count;
        return this;
    }
}
