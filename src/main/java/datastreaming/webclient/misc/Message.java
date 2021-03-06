package datastreaming.webclient.misc;

public class Message {

    public interface Type{
        String SUCCESS = "success";
        String WARNING = "warning";
        String ERROR = "danger";
    }

    private String text;

    private String type;

    public Message(String text, String type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
