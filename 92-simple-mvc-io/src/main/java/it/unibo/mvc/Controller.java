package it.unibo.mvc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    
    public static final String PATH = System.getProperty("user.home") +
        System.getProperty("file.separator") + "output.txt";
    public File currentFile;
    
    public Controller() {
        this.currentFile = new File(PATH);
    }

    public void setCurrentFile(final String string) {
        this.currentFile = new File(string);
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public String getCurrentFilePath() {
        return currentFile.toPath().toString();
    }

    public void writeOnCurrentFile(final String string) throws IOException{
        final FileWriter writer = new FileWriter(this.currentFile);
        writer.write(string);
        writer.close();
    }

    
}
