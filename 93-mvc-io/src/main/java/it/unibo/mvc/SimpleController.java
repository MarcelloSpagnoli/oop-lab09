package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * It implements a SimpleController.
 * Extends the {@link Controlle} interface
 */
public final class SimpleController implements Controller {
    private String current;
    private final List<String> history = new LinkedList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextStringToPrint(final String string) {
        this.current = Objects.requireNonNull(string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextStringToPrint() {
        return this.current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getPrintedStrings() {
        return List.copyOf(this.history);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printCurrentString() {
        if (this.current == null) {
            throw new IllegalStateException("String to print is unset");
        }
        System.out.println(this.current); //NOPMD: exercise asks so
        history.add(current);
    }

}
