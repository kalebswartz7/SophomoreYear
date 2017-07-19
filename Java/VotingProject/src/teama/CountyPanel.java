package teama;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CountyPanel extends JPanel {
    private CountyList countyList;

    CountyPanel(final CountyList countyList) {
        this.countyList = countyList;

        // Set padding
        this.setBorder(new EmptyBorder(20, 40, 20, 0));

        // County multi-select list
        JScrollPane listScroller = new JScrollPane(this.countyList);
        listScroller.setPreferredSize(new Dimension(250, 750));

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

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectAllButton);
        buttonPanel.add(deselectAllButton);

        this.add(listScroller);
        this.add(buttonPanel);
    }
}
