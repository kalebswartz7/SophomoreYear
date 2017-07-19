package teama;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    public DisplayPanel(TextualDisplay textualDisplay, BarChart barChart, PieChart pieChart) {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Textual Display", textualDisplay);
        tabbedPane.addTab("Bar Chart", barChart);
        tabbedPane.addTab("Pie Chart", pieChart);
        tabbedPane.setPreferredSize(new Dimension(500, 340));
        this.add(tabbedPane);
    }
}
