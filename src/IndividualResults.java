import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;

public class IndividualResults {
    private Vector<String> students = new Vector<String>();
    private Vector<String[]> answers = new Vector<String[]>();

    private Vector<Integer> scores = new Vector<Integer>();

    private ComboBox nameBox;
    private Stage window;
    private Scene scene;
    private Pane layout;
    private Text header;

    private  Label[] qna;
    private Label score;
    private Label percentage;
    private Label pass;
    private Button back;


    public IndividualResults(){
        loadResults();

    }
    public void display(){
        window = new Stage();
        layout= new Pane();
        qna = new Label[25];
        score = new Label();
        percentage = new Label();
        pass = new Label();
        back = new Button("Back");

        for (int i=0;i<25;i++){

            qna[i] = new Label("");
        }


        header = new Text("Individual Results");
        header.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
        header.setLayoutX(300);
        header.setLayoutY(30);

        nameBox = new ComboBox();

        for(int i =0;i<students.size();i++){
            nameBox.getItems().add(this.students.get(i));
        }
        //changes the option on the name box
        nameBox.setOnAction(e->{
            int selection = nameBox.getSelectionModel().getSelectedIndex();
            String[] currAns = answers.get(selection);
            int currScore = scores.get(selection);
            for(int i=0;i<25;i++){
                int currNumber = i+1;
                String[] currQues = currAns[i].split(":");
                switch(Integer.parseInt(currQues[0])){
                    case 0:
                        qna[i].setText(currNumber+". A");
                        break;
                    case 1:
                        qna[i].setText(currNumber+". B");
                        break;
                    case 2:
                        qna[i].setText(currNumber+". C");
                        break;
                    case 3:
                        qna[i].setText(currNumber+". D");
                        break;
                    default:
                        break;
                }

                if(currQues[0].equals(currQues[1])){
                    qna[i].setTextFill(Color.color(0,1,0));
                }
                else{
                    qna[i].setTextFill(Color.color(1,0,0));
                }
            }
            score.setText("Total Correct Answers: "+currScore);
            double percentageCalc = (((double) currScore)/25)*100;
            percentage.setText("Exam Score Percentage: "+ percentageCalc);

            // sets the color
            if(percentageCalc>70){
                pass.setText("Passed Exam");
                pass.setTextFill(Color.color(0,1,0));
            }
            else{
                pass.setText("Failed Exam");
                pass.setTextFill(Color.color(1,0,0));
            }

        });

        nameBox.setLayoutX(100);
        nameBox.setLayoutY(50);

        for(int i =0;i<10;i++){
            qna[i].setLayoutX(100);
            qna[i].setLayoutY(90+(i*20));
            layout.getChildren().add(qna[i]);
        }

        for(int i=10;i<20;i++){
            qna[i].setLayoutX(200);
            qna[i].setLayoutY(90+((i-10)*20));
            layout.getChildren().add(qna[i]);
        }

        for(int i=20;i<25;i++){
            qna[i].setLayoutX(300);
            qna[i].setLayoutY(90+(i-20)*20);
            layout.getChildren().add(qna[i]);
        }

        score.setLayoutX(700);
        score.setLayoutY(500);

        percentage.setLayoutX(700);
        percentage.setLayoutY(520);

        pass.setLayoutX(700);
        pass.setLayoutY(540);

        Button backButton = new Button("Back");
        backButton.setLayoutX(700);
        backButton.setLayoutY(560);
        backButton.setOnAction(e->{
            window.close();
            ResultsForm rf = new ResultsForm();
            rf.display();
        });


        layout.getChildren().addAll(header,nameBox,score,percentage,pass,backButton);
        scene = new Scene(layout,900,600);
        window.setScene(scene);
        window.setOnCloseRequest(e->{
            ResultsForm rf= new ResultsForm();
            rf.display();
        });
        window.show();

    }
    public void loadResults(){
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
