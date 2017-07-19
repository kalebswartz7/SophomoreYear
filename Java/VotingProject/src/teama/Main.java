package teama;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        final int width = 800;
        final int height = 640;

        JFrame f = new JFrame();
        f.setTitle("Ohio Voting Data");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the county repository
        CountyStore countyStore = new CountyStore();

        // Create the voting data parser
        VotingDataParser votingDataParser = new VotingDataParser();
        votingDataParser.loadRegisteredVoters("RegisteredOhioVoters.csv");

        // Create displays
        TextualDisplay textualDisplay = new TextualDisplay();
        BarChart barChart = new BarChart();
        PieChart pieChart = new PieChart();

        // Create display manager
        DisplayManager displayManager = new DisplayManager(countyStore);
        displayManager.addDisplay(textualDisplay);
        displayManager.addDisplay(barChart);
        displayManager.addDisplay(pieChart);

        // Create the county multi-select box
        CountyList countyList = new CountyList();

        // Create the county panel
        CountyPanel countyPanel = new CountyPanel(countyList);
        countyPanel.setSize(width / 2, height);

        // Create the display panel
        DisplayPanel displayPanel = new DisplayPanel(textualDisplay, barChart, pieChart);
        displayPanel.setSize(width / 2, height);

        // Create the right panel
        JPanel rightPanel = new JPanel();

        // Create the folder panel
        FolderPanel folderPanel = new FolderPanel(rightPanel);

        // Add components to right panel
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(displayPanel);
        rightPanel.add(folderPanel);

        // Add main panels to JFrame
        f.add(countyPanel, BorderLayout.WEST);
        f.add(rightPanel, BorderLayout.EAST);

        // Add the event listeners
        EventBus.addListener("directoryChanged", votingDataParser);
        EventBus.addListener("votingRecordsParsed", countyStore);
        EventBus.addListener("countyDataLoaded", countyList);
        EventBus.addListener("selectedCountiesUpdated", displayManager);
        EventBus.addListener("errorsFound", folderPanel);

        // Open the GUI
        f.setSize(width, height);
        f.setVisible(true);
    }
}
