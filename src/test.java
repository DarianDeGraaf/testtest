import javax.swing.*;

public class test {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5", "Option 6", "Option 7"};

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);

        JComboBox<String> nameBox = new JComboBox<>(options);
        JComboBox<String> typeBox = new JComboBox<>(options);
        JComboBox<String> roomBox = new JComboBox<>(options);
        JTextField startTimeBox = new JTextField();
        JTextField endTimeBox = new JTextField();

        nameBox.setBounds(50, 30, 150, 25);
        typeBox.setBounds(50, 70, 150, 25);
        roomBox.setBounds(50, 110, 150, 25);
        startTimeBox.setBounds(50, 150, 150, 25);
        endTimeBox.setBounds(50, 190, 150, 25);

        frame.add(nameBox);
        frame.add(typeBox);
        frame.add(roomBox);
        frame.add(startTimeBox);
        frame.add(endTimeBox);

        frame.setVisible(true);

    }
}
