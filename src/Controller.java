import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class Controller {
    protected void writeLoggedUserToFile(String email) {
        try {
            FileWriter writer = new FileWriter("loggedUser.txt");
            writer.write(email);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
