package teama.cse201;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CountyPanel extends JPanel {
    private CountyList countyList;

    CountyPanel(final CountyList countyList) {
        this.countyList = countyList;

        // Set padding
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        // County multi-select list
        JScrollPane listScroller = new JScrollPane(this.countyList);
        listScroller.setPreferredSize(new Dimension(120, 400));

        // Select all button
        JButton selectAllButton = new JButton("Select All");
        selectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countyList.selectAll();
            }
        });

        // Deselect all button
        JButton deselectAllButton = new JButton("Deselect All");
        deselectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countyList.clearSelection();
            }
        });

        JList<County> selectedList = new JList<>();
        JScrollPane selectedListScroller = new JScrollPane(selectedList);
        selectedListScroller.setPreferredSize(new Dimension(120, 400));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectAllButton);
        buttonPanel.add(deselectAllButton);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout(5, 0));
        listPanel.add(listScroller, BorderLayout.WEST);
        listPanel.add(selectedListScroller, BorderLayout.EAST);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(listPanel);
        this.add(buttonPanel);
    }
}
