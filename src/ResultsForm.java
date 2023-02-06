import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultsForm {


    private Text header;

    private Text congrats;
    private Text info;

    private Stage window;
    private Scene scene;
    private Pane layout;


    //each button navigates the program into a different window
    private Button mainMenu;
    private Button individualResults;
    private Button questionStats;
    private Button overallStatistics;


    public ResultsForm(){

    }

    public void display(){
        // display function for the results form
        window = new Stage();
        window.setTitle("Results Form");

        layout = new Pane();

        header = new Text("Results Form");
        header.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
        header.setLayoutX(150);
        header.setLayoutY(30);

        congrats = new Text("Congratulations, you have finished the test.");
        congrats.setLayoutX(75);
        congrats.setLayoutY(150);

        info = new Text("You may return to the login screen or see various results in the options below");
        info.setLayoutX(75);
        info.setLayoutY(180);

        mainMenu = new Button("Main Menu");
        mainMenu.setLayoutX(150);
        mainMenu.setLayoutY(200);

        mainMenu.setOnAction(e->{
            window.close();
            ApplicantForm af = new ApplicantForm();
            af.display();
        });

        individualResults= new Button("Individual Results");
        individualResults.setLayoutX(150);
        individualResults.setLayoutY(240);

        individualResults.setOnAction(e->{
            window.close();
            IndividualResults ir = new IndividualResults();
            ir.display();
        });

        questionStats = new Button("Question Statistics");
        questionStats.setLayoutX(150);
        questionStats.setLayoutY(280);

        questionStats.setOnAction(e->{
            window.close();
            QuestionStatistics qs = new QuestionStatistics();
            qs.display();
        });

        overallStatistics = new Button("Exam Statistics");
        overallStatistics.setLayoutX(150);
        overallStatistics.setLayoutY(320);

        overallStatistics.setOnAction(e->{
            window.close();
            ExamStatistics es = new ExamStatistics();
            es.display();
        });
        window.setOnCloseRequest(e->{
            ApplicantForm af= new ApplicantForm();
            af.display();
        });

        layout.getChildren().addAll(header,congrats,info,mainMenu,individualResults,questionStats,overallStatistics);

        scene = new Scene(layout,500,400);
        window.setScene(scene);
        window.show();

    }
}
