package it.unibo.mvc;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 3;

    private final Controller ctrl;
    private final JFrame frame = new JFrame();

    /**
     * Public contructor with handlers.
     * @param controller of type {@link Controller} is the application controller
     */
    @SuppressFBWarnings(
        value = { "EI_EXPOSE_REP2" },
        justification = "The exercise asks me to implement the controller this way, don't complain about this"
    )
    public SimpleGUI(final Controller controller) {
        this.ctrl = controller;
        final JPanel panel = new JPanel(new BorderLayout());
        frame.setContentPane(panel);
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        final JTextField textField = new JTextField();
        panel.add(textField, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);
        final JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
        panel.add(lowerPanel, BorderLayout.SOUTH);
        final JButton print = new JButton("Print");
        final JButton history = new JButton("History");
        lowerPanel.add(print);
        lowerPanel.add(history);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * handlers
         */
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String message = textField.getText();
                ctrl.setNextStringToPrint(message);
                ctrl.printCurrentString();
                textField.setText("");
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final List<String> history = ctrl.getPrintedStrings();
                textArea.setText("");
                for (final String string : history) {
                    textArea.append(string + "\n");
                }
            }
        });
    }

    /**
     * Makes adjustements about the frame size and displays.
     */
    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int height = screen.height;
        final int width = screen.width;
        frame.setSize(width / PROPORTION, height / PROPORTION);
        frame.setVisible(true);
    }


    /**
     * This method displays the graphical interface. 
     * @param args ignored
     */
    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }
}
