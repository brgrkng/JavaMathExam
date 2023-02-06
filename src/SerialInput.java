import java.io.*;

public final class SerialInput implements Serializable {
    //class used to read input from the serialized file

    private Applicant[] newApplicants;

    public SerialInput()  {
        try{
            newApplicants = new Applicant[10];
            FileInputStream fis = new FileInputStream("applicants.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            newApplicants =(Applicant[])ois.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public Applicant[] getApplicants(){
        return(this.newApplicants);
    }
}
