package teama;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class CountyList extends JList<County> implements EventHandler {
    private ArrayList<County> items;

    public CountyList() {
        final JList jlist = this;
        this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int indexStart, int indexEnd) {
                final int index0 = indexStart;
                final int index1 = indexEnd;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (jlist.isSelectedIndex(index0)) {
                            jlist.removeSelectionInterval(index0, index1);
                        }
                        else {
                            jlist.addSelectionInterval(index0, index1);
                        }
                    }
                });

            }
        });

        final CountyList that = this;
        this.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent ev) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        that.selectionChanged();
                    }
                });
            }
        });

        this.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list,
                                                          Object value, int index, boolean isSelected,
                                                          boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index,
                        isSelected, cellHasFocus);

                County county = (County)value;

                if (county.isIllegal) {
                    setForeground(Color.RED);
                    if (isSelected) {
                        setBackground(Color.RED);
                        setForeground(Color.WHITE);
                        setBorder(null);
                    }
                } else if (county.totalVotes() == 0) {
                    if (!isSelected) {
                        setForeground(Color.GRAY);
                    }
                }

                return this;
            }
        });
    }

    public void selectionChanged() {
        ArrayList<County> counties = new ArrayList<>();
        int[] selectedIndices = this.getSelectedIndices();
        for (int index : selectedIndices) {
            if (index < getModel().getSize()) {
                counties.add(getModel().getElementAt(index));
            }
        }

        EventBus.fire("selectedCountiesUpdated", counties);
    }

    public void selectAll() {
        super.clearSelection();
        this.setSelectionInterval(0, items.size());
    }

    public void clearSelection() {
        super.clearSelection();
        this.selectionChanged();
    }

    @Override
    public void handleEvent(Object object) {
        ArrayList<County> items = (ArrayList<County>)object;
        this.items = items;
        DefaultListModel<County> defaultListModel = new DefaultListModel<>();
        for (County item : items) {
            defaultListModel.addElement(item);
        }
        this.setModel(defaultListModel);
    }
}
