public class QTypeB extends Question{
    private int type = 1;
    String image;

    QTypeB(String question, String imagePath, String answer1,String answer2,String answer3, String answer4){
        super(question,answer1,answer2,answer3,answer4);
        this.image = imagePath;
    }

    public String getImage(){
        return this.image;
    }

    public int getType(){
        return 1;
    }

}
