
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public final class ApplicantForm {
    private Applicant[] applicants = new Applicant[10];

    public void display() {
        //the display function for the applicant class. draws the login screen
        for(int i =0;i<10;i++){
            applicants[i] = new Applicant();
        }

        readApplicants();
        Stage window = new Stage();
        window.setTitle("Math Test Applicant Form");
        Label name = new Label("Name");


        ComboBox nameBox = new ComboBox();
        for(int i=0;i<10;i++){
            nameBox.getItems().add(this.applicants[i].getName());
        }


        Label nationality = new Label("Nationality");

        Label gender = new Label("Gender");
        ComboBox genderBox = new ComboBox();
        for(int i=0;i<10;i++){
            if(this.applicants[i].getGender()){
                genderBox.getItems().add("Female");
            }
            else{
                genderBox.getItems().add("Male");
            }
        }
        genderBox.setOnShown(e->genderBox.hide());



        ComboBox nationalityBox = new ComboBox();
        for(int i=0;i<10;i++){
            nationalityBox.getItems().add(this.applicants[i].getNationality());
        }

        nameBox.setOnAction(e->{
            int selection = nameBox.getSelectionModel().getSelectedIndex();
            nationalityBox.getSelectionModel().select(selection);
            genderBox.getSelectionModel().select(selection);
        });
        nationalityBox.setOnShown(e->nationalityBox.hide());

        Label password = new Label("Password");
        Label wrong = new Label();
        wrong.setTextFill(Color.color(1,0,0));

        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e->{
            if(nameBox.getSelectionModel().isEmpty()){
                wrong.setText("Please select your Name");
            }
            else{
                int currUser = nameBox.getSelectionModel().getSelectedIndex();
                String currPass = passwordField.getText();
                if(applicants[currUser].getPassword().equals(currPass)){
                    ExamForm ef = new ExamForm(nameBox.getSelectionModel().getSelectedItem().toString()
                            ,nationalityBox.getSelectionModel().getSelectedItem().toString());
                    ef.display();
                    window.close();

                }
                else{

                    wrong.setText("Wrong password entered");

                }
            }
        });

        GridPane grid = new GridPane();
        grid.add(name,0,0);
        grid.add(nameBox,1,0);
        grid.add(gender,0,1);
        grid.add(genderBox,1,1);
        grid.add(nationality,0,2);
        grid.add(nationalityBox,1,2);
        grid.add(password,0,3);
        grid.add(passwordField,1,3);
        grid.add(loginButton,2,4);
        grid.add(wrong,1,4);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);

        Scene scene  = new Scene(grid,320,200);

        window.setScene(scene);
        window.setMinWidth(420);
        window.setMinHeight(300);
        window.setResizable(false);
        window.showAndWait();
    }
    private void readApplicants() {
        SerialInput si = new SerialInput();
        this.applicants = si.getApplicants();
    }
}
