package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String TITLE = "My first Java graphical interface"; 
    private static final int PROPORTION = 4;

    private final JFrame frame = new JFrame(TITLE);

    /**
     * Constructor of SimpleGUI.
     * @param ctrl Controller 
     */
    public SimpleGUI(final Controller ctrl) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JButton save = new JButton("Save");
        final JTextArea text = new JTextArea();
        panel.add(text, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    ctrl.writeOnCurrentFile(text.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(save, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int height = (int) screen.getHeight();
        final int width = (int) screen.getWidth();
        this.frame.setSize(width / PROPORTION, height / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Main function, starts a SimpleGUI.
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUI(new Controller()).display();
    }
}
