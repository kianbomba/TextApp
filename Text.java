package operation.model;
import java.util.Date;
public class Text {
    private String text;
    private Date Date;
    private final String author = "Kian Nguyen";

    public Text(String text){
        this.text = text;
        this.Date = new Date();
    }


    public String getText(){
        return this.text;
    }

    public void setText(String text){
        this.text = text;
    }

    public Date getlocalDate(){
        return this.Date;
    }

    @Override
    public String toString(){
        return String.format("Message: \t%s \nDate: \t\t%s \nBy: \t\t%s" ,text, Date, author);
    }

}
