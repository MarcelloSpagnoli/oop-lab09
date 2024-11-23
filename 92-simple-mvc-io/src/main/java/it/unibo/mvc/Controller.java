package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt";

    private File currentFile;

    /**
     * Public constructor.
     */
    public Controller() {
        this.currentFile = new File(PATH);
    }

    /**
     * Sets the current file where to write.
     * @param file
     */
    public void setCurrentFile(final File file) {
        this.currentFile = file;
    }

    /**
     * Gives the current file.
     * @return File which is set current
     */
    public File getCurrentFile() {
        return currentFile;
    }

    /**
     * Gives the pah of the file.
     * @return the path of the file as a string
     */
    public String getCurrentFilePath() {
        return currentFile.toPath().toString();
    }

    /**
     * Writes a certain string on the current file.
     * @param string
     * @throws IOException
     */
    public void writeOnCurrentFile(final String string) throws IOException {
        try (PrintStream ps = new PrintStream(this.currentFile, StandardCharsets.UTF_8)) {
            ps.println(string);
        }
    }
}
