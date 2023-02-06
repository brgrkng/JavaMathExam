import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
public class QuestionsForm {
    private Text name;
    private Text nationality;

    private Question questions[];

    private int time = 180;

    private Timer timer;

    private TimerTask timerTask;

    private int questionNumber = 0;

    private Text headerText;
    private Text questionNumberLabel;
    private Text question;
    private ToggleGroup group;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Label showTime;
    private ImageView imView;
    private Stage window;
    private Button next = new Button("Next");

    private int[] answers;
    QuestionsForm(Question[] questions,Text name, Text nationality){
        this.name = name;
        this.nationality = nationality;
        this.questions = questions;

        answers = new int[25];
        for(int i=0;i<25;i++){
            answers[i] = 4;
        }

        showTime = new Label();
        timer= new Timer();
        timerTask = new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(()->{
                    if(time==0){
                        saveResults();
                        window.close();
                        timer.cancel();
                        timer.purge();
                        ResultsForm rf = new ResultsForm();
                        rf.display();
                    }
                    else if(time==11){
                        Media sound = new Media(new File("data/10secondCountdown.mp3").toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.play();
                    }
                    else if(time%30==0){
                        Media sound = new Media(new File("data/clockTicking.mp3").toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.play();
                    }
                    time--;
                    showTime.setText("Time Remaining: "+getTimeString(time));
                });
            }
        };
        timer.schedule(timerTask, 0 ,1000);
    }

