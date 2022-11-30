import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        JFileChooser file_chooser = new JFileChooser();

        file_chooser.setAcceptAllFileFilterUsed( false );

        file_chooser.setFileFilter( new FileNameExtensionFilter( "Text Files", "txt" ) );

        File selectedFile;
        String lineStr;

        try {
            File dir = new File( System.getProperty( "user.dir" ) );
            file_chooser.setCurrentDirectory( dir );
            if (file_chooser.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION) {
                selectedFile = file_chooser.getSelectedFile();
                Path path = selectedFile.toPath();
                InputStream in = new BufferedInputStream( Files.newInputStream( path, CREATE ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );

                int line_count = 0;
                int word_count = 0;
                int character_count = 0;
                while (reader.ready()) {
                    lineStr = reader.readLine();
                    String[] wordsArray = lineStr.split( " " );
                    word_count = word_count + wordsArray.length;
                    character_count = character_count + lineStr.length();
                    line_count++;
                }
                System.out.println( "File Selected: " + selectedFile.getName() );
                System.out.println( "File Path: " + path );
                System.out.println( "Count of Lines in file: " + line_count );
                System.out.println( "Count of Words in file: " + word_count );
                System.out.println( "Total characters in file: " + character_count );

            }
        } catch (FileNotFoundException e) {
            System.out.println( "File not found!!!" );
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
