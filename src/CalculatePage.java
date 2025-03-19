import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CalculatePage {
    List<AppliancePanel> appliancePanels;
    JFrame frame;
    JPanel bodyPanel;
    JPanel buttonPanel;
    JScrollPane scrollPane;
    static final int FRAME_WIDTH = 700;
    static final int FRAME_HEIGHT = 600;
    static final int PANEL_HEIGHT = 50;
    static final int PANEL_DISTANCE = 10;


    public CalculatePage() {
//        this.homeFrame = home;
        this.appliancePanels = new ArrayList<>();
        this.frame = new JFrame("Calculate Carbon Footprint");
        this.frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());

        createHeader();

        this.bodyPanel = new JPanel();
        this.bodyPanel.setLayout(new BoxLayout(this.bodyPanel, BoxLayout.Y_AXIS));
//        this.bodyPanel.setBackground(Color.PINK);

        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
//        this.buttonPanel.setBackground(Color.YELLOW);
        this.frame.add(this.buttonPanel, BorderLayout.SOUTH);

        createScroll();
        createAddApplianceButton();

        createCalculateButton();
        createStatisticsButton();
        createRecommendationsButton();
        createSaveButton();
        createRestoreButton();

        this.bodyPanel.revalidate();
        this.bodyPanel.repaint();

        this.buttonPanel.revalidate();
        this.buttonPanel.repaint();
        this.frame.setVisible(true);
    }
    public void addPanel() {
        JPanel panelWrapper = new JPanel();
//        panelWrapper.setBackground(Color.gray);
        panelWrapper.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelWrapper.setMaximumSize(new Dimension(FRAME_WIDTH, PANEL_HEIGHT));

        AppliancePanel appliacePanel = new AppliancePanel();
        appliacePanel.deleteButton.addActionListener(e -> removePanel(appliacePanel, panelWrapper));

        panelWrapper.add(appliacePanel.panel);

        this.appliancePanels.add(appliacePanel);

        bodyPanel.add(panelWrapper, bodyPanel.getComponentCount() - 1);
        bodyPanel.add(Box.createVerticalStrut(PANEL_DISTANCE), bodyPanel.getComponentCount() - 1);

        bodyPanel.revalidate();
        bodyPanel.repaint();
    }
    public void removePanel(AppliancePanel appliacePanel, JPanel panelWrapper) {
        int index = -1;
        for (int i = 0; i < bodyPanel.getComponentCount(); i++) {
            if (bodyPanel.getComponent(i) == panelWrapper) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            bodyPanel.remove(index);
            if (index < bodyPanel.getComponentCount()) {
                Component nextComponent = bodyPanel.getComponent(index);
                if (nextComponent instanceof Box.Filler) {
                    // remove box filler if included.
                    bodyPanel.remove(index);
                }
            }
        }

        this.appliancePanels.remove(appliacePanel);
        appliacePanel.cleanup();

        bodyPanel.revalidate();
        bodyPanel.repaint();
    }
    public void createHeader(){
        // header
        JPanel headPanel  = new JPanel();
        this.frame.add(headPanel, BorderLayout.NORTH);
        headPanel.setPreferredSize(new Dimension(frame.getWidth(), 40));
//        headPanel.setBackground(Color.BLUE);
        headPanel.setLayout(new BorderLayout());

        JButton leftButton = new JButton("Menu");
        leftButton.addActionListener(e -> {
            closeFrame();
            new HomePage();
        });
        headPanel.add(leftButton, BorderLayout.WEST);

        JLabel title = new JLabel("HouseHold", SwingConstants.CENTER);
//        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(BorderFactory.createEmptyBorder(0, -80, 0, 0));
        headPanel.add(title, BorderLayout.CENTER);

        frame.add(headPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private void closeFrame(){
        frame.dispose();
    }
    private void createScroll(){
        this.scrollPane = new JScrollPane(this.bodyPanel);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        this.frame.add(scrollPane, BorderLayout.CENTER);
    }
    private void createAddApplianceButton() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Center the button
        JButton addApplianceButton = new JButton("Add Appliance");
        addApplianceButton.setPreferredSize(new Dimension(200, 50));
        addApplianceButton.addActionListener(e -> addPanel());

        buttonPanel.add(addApplianceButton);

        this.bodyPanel.add(Box.createVerticalStrut(20)); // Add spacing
        this.bodyPanel.add(buttonPanel);
    }
    private void createCalculateButton() {
//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right align the button
        JButton calculateButton = new JButton("Calculate Carbon Footprint");
        calculateButton.addActionListener(e -> openNewFrame());
        this.buttonPanel.add(calculateButton);
    }
    private void createStatisticsButton() {
//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton statisticsButton = new JButton("Show Statistics");
        statisticsButton.addActionListener(e -> openNewFrame());
        this.buttonPanel.add(statisticsButton);
        // statistics service object will be initialized
        // input info will be passed in the constric
    }
    private void createRecommendationsButton() {
//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton recommendationButton = new JButton("Give me Recommendations");
        recommendationButton.addActionListener(e -> openNewFrame());
        this.buttonPanel.add(recommendationButton);
    }
    private void createSaveButton() {
//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save Configuration");
        this.buttonPanel.add(saveButton);
    }
    private void createRestoreButton() {
//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Restore Saved Configuration");
        this.buttonPanel.add(saveButton);
    }

    private void openNewFrame() {
        JFrame newFrame = new JFrame("New Frame");
        newFrame.setSize(300, 200);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JLabel label = new JLabel("Hello World", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font size

        // Add the label to the new frame
        newFrame.add(label);

        // Make the new frame visible
        newFrame.setVisible(true);
    }


//    public static void main(String[] args) {
//        new CalculatePage();
//    }
}






