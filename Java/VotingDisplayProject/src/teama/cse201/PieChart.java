package teama.cse201;

import java.awt.*;
import java.util.ArrayList;


public class PieChart extends Display {
    private ArrayList<ChartEntry> entries;

    PieChart() {
        entries = new ArrayList<>();
        entries.add(new ChartEntry(1320, "Democrat", Color.BLUE));
        entries.add(new ChartEntry(625, "Republican", Color.RED));
        entries.add(new ChartEntry(62, "Other", Color.GRAY));
    }

    @Override
    public void setVotes(Result result) {
        entries.clear();
        entries.add(new ChartEntry(result.democratVotes, "Democrat", Color.BLUE));
        entries.add(new ChartEntry(result.republicanVotes, "Republican", Color.RED));
        entries.add(new ChartEntry(result.otherVotes, "Other", Color.GRAY));
        repaint();
    }

    public void paint(Graphics g) {
        Rectangle area = new Rectangle(this.getX(), this.getY() - 25,
                this.getHeight() - 25, this.getHeight() - 25);

        double total = 0.0;
        for (ChartEntry entry : entries) {
            total += entry.value;
        }

        if (total == 0) return;

        // Draw pie chart
        int startAngle = 0;
        double curValue = 0.0;
        for (ChartEntry entry : entries) {
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (entry.value * 360 / total) + (entry.value > 0 ? 1 : 0);
            g.setColor(entry.color);
            g.fillArc(area.x, area.y, area.width, area.height,
                    startAngle, arcAngle);
            curValue += entry.value;
        }

        // Set labels
        int y = getHeight() / 2;
        int x = getWidth() - 150;
        g.setColor(Color.black);
        for (ChartEntry entry : entries) {
            Double percentage = total > 0 ? entry.value / total * 100 : 0;
            g.setColor(entry.color);
            g.fillRect(x, y - 6, 8, 8);
            g.setColor(Color.black);
            g.drawString(String.format("%s (%.2f%%)", entry.columnName, percentage), 15 + x, y);
            y -= 20;
        }
    }
}
