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
    public static void main(String[] args)
    {
        /**
         * The file chooser that is used for selecting a file to read
         */
        JFileChooser chooser = new JFileChooser();
        /**
         * a File that holds the File selected by the user
         */
        File selectedFile;
        /**
         * a String that holds the line that has just been read from the document selected
         */
        String rec = "";
        /**
         * an ArrayList that holds every line from the document being read
         */
        ArrayList<String> lines = new ArrayList<>();

        /**
         * This algorithm identifies the working directory, allows the user to select a file to read, reads the file, outputs a summary of the file, and checks for exceptions
         */
        try {
            /**
             * this File holds the current directory
             */
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            /**
             * This algorithm identifies whether the user has selected a file, keeps track of the file selected, reads the file, identifies the number of lines, words, and characters in the file, and outputs these alongside the name of the file
             */
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                Path fileName = file.getFileName();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                /**
                 * an int that tracks how many lines have been read
                 */
                int line = 0;
                /**
                 * an int that tracks how many characters are in the file
                 */
                int totalChars = 0;
                /**
                 * This algorithm identifies if the file has lines left to read, saves each line in rec and in the lines ArrayList, and counts the number of lines and characters in the file; it also outputs the number of the line and the line read
                 */
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

                /**
                 * This array temporarily stores the words in the file on separate indices
                 */
                String[] words;
                /**
                 * This ArrayList permanently takes the list of words from the words array
                 */
                ArrayList<String> result = new ArrayList<>();
                /**
                 * This algorithm splits each line from lines into individual words, inputs these words into the words array, and transfers this list to results
                 */
                for(String l:lines)
                {
                    words = l.split("\\s+");
                    result.addAll(Arrays.asList(words));
                }

                System.out.println("\nSummary of processed file:");
                System.out.printf("\nName of file processed: %-50s", fileName);
                System.out.printf("\nNumber of lines in file: %-4d", line);
                System.out.printf("\nNumber of words in file: %-5d", result.size());
                System.out.printf("\nNumber of characters in file: %-5d", totalChars);

            } else
            {
                System.out.println("\nThe user didn't choose a file to process. Rerun the program to select and process a file.");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\nThe file couldn't be found.");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("\nAn exception occurred.");
            e.printStackTrace();
        }

    }
}