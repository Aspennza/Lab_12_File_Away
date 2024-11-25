import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.System.out;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;
import java.util.Scanner;

public class DataSaver
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ArrayList<String> recs = new ArrayList<>();
        String firstName = "";
        String lastName = "";
        int userNumber = 0;
        String numberID = "";
        String email = "";
        int yobInt = 0;
        String yob = "";
        String recEntry = "";
        boolean continueYN = false;
        String fileName = "";

        try {
            do {
                    firstName = SafeInput.getNonZeroLenString(in, "Please enter a first name");
                    lastName = SafeInput.getNonZeroLenString(in, "Please enter a last name");

                    userNumber = SafeInput.getRangedInt(in, "Please enter an ID number", 1, 999999);

                    if(userNumber < 10) {
                        numberID = "00000" + userNumber;
                    }else if(userNumber < 100) {
                        numberID = "0000" + userNumber;
                    }else if(userNumber < 1000) {
                        numberID = "000" + userNumber;
                    }else if(userNumber < 10000) {
                        numberID = "00" + userNumber;
                    }else if(userNumber < 100000) {
                        numberID = "0" + userNumber;
                    }else {
                        numberID = Integer.toString(userNumber);
                    }

                    email = SafeInput.getRegExString(in, "Please enter an email address", "[a-zA-Z0-9]+@[a-zA-Z0-9]+[\\.][a-z]+");

                    yobInt = SafeInput.getRangedInt(in, "Please enter a year of birth", 1000, 9999);

                    yob = Integer.toString(yobInt);

                    recEntry = firstName + ", " + lastName + ", " + numberID + ", " + email + ", " + yob;

                    recs.add(recEntry);

                    continueYN = SafeInput.getYNConfirm(in, "Would you like to continue entering records?");
            }while (continueYN);




        }
        catch () {

        }
        catch () {

        }

    }
}
