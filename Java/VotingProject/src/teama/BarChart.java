package teama;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class BarChart extends Display {
    private ArrayList<ChartEntry> entries;

    public BarChart() {
        this.entries = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (entries.size() < 0) {
            return;
        }

        int maximum = Integer.MIN_VALUE;
        for (ChartEntry chartEntry : entries) {
            maximum = Math.max(chartEntry.value, maximum);
        }

        int x = 25;
        int width = ((getWidth() / entries.size()) - 2) / 2;
        for (ChartEntry chartEntry : entries) {
            int height = (int) ((getHeight() - 50) *
                    ((double) chartEntry.value / maximum));
            g.setColor(chartEntry.color);
            g.fillRect(x, getHeight() - height - 20, width, height);
            g.setColor(Color.BLACK);
            g.drawString(chartEntry.toString(), x, getHeight() - 5);
            x += width + 75;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(512, 512);
    }

    @Override
    public void setVotes(Result result) {
        entries.clear();
        entries.add(new ChartEntry(result.democratVotes, "Democrat", Color.BLUE));
        entries.add(new ChartEntry(result.republicanVotes, "Republican", Color.RED));
        entries.add(new ChartEntry(result.otherVotes, "Other", Color.GRAY));
        repaint();
    }
}

