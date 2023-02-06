import javafx.application.Application;
import javafx.stage.Stage;

public class StartExamination extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        ApplicantForm af = new ApplicantForm();
        af.display();
    }
}
