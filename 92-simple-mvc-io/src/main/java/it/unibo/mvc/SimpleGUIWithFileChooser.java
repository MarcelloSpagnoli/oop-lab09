package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "Another Java interface";
    private static final int PROPORTION = 4;
    private final JFrame frame = new JFrame(TITLE);

    /**
     * SimpleGUIWithFIleChooser contructor.
     * @param ctrl COntroller
     */
    public SimpleGUIWithFileChooser(final Controller ctrl) {
        final JPanel canvas = new JPanel(new BorderLayout());
        final JButton save = new JButton("Save");
        final JTextArea text = new JTextArea();
        frame.setContentPane(canvas);
        canvas.add(text, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);

        final JTextField filePath = new JTextField(ctrl.getCurrentFile().toString());
        filePath.setEditable(false);
        final JButton browse = new JButton("Browse");
        final JPanel panel = new JPanel();


        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                final FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.addChoosableFileFilter(filter);
                final Integer result = chooser.showSaveDialog(browse);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        ctrl.setCurrentFile(chooser.getSelectedFile());
                        filePath.setText(chooser.getSelectedFile().getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, result, "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    ctrl.writeOnCurrentFile(text.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.add(filePath, BorderLayout.CENTER);
        panel.add(browse, BorderLayout.LINE_END);
        canvas.add(panel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int height = (int) screen.getHeight();
        final int width = (int) screen.getWidth();
        frame.setSize(width / PROPORTION, height / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Main function, starts a SimpleGUIWithFileChooser.
     * @param args ignored
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}
