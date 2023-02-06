/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nafee
 */
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
import java.util.Collections;
import javafx.scene.shape.Rectangle;

import java.io.File;
import static java.lang.Math.sqrt;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;
import javafx.scene.Group;

public class ExamStatistics {

    Vector<String> students = new Vector<String>();
    Vector<String[]> answers = new Vector<String[]>();

    Vector<Integer> scores = new Vector<Integer>();

    Stage window;
    Scene scene;
    Pane layout;
    Text header;

    // min, max, standard deviation, passing rate
    String min, max, std, mean, passingR;
    Label lblmin, lblmax, lblstd, lblmean, lblpassingR;
    Label minAns, maxAns, stdAns, meanAns, passingRAns;

    float variance;
    public ExamStatistics() {
        loadResults();
    }

    public void display() {
        window = new Stage();
        layout = new Pane();
        header = new Text();

        // Instantiating statistical labels
        lblmin = new Label();
        lblmax = new Label();
        lblstd = new Label();
        lblmean = new Label();
        lblpassingR = new Label();

        minAns = new Label();
        maxAns = new Label();
        stdAns = new Label();
        meanAns = new Label();
        passingRAns = new Label();

        //  Grey rectangle background section
        Rectangle greyBackground = new Rectangle();
        //Setting the properties of the rectangle
        greyBackground.setX(145.0f);
        greyBackground.setY(125.0f);
        greyBackground.setWidth(635.0f);
        greyBackground.setHeight(190.0f);

        //Setting the height and width of the arc
        greyBackground.setArcWidth(30.0);
        greyBackground.setArcHeight(20.0);

        greyBackground.setFill(Color.color(0.83, 0.83, 0.83));

        // Header section
        header = new Text("Exam Statistics");
        header.setFont(Font.font("Helvatica", 45));
        header.setLayoutX(313);
        header.setLayoutY(50);

        // min section
        lblmin.setText("Min");
        lblmin.setFont(Font.font("Helvatica", 33));
        lblmin.setLayoutX(185);
        lblmin.setLayoutY(250);

        min = Collections.min(scores).toString();
        minAns.setText(min);
        minAns.setFont(Font.font("Helvatica", FontWeight.NORMAL, 55));
        minAns.setLayoutX(200);
        minAns.setLayoutY(150);

        // max section
        lblmax.setText("Max");
        lblmax.setFont(Font.font("Helvatica", 33));
        lblmax.setLayoutX(348);
        lblmax.setLayoutY(250);

        max = Collections.max(scores).toString();
        maxAns.setText(max);
        maxAns.setFont(Font.font("Helvatica", FontWeight.NORMAL, 55));
        maxAns.setLayoutX(350);
        maxAns.setLayoutY(150);

        // mean section
        lblmean.setText("Mean");
        lblmean.setFont(Font.font("Helvatica", 33));
        lblmean.setLayoutX(490);
        lblmean.setLayoutY(250);

        int sumScores = 0;
        int lengthScores = 0;
        for (int i = 0; i < scores.size(); i++) {
            sumScores += scores.get(i);
        }
        mean = String.valueOf(sumScores / (scores.size()));
        meanAns.setText(mean);
        meanAns.setFont(Font.font("Helvatica", FontWeight.NORMAL, 55));
        meanAns.setLayoutX(505);
        meanAns.setLayoutY(150);

        // std section
        lblstd.setText("Std");
        lblstd.setFont(Font.font("Helvatica", 33));
        lblstd.setLayoutX(660);
        lblstd.setLayoutY(250);

        // std = square root of ((summation of score - mean score)^2 / lengthScores - 1))
        // beginning of calculation
        int summation = 0;
        for (int i = 0; i < scores.size(); i++) {
            int element = scores.get(i);
            summation += Math.pow(element - Integer.parseInt(mean), 2);
        }

        if(scores.size()==1){
            std = "N/A";
        }
        else{
            variance = summation / (scores.size()-1);
            std = String.valueOf(roundTwoDecimal((Math.sqrt(variance) * 100.0) / 100.0));
        }
        // end of calculation

        stdAns.setText(std);
        stdAns.setFont(Font.font("Helvatica", FontWeight.NORMAL, 55));
        stdAns.setLayoutX(650);
        stdAns.setLayoutY(150);

        // Passing Rate section
        lblpassingR.setText("Passing Rate");
        lblpassingR.setFont(Font.font("Helvatica", FontWeight.BOLD,  39));
        lblpassingR.setLayoutX(353);
        lblpassingR.setLayoutY(490);

        float pass = 0; // float due to division function returning int value
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) >= 18) {
                pass += 1;
            }
        }

        passingR = String.valueOf(Math.round(pass / scores.size() * 100));
        double dPassingR = Double.parseDouble(passingR);

        // Color coding depending on the passing rate
        if (dPassingR >= 90) {
            passingRAns.setTextFill(Color.color(0, 0.9, 0.3)); // dark green
        } else if (dPassingR >= 80) {
            passingRAns.setTextFill(Color.color(0.5, 1, 0.67)); // light green
        } else if (dPassingR >= 70) {
            passingRAns.setTextFill(Color.color(0.67, 0.9, 0)); // light yellow
        } else if (dPassingR >= 50) {
            passingRAns.setTextFill(Color.color(1, 0.6, 0)); // dark yellow
        } else {
            passingRAns.setTextFill(Color.color(1, 0.3, 0.3)); // red
        }

        passingRAns.setText(passingR + "%");
        passingRAns.setFont(Font.font("Helvatica", FontWeight.BOLD, 65));
        passingRAns.setLayoutX(405);
        passingRAns.setLayoutY(380);

        //Back button
        Button backButton = new Button("Back");
        backButton.setLayoutX(700);
        backButton.setLayoutY(560);
        backButton.setOnAction(e->{
            window.close();
            ResultsForm rf = new ResultsForm();
            rf.display();
        });


        // Finalizing section
        layout.getChildren().addAll(header, greyBackground, lblmin, lblmax, lblstd, lblmean, lblpassingR, minAns, maxAns, meanAns, stdAns, passingRAns,backButton);
        scene = new Scene(layout, 900, 600);
        window.setScene(scene);
        window.setOnCloseRequest(e->{
            ResultsForm rf= new ResultsForm();
            rf.display();
        });
        window.show();

    }

    public double roundTwoDecimal(double n) {
        return Math.round(n * 100) / 100;
    }

    public void loadResults() {
        try {
            File resultsFile = new File("studentResults.txt");
            int count = 0;
            Scanner sc = new Scanner(resultsFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line == "") {
                    break; // to break when there's no more line
                }
                String[] features = line.split(",");
                students.add(features[0]);
                String[] answerHolder = new String[25];
                for (int i = 1; i < 26; i++) {
                    answerHolder[i - 1] = features[i];
                }
                answers.add(answerHolder);
            }
            for (int i = 0; i < answers.size(); i++) {
                String[] currentStudent = answers.get(i);
                int currScore = 0;
                for (int j = 0; j < 25; j++) {
                    String[] currQuestion = currentStudent[j].split(":");
                    if (currQuestion[0].equals(currQuestion[1])) {
                        currScore++;
                    }
                }
                scores.add(currScore);
            }
            System.out.println(scores);
        } catch (Exception e) {

        }
    }
}
