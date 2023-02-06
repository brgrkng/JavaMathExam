import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class QuestionStatistics {
    private Vector<String> students = new Vector<String>();
    private Vector<String[]> answers = new Vector<String[]>();

    private Vector<Integer> scores = new Vector<Integer>();

    private int[] questionScore;
    private Label[] qLabels;
    private Stage window;
    private Pane layout;
    private Scene scene;

    private Text header;


    public QuestionStatistics(){
        loadResults();

        questionScore = new int[25];
        for(int i=0;i<25;i++){
            questionScore[i] = 0;
        }
        for(int i =0;i<answers.size();i++){
            for(int j=0;j<questionScore.length;j++){
                String[] currAnswer = answers.get(i)[j].split(":");
                if(currAnswer[0].equals(currAnswer[1])){
                    questionScore[j]+=1;
                }
            }
        }
    }
    public void display(){
        window = new Stage();
        window.setTitle("Question Statistics");

        layout = new Pane();
        qLabels = new Label[25];

        header = new Text("Question Statistics");
        header.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
        header.setLayoutX(300);
        header.setLayoutY(30);

        layout.getChildren().add(header);

        for(int i =0;i<25;i++){
            qLabels[i]=new Label();
        }

        for(int i=0;i<questionScore.length;i++){
            double percentageCalc = ((double)questionScore[i]/answers.size())*100;
            qLabels[i].setText(i+". "+questionScore[i]+" correct answers ("+Math.round(percentageCalc)+"%)");
        }

        for(int i =0;i<10;i++){
            qLabels[i].setLayoutX(50);
            qLabels[i].setLayoutY(90+(i*20));
            layout.getChildren().add(qLabels[i]);
        }

        for(int i=10;i<20;i++){
            qLabels[i].setLayoutX(350);
            qLabels[i].setLayoutY(90+((i-10)*20));
            layout.getChildren().add(qLabels[i]);
        }

        for(int i=20;i<25;i++){
            qLabels[i].setLayoutX(700);
            qLabels[i].setLayoutY(90+(i-20)*20);
            layout.getChildren().add(qLabels[i]);
        }

        Button backButton = new Button("Back");
        backButton.setLayoutX(700);
        backButton.setLayoutY(560);
        backButton.setOnAction(e->{
            window.close();
            ResultsForm rf = new ResultsForm();
            rf.display();
        });
        layout.getChildren().add(backButton);

        scene = new Scene(layout,1100,600);
        window.setOnCloseRequest(e->{
            ResultsForm rf= new ResultsForm();
            rf.display();
        });
        window.setScene(scene);
        window.show();
    }
    private void loadResults(){
        try{
            File resultsFile = new File("studentResults.txt");
            int count = 0;
            Scanner sc = new Scanner(resultsFile);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(line==""){
                    break;
                }
                String[] features = line.split(",");
                students.add(features[0]);
                String[] answerHolder = new String[25];
                for(int i=1;i<26;i++){
                    answerHolder[i-1] = features[i];
                }
                answers.add(answerHolder);
            }
            for(int i=0;i<answers.size();i++){
                String[] currentStudent = answers.get(i);
                int currScore = 0;
                for(int j=0; j<25;j++){
                    String[] currQuestion = currentStudent[j].split(":");
                    if(currQuestion[0].equals(currQuestion[1])){
                        currScore++;
                    }
                }
                scores.add(currScore);
            }
        }
        catch(Exception e){

        }
    }
}
