package teama;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextualDisplay extends Display {
    private JLabel democratVotesLabel, republicanVotesLabel, otherVotesLabel;

    public TextualDisplay() {
        this.setLayout(new GridLayout(3, 2));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        otherVotesLabel = new JLabel("0");
        democratVotesLabel = new JLabel("0");
        republicanVotesLabel = new JLabel("0");

        JLabel democratLabel = new JLabel("Democrat Votes: ");
        JLabel republicanLabel = new JLabel("Republican Votes: ");
        JLabel otherLabel = new JLabel("Other Votes: ");

        Font labelFont = new Font("San Serif", Font.PLAIN, 20);
        Font countFont = new Font("San Serif", Font.BOLD, 20);
        democratLabel.setFont(labelFont);
        republicanLabel.setFont(labelFont);
        otherLabel.setFont(labelFont);
        democratVotesLabel.setFont(countFont);
        republicanVotesLabel.setFont(countFont);
        otherVotesLabel.setFont(countFont);

        this.add(democratLabel);
        this.add(democratVotesLabel);
        this.add(republicanLabel);
        this.add(republicanVotesLabel);
        this.add(otherLabel);
        this.add(otherVotesLabel);
    }

    public void setVotes(Result votes) {
        otherVotesLabel.setText(votes.otherVotes());
        democratVotesLabel.setText(votes.democratVotes());
        republicanVotesLabel.setText(votes.republicanVotes());
    }
}
