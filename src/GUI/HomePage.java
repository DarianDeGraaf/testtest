package GUI;

import javax.swing.*;
import java.awt.*;

public class HomePage {
    private JFrame frame;
    public HomePage(){
        frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // Create a wrapper panel to center the button container
        JPanel wrapperPanel = new JPanel(new GridBagLayout());

        // Button container
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
        buttonContainer.setPreferredSize(new Dimension(200, 100));

        JButton goToDB = new JButton("Update DataBase");
        JButton goToCalculate = new JButton("Calculate Carbon Footprint");

        buttonContainer.add(goToDB);
        buttonContainer.add(goToCalculate);

        goToCalculate.addActionListener(e->{frame.dispose();new CalculatePage();});
        goToDB.addActionListener(e->{frame.dispose();new DatabasePage();});

        wrapperPanel.add(buttonContainer);
        frame.add(wrapperPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage();
    }
}

