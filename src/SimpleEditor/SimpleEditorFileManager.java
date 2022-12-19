package SimpleEditor;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleEditorFileManager {
    File file;

    public SimpleEditorFileManager(String filePath) {
        file = new File(filePath);
    }


    public String readFile() {
            String fileString = null;
            if(file.canRead()) {
                try {
                    FileReader reader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    StringBuilder builder = new StringBuilder();
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        builder.append(line);
                        builder.append(System.lineSeparator());
                        line = bufferedReader.readLine();
                    }
                    fileString = builder.toString();
                    bufferedReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(SimpleEditorFileManager.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(file + " не может быть прочтен.");
                }
            }
            return fileString;
    }
    public void saveFile(String text) {
        if(file==null) return;
        if(file.canWrite()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(text);
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(SimpleEditorFileManager.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(file + " не может быть записан.");
            }
        }
    }
    public boolean validateFile() throws Exception{
        if(file.getAbsolutePath().endsWith(".txt"))
            return true;
        else
            throw new Exception();
    }
}


