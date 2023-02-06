public class QTypeC extends Question{
    private int type = 2;

    QTypeC(String question, String answer1,String answer2,String answer3, String answer4){
        super(question, answer1, answer2, answer3, answer4);
    }
    public int getType(){
        return 2;
    }
}
