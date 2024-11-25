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

public class FileInspector
{
    //INCLUDE JAVADOC!!

    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                Path fileName = file.getFileName();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                int totalChars = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    lines.add(rec);
                    totalChars = totalChars + rec.length();
                    line++;

                    System.out.printf("\n\nLine %4d %-60s ", line, rec);
                }
                reader.close();
                System.out.println("\n\nThe data file has been read.");


                //DOES THIS WORK??

                String[] words;
                ArrayList<String> result = new ArrayList<>();
                for(String l:lines)
                {
                    words = l.split("\\s+");
                    result.addAll(Arrays.asList(words));
                }

                System.out.println("\nSummary of processed file:");
                System.out.printf("\nName of file processed: %50s", fileName);
                System.out.printf("\nNumber of lines in file: %4d", line);
                System.out.printf("\nNumber of words in file: %5d", result.size());
                System.out.printf("\nNumber of characters in file: %5d", totalChars);

            } else
            {
                System.out.println("The user didn't choose a file to process. Rerun the program to select and process a file.");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("The file couldn't be found.");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("An exception occurred.");
            e.printStackTrace();
        }

    }
}