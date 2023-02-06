import java.io.Serializable;

public final class Applicant implements Serializable {
    private String name,nationality;
    private boolean gender;
    private String password;

    Applicant(String name, String nationality, boolean sex, String pass){
        this.name = name;
        this.nationality = nationality;
        this.gender = sex;
        this.password = pass;
    }
    Applicant(){
        this.name = "default";
        this.nationality = "default";
        this.gender = false;
        this.password = "default";
    }
    public String getName(){
        return(this.name);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNationality(){
        return(this.nationality);
    }
    public void setNationality(String nationality){
        this.nationality = nationality;
    }
    public boolean getGender(){
        return(this.gender);
    }
    public void setGender(boolean sex){
        this.gender = sex;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public String getPassword() {
        return(this.password);
    }
}
