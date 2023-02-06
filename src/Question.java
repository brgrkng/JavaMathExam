import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Question{
    private String question;
    private String cAns;
    private String[] oth = new String[4];

    private boolean correct = false;
    private int total = 0;
    private int rightAns;
    private int type = 0;

    Question(){
        this.question = "";

        this.oth[0] = "";
        this.oth[1] = "";
        this.oth[2] = "";
        this.oth[3] = "";
    }

    Question(String question, String answer1,String answer2,String answer3, String answer4){
        this.question = question;
        this.oth[0] = answer1;
        this.oth[1] = answer2;
        this.oth[2] = answer3;
        this.oth[3] = answer4;
    }

    public String getQuestion(){
        return (this.question);
    }
    public String[] getOthers(){
        return(this.oth);
    }

    public void setRightAns(int rightAns){
        this.rightAns = rightAns;
    }

    public int getRightAns(){
        return(this.rightAns);
    }
    public void isRight(){
        this.correct = true;
    }
    public void isWrong(){
        this.correct = false;
    }
    public void incrementTotal(){
        this.total++;
    }

    public boolean getCorrect(){
        return this.correct;
    }

    public int getTotal(){
        return this.total;
    }

    public int getType(){
        return type;
    }
    public String getImage(){
        return ("ERROR");
    }



}
