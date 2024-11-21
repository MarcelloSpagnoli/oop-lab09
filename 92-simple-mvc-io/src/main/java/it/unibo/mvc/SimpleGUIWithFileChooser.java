package it.unibo.mvc;

import java.awt.BorderLayout;
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
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser{

    private static final String TITLE = "Another Java interface";
    private static final int PROPORTION = 4;
    private final JFrame frame = new JFrame(TITLE);

    public SimpleGUIWithFileChooser(final Controller ctrl) {
        final JPanel canvas = new JPanel(new BorderLayout());
        final JButton save = new JButton("Save");
        final JTextArea text = new JTextArea();
        frame.setContentPane(canvas);
        canvas.add(text, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        final JPanel panel = new JPanel(new BorderLayout());
        canvas.add(panel, BorderLayout.NORTH);
        final JTextArea filePath = new JTextArea(ctrl.getCurrentFile().toString());
        filePath.setEditable(false);
        final JButton browse = new JButton("Browse");
        panel.add(filePath, BorderLayout.CENTER);
        panel.add(browse, BorderLayout.LINE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
                    chooser.setFileFilter(filter);
                    final Integer result = chooser.showSaveDialog(browse);
                    if (result.equals(JFileChooser.APPROVE_OPTION)) {
                        ctrl.setCurrentFile(chooser.getSelectedFile().getPath().toString());
                        filePath.setText(chooser.getSelectedFile().getPath().toString());
                    }
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(browse, error.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                }    
            }
            
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ctrl.writeOnCurrentFile(text.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }   
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int height = (int)screen.getHeight();
        final int width = (int)screen.getWidth();
        frame.setSize(width/PROPORTION, height/PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}
