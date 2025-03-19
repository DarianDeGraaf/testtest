import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AppliancePanel {
    JPanel panel;

    String[] options = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5", "Option 6", "Option 7"};
    JComboBox<String> nameBox;
    JComboBox<String> typeBox;
    JComboBox<String> roomBox;
    JTextField startTimeBox;
    JTextField endTimeBox;
    JButton deleteButton;

    public AppliancePanel(){
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(panel,  BoxLayout.X_AXIS));
        this.panel.setBackground(Color.RED);
        this.panel.setPreferredSize(new Dimension(600, 50));

        // initialization
        this.nameBox = new JComboBox<>(options);
        this.typeBox = new JComboBox<>(options);
        this.roomBox = new JComboBox<>(options);
        this.startTimeBox = new JTextField();
        this.endTimeBox = new JTextField();
        this.deleteButton = new JButton();

        designInputs();

        this.panel.add(nameBox);
        this.panel.add(typeBox);
        this.panel.add(roomBox);
        this.panel.add(startTimeBox);
        this.panel.add(endTimeBox);
        this.panel.add(deleteButton);
    }


    public void cleanup() {
        // remove all action listeners to prevent memory leaks
        for (ActionListener al : deleteButton.getActionListeners()) {
            deleteButton.removeActionListener(al);
        }
        this.panel = null;
        this.nameBox = null;
        this.typeBox = null;
        this.roomBox = null;
        this.startTimeBox = null;
        this.endTimeBox = null;
        this.deleteButton = null;
    }

    private void designInputs(){
        //TODO
        this.deleteButton.setText("Delete");
    }
}
