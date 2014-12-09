package Browsie;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private JTextField field = new JTextField();
    private JEditorPane display = new JEditorPane();
    private JScrollPane scrollPane = new JScrollPane(display);

    public static void main(String[] args) {
	    Main browser = new Main();
        browser.frameHandler();
    }

    public void frameHandler() {
        setTitle("Browser");
        setSize(1280, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        addComponentsToFrame(getContentPane());
    }

    public void addComponentsToFrame(Container pane) {
        Insets insets = getInsets();

        pane.add(field);
        pane.add(scrollPane);

        Font font = new Font("Sans", Font.ITALIC, 12);

        field.setFont(font);
        field.setBounds(8 - insets.left, 30 - insets.top, 1268, 20);
        scrollPane.setBounds(8 - insets.left, 30 - insets.top, 1268, 830);

        actionListenerCalls();
    }

    private void actionListenerCalls() {
        field.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                load("http://" + event.getActionCommand());
            }
        });

        display.addHyperlinkListener(
                new HyperlinkListener() {
                    public void hyperlinkUpdate(HyperlinkEvent event) {
                        if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                            load(event.getURL().toString());
                        }
                    }
                }
        );
    }

    private void load(String addressText) {
        try {
            display.setPage(addressText);
            field.setText(addressText);
        } catch (Exception e) {
            System.out.println("Malformed URL!");
        }
    }
}
