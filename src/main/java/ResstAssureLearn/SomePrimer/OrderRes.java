package ResstAssureLearn.SomePrimer;

public class OrderRes {

    public OrderRes(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OrderRes(int id, String text, String comment) {
        this.id = id;
        this.text = text;
        this.comment = comment;
    }

    private int id;
    private String text;
    private String comment;
}
