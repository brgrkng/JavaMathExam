
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class ExamForm  {

    //variables for the scene

    private Text name;
    private Text nationality;
    private Scene scene;
    private Timer timer;
    private TimerTask timerTask;
    private int time = 180;
    private BorderPane layout;

    private   Question[] questions;

    public ExamForm(String name, String nationality){
        this.name = new Text("  Name: \t\t"+ name);
        this.name.setFont(Font.font("Times New Roman", FontWeight.BOLD,17));
        this.nationality = new Text("  Nationality: \t"+ nationality);
        this.nationality.setFont(Font.font("Times New Roman", FontWeight.BOLD,17));
    }
    public void display(){
        // the display function that draws the scene in the window
        Stage window = new Stage();
        window.setTitle("Exam Form");

        layout = new BorderPane();



        VBox details = new VBox(3);
        details.getChildren().addAll(name,nationality);

        StackPane header = new StackPane();
        Text headerText = new Text("Mathematics Exam Form");
        headerText.setFont(Font.font("Times New Roman",FontWeight.BOLD,30));
        header.getChildren().add(headerText);

        VBox confirmations = new VBox(10);
        Text confirmText = new Text();
        confirmText.setText("\n\nYour details are displayed in the above left corner.\n" +
                "Please confirm these are correct\n"+
                "\nThis exam consists of 25 multiple choice questions based on A Level Mathematics.\n"+
                "\nYou will have 3 minutes to answer.\n"+
                "\nCheck the confirmation box and click start to begin your Exam\n\n\n");
        confirmText.setFont(Font.font("Times New Roman",17));
        confirmations.getChildren().add(confirmText);
        confirmText.setTextAlignment(TextAlignment.JUSTIFY);
        confirmations.setAlignment(Pos.CENTER_LEFT);

        CheckBox checkBox = new CheckBox("I Understand");
        confirmations.getChildren().add(checkBox);

        Label wrong = new Label("");
        confirmations.getChildren().add(wrong);


        Button start = new Button("Start");
        start.setOnAction(e->{
            if(checkBox.isSelected()){


                questions = new Question[25];
                readQuestions();

                QuestionsForm qf = new QuestionsForm(questions,name,nationality);
                qf.display();
                window.close();


            }
            else{
                wrong.setTextFill(Color.color(1,0,0));
                wrong.setText("Please tick the checkbox");
            }
        });



        Button cancel = new Button("Cancel");
        cancel.setOnAction(e->{
            window.close();
            ApplicantForm af = new ApplicantForm();
            af.display();
        });

        HBox buttons = new HBox(5);
        buttons.getChildren().addAll(start,cancel);
        buttons.setAlignment(Pos.CENTER_RIGHT);
        confirmations.getChildren().add(buttons);

        details.setPrefWidth(20);
        header.setPrefHeight(100);
        confirmations.setMinHeight(400);
        confirmations.setMaxHeight(200);
        buttons.setMaxWidth(600);
        buttons.setMinHeight(20);
        layout.setLeft(details);
        layout.setCenter(confirmations);
        layout.setTop(header);

        layout.setAlignment(confirmations,Pos.TOP_CENTER);
        scene = new Scene(layout,950,600);
        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }


    private void readQuestions(){
        // This function reads the questions files and enters it into the appropriate array
        try{
            File questionsFile = new File("./data","questions.txt");
            File typeBFile= new File("./data","typeB.txt");
            File typeCFile = new File("./data","typeC.txt");
            Scanner sc = new Scanner(questionsFile);
            int count = 0;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] features = line.split(";");
                if(Integer.parseInt(features[0])==0){
                    questions[count] = new Question(features[1],features[2],features[3],features[4],features[5]);
                    questions[count].setRightAns(Integer.parseInt(features[6]));
                }
                count++;
            }
            sc.close();
            sc = new Scanner(typeBFile);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] features = line.split(";");
                if(Integer.parseInt(features[0])==1){
                    questions[count] = new QTypeB(features[1],features[2],features[3],features[4],features[5],features[6]);
                    questions[count].setRightAns(Integer.parseInt(features[7]));
                }
                count++;
            }
            sc.close();
            sc = new Scanner(typeCFile);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] features = line.split(";");
                if(Integer.parseInt(features[0])==2){
                    questions[count] = new QTypeC(features[1],features[2],features[3],features[4],features[5]);
                    questions[count].setRightAns(Integer.parseInt(features[6]));
                }
                count++;
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
