package resstAssureLearn.SomePrimer;

public class OrderReq {

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

    public OrderReq(String text, String comment) {
        this.text = text;
        this.comment = comment;
    }

    public OrderReq(){

    }

    private String text;
    private String comment;



}
