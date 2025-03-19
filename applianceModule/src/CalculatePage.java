import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CalculatePage {
    private List<AppliancePanel> appliancePanels;
    private JFrame frame;
    private JPanel bodyPanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    private static final int PANEL_HEIGHT = 50;
    private static final int PANEL_DISTANCE = 10;


    public CalculatePage() {
        this.appliancePanels = new ArrayList<>();
        this.frame = createFrame();
        this.bodyPanel = new JPanel();
        this.bodyPanel.setLayout(new BoxLayout(this.bodyPanel, BoxLayout.Y_AXIS));
//        this.bodyPanel.setBackground(Color.PINK);
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
//        this.buttonPanel.setBackground(Color.YELLOW);
        this.frame.add(this.buttonPanel, BorderLayout.SOUTH);

        createHeader();
        createScroll();
        createAddApplianceButton();
        createActionButtons();

        this.frame.setVisible(true);
    }
    private JFrame createFrame() {
        JFrame newFrame = new JFrame("Calculate Carbon Footprint");
        newFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setLayout(new BorderLayout());
        return newFrame;
    }
    private void createActionButtons() {
        createCalculateButton();
        createStatisticsButton();
        createRecommendationsButton();
        createSaveButton();
        createRestoreButton();
    }
    public void addPanel() {
        JPanel panelWrapper = new JPanel();
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
        JPanel headPanel  = new JPanel();
        this.frame.add(headPanel, BorderLayout.NORTH);
        headPanel.setPreferredSize(new Dimension(frame.getWidth(), 40));
        headPanel.setLayout(new BorderLayout());

        JButton leftButton = new JButton("Menu");
        leftButton.addActionListener(e -> {
            closeFrame();
            // TODO
            //  new HomePage();
        });
        headPanel.add(leftButton, BorderLayout.WEST);
        JLabel title = new JLabel("HouseHold", SwingConstants.CENTER);
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
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addApplianceButton = new JButton("Add Appliance");
        addApplianceButton.setPreferredSize(new Dimension(200, 50));
        addApplianceButton.addActionListener(e -> addPanel());
        buttonPanel.add(addApplianceButton);
        this.bodyPanel.add(Box.createVerticalStrut(20));
        this.bodyPanel.add(buttonPanel);
    }
    private void createCalculateButton() {
        JButton calculateButton = new JButton("Calculate Carbon Footprint");
        calculateButton.addActionListener(e -> openNewFrame());
        this.buttonPanel.add(calculateButton);
    }
    private void createStatisticsButton() {
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
    public static void main(String[] args) {
        new CalculatePage();
    }
}