    public void display(){
        //displays the window for class
        window = new Stage();
        window.setTitle("Questions Form");

        Text headerText = new Text("Mathematics Exam Form");
        headerText.setFont(Font.font("Times New Roman",FontWeight.BOLD,30));

        headerText.setLayoutX(300);
        headerText.setLayoutY(30);

        name.setLayoutX(0);
        name.setLayoutY(75);
        nationality.setLayoutX(0);
        nationality.setLayoutY(95);

        showTime.setLayoutX(800);
        showTime.setLayoutY(50);

        Pane layout = new Pane();


        Text questionNumberLabel = new Text("Question Number "+(questionNumber+1));
        questionNumberLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,17));

        questionNumberLabel.setLayoutX(200);
        questionNumberLabel.setLayoutY(150);


        Text question = new Text(questions[questionNumber].getQuestion());
        question.setFont( Font.font("Times New Roman",17));

        question.setLayoutX(200);
        question.setLayoutY(200);

        String placeholdertxt ="file:data/plc.png" ;
        int buttonX = 200;

        if(!questions[questionNumber].getImage().equals("ERROR")){
            placeholdertxt = "file:data/"+questions[questionNumber].getImage();
            buttonX = 700;
        }
        else{
            buttonX = 200;
        }

        Image placeholder = new Image(placeholdertxt);
        imView = new ImageView(placeholder);
        imView.setLayoutX(200);
        imView.setLayoutY(220);
        imView.setFitHeight(300);
        imView.setFitWidth(300);

        ImageView[] qviews = new ImageView[4];




        next.setLayoutX(700);
        next.setLayoutY(500);

        ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton(questions[questionNumber].getOthers()[0]);
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rb1.setLayoutX(buttonX);
        rb1.setLayoutY(270);

        RadioButton rb2 = new RadioButton(questions[questionNumber].getOthers()[1]);
        rb2.setToggleGroup(group);
        rb2.setLayoutX(buttonX);
        rb2.setLayoutY(290);

        RadioButton rb3 = new RadioButton(questions[questionNumber].getOthers()[2]);
        rb3.setToggleGroup(group);
        rb3.setLayoutX(buttonX);
        rb3.setLayoutY(310);

        RadioButton rb4 = new RadioButton(questions[questionNumber].getOthers()[3]);
        rb4.setToggleGroup(group);
        rb4.setLayoutX(buttonX);
        rb4.setLayoutY(330);
        if(questions[questionNumber].getType()==2){
            Image[] qpics = new Image[4];
            qpics[0] = new Image("file:data/"+questions[questionNumber].getOthers()[0]);
            qpics[1] = new Image("file:data/"+questions[questionNumber].getOthers()[1]);
            qpics[2] = new Image("file:data/"+questions[questionNumber].getOthers()[2]);
            qpics[3] = new Image("file:data/"+questions[questionNumber].getOthers()[3]);

            qviews = new ImageView[4];

            qviews[0] = new ImageView(qpics[0]);
            qviews[0].setFitHeight(100);
            qviews[0].setFitWidth(100);
            qviews[0].setLayoutX(200);
            qviews[0].setLayoutY(290);

            qviews[1] = new ImageView(qpics[1]);
            qviews[1].setFitHeight(100);
            qviews[1].setFitWidth(100);
            qviews[1].setLayoutX(330);
            qviews[1].setLayoutY(290);

            qviews[2] = new ImageView(qpics[2]);
            qviews[2].setFitHeight(100);
            qviews[2].setFitWidth(100);
            qviews[2].setLayoutX(460);
            qviews[2].setLayoutY(290);

            qviews[3] = new ImageView(qpics[3]);
            qviews[3].setFitHeight(100);
            qviews[3].setFitWidth(100);
            qviews[3].setLayoutX(590);
            qviews[3].setLayoutY(290);


            int buttonY= 339;

            rb1.setText("A");
            rb1.setLayoutX(180);
            rb1.setLayoutY(buttonY);

            rb2.setText("B");
            rb2.setLayoutX(310);
            rb2.setLayoutY(buttonY);

            rb3.setText("C");
            rb3.setLayoutX(440);
            rb3.setLayoutY(buttonY);

            rb4.setText("D");
            rb4.setLayoutX(570);
            rb4.setLayoutY(buttonY);
        }
        Button backButton = new Button("Back");
        backButton.setLayoutX(650);
        backButton.setLayoutY(500);

        if(questionNumber!=0){
            layout.getChildren().add(backButton);
        }
        backButton.setOnAction(e->{
            answers[questionNumber]=5;
            questionNumber--;
            window.close();
            display();
        });


        if (questions[questionNumber].getType() == 0) {
            layout.getChildren().addAll(name,nationality,headerText,showTime,questionNumberLabel,question,rb1,rb2,rb3,rb4,next);
        }
        else if(questions[questionNumber].getType()==1){
            layout.getChildren().addAll(name,nationality,headerText,showTime,questionNumberLabel,question,imView,rb1,rb2,rb3,rb4,next);
        }
        else{
            layout.getChildren().addAll(name,nationality,headerText,showTime,questionNumberLabel,question,imView,rb1,qviews[0],rb2,qviews[1],rb3,qviews[2],rb4,qviews[3],next);
        }


        Scene scene  = new Scene(layout,950,600);
        window.setScene(scene);
        window.setOnCloseRequest(e->{
            e.consume();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Cannot Exit from exam");
            a.show();
        });
        window.show();
        next.setOnAction(e->{
            timer.cancel();
            timer.purge();
            // actions for the next button
            // changes the window each time a question is progressed
            answers[questionNumber] = group.getToggles().indexOf(group.getSelectedToggle());

            if(group.getToggles().indexOf(group.getSelectedToggle())==questions[questionNumber].getRightAns()){

                questions[questionNumber].isRight();
            }
            else{
                questions[questionNumber].isWrong();
            }
            if(questionNumber<questions.length-1){
                questionNumber++;
                if(questionNumber==questions.length-1){

                    next.setText("Confirm");
                }
                window.close();
                display();
            }
            else{
                window.close();
                saveResults();
                ResultsForm rf = new ResultsForm();
                rf.display();
            }
        });
    }
    private String getTimeString(int time){
        int minutes = time/60;
        int seconds = time%60;
        String finalOut;
        if(seconds == 0){
            finalOut = minutes+":00";
        }
        else{
            finalOut = minutes +":"+seconds;
        }
        return finalOut;
    }

    private void saveResults() {
        try{
            String name = this.name.getText().split(": \t\t")[1];
            File answersFile = new File("studentResults.txt");
            FileWriter fr = new FileWriter(answersFile,true);
            String line = name;
            for(int i =0;i<25;i++){
                line+=","+answers[i]+":"+questions[i].getRightAns();
            }
            fr.write(line+"\n");
            fr.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
