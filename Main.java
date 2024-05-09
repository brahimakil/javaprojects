import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Enrolling enroll = new Enrolling();
        enroll.loadStudents();
        enroll.mainmenu();
    }
}
