package it.unibo.mvc;

import java.util.List;

/**
 *Application controller. Performs the I/O.
 */
public interface Controller {
    /**
     * Sets the next string to print.
     *  Null values are not acceptable
     * @param string the next String to print
     */
    void setNextStringToPrint(String string);


    /**
     * Gets the next string to print.
     * @return {@link String} which is set to print
     */
    String getNextStringToPrint();

    /**
     * Gets the history of the printed strings.
     * @return {@link java.util.List} of the printed stringd
     */
    List<String> getPrintedStrings();

    /**
     * It prints the current string.
     */
    void printCurrentString();
}
