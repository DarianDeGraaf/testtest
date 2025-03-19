import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class AppliancePanel {
    JPanel panel;
    static Set<String> roomOptions;
    static Set<String> applianceTypeOptions;
    static String [] applianceNameOptions;

    static int PANEL_WIDTH  = 900;
    static int PANEL_HEIGHT  = 50;


    static {
        roomOptions = Room.getAllEnumCaptions();
        applianceTypeOptions = ApplianceType.getAllEnumCaptions();
    }

    JComboBox<String> typeBox;
    JComboBox<String> nameBox;
    JComboBox<String> roomBox;
    JTextField startTimeBox;
    JTextField endTimeBox;
    JCheckBox alwaysOn;
    JButton deleteButton;

    public AppliancePanel(){
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(panel,  BoxLayout.X_AXIS));
        this.panel.setBackground(Color.RED);
        this.panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // initialization
        this.typeBox = new JComboBox<>(applianceTypeOptions.toArray(new String[0]));
        this.nameBox = new JComboBox<>();
        this.roomBox = new JComboBox<>(roomOptions.toArray(new String[0]));
        this.alwaysOn = new JCheckBox("Always On");
        this.startTimeBox = new JTextField();
        this.endTimeBox = new JTextField();
        this.deleteButton = new JButton();

        designInputs();

        this.panel.add(typeBox);
        this.panel.add(nameBox);
        this.panel.add(roomBox);
        this.panel.add(alwaysOn);
        this.panel.add(startTimeBox);
        this.panel.add(endTimeBox);
        this.panel.add(deleteButton);

        typeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateApplianceNameOptions();
                nameBox.removeAllItems();
                for (String name : applianceNameOptions) {
                    nameBox.addItem(name);
                }
            }
        });

        this.alwaysOn.addActionListener(e -> {
            if (this.alwaysOn.isSelected()) {
                this.startTimeBox.setText("0");
                this.endTimeBox.setText("24");
                this.startTimeBox.setEditable(false);
                this.endTimeBox.setEditable(false);
            }else {
                this.startTimeBox.setEditable(true);
                this.endTimeBox.setEditable(true);
            }
        });

        designInputs();

    }

    private void updateApplianceNameOptions(){
        String selectedType = (String) typeBox.getSelectedItem();
        ApplianceType applianceType = ApplianceType.getEnumByCaption(selectedType);
        applianceNameOptions = ApplianceService.getApplianceList().get(applianceType).toArray(new String[0]);

    }

    public void cleanup() {
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
        this.startTimeBox.setSize(80, 30);
    }
}
