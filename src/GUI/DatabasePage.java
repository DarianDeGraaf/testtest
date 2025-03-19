package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatabasePage {
    JFrame frame;

    public DatabasePage(){
        this.frame = new JFrame("Update Appliance Database");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(500,500));
        this.frame.setLayout(new BorderLayout());

        JButton menu = new JButton();
        menu.setText("Menu");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomePage();
            }
        });
        this.frame.add(menu, BorderLayout.NORTH);
        frame.setVisible(true);
    }
}
