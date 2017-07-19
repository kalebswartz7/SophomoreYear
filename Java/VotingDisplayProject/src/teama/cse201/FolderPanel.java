package teama.cse201;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class FolderPanel extends JPanel implements EventHandler {
    private JComponent mainPanel;
    private JLabel errorLabel;
    private File errorFile;

    public FolderPanel(final JComponent mainPanel) {
        this.mainPanel = mainPanel;
        final JTextField textField = new JTextField();
        errorLabel = new JLabel("There were errors detected in the voting data.", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        textField.setEnabled(false);
        textField.setText("No folder chosen...");
        textField.setPreferredSize(new Dimension(350, 25));

        errorLabel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                final int x = e.getX();
                final int y = e.getY();
                final Rectangle cellBounds = errorLabel.getBounds();
                if (cellBounds != null && cellBounds.contains(x, y)) {
                    errorLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    errorLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }
        });

        errorLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (errorFile != null) {
                    try {
                        Desktop.getDesktop().open(errorFile);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

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
                    errorLabel.setVisible(false);
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel pickerPanel = new JPanel();
        pickerPanel.add(openDirectoryButton);
        pickerPanel.add(textField);

        this.add(pickerPanel);
        this.add(errorLabel);
        this.setBorder(new EmptyBorder(0, 0, 20, 0));
    }

    @Override
    public void handleEvent(Object object) {
        errorFile = (File) object;
        errorLabel.setVisible(true);
    }
}
