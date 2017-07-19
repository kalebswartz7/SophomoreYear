package teama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FolderPanel extends JPanel implements EventHandler {
    private boolean foundErrors = false;
    private JComponent mainPanel;

    public FolderPanel(final JComponent mainPanel) {
        this.mainPanel = mainPanel;
        final JTextField textField = new JTextField();
        textField.setEnabled(false);
        textField.setText("No folder chosen...");
        textField.setPreferredSize(new Dimension(350, 25));

        // File chooser button
        final JButton openDirectoryButton = new JButton("Select folder");
        openDirectoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open file picker
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Select a directory to read CSV files from");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (chooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    // Dispatch the directoryChanged event
                    openDirectoryButton.setText("Change folder");
                    textField.setText(chooser.getSelectedFile().getAbsolutePath());
                    EventBus.fire("directoryChanged", chooser.getSelectedFile());
                    foundErrors = false;
                }
            }
        });

        this.add(openDirectoryButton);
        this.add(textField);
    }

    @Override
    public void handleEvent(Object object) {
        final File file = (File)object;
        final JComponent that = this;
        if (!foundErrors) {
            foundErrors = true;
            JLabel label = new JLabel("Errors were detected in the voting data. Click below to view them");
            final JButton errorButton = new JButton("Errors");
            errorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException e1) {
                        //
                    }
                }
            });

            //this.add(label);
            //this.add(errorButton);
        }
    }
}
