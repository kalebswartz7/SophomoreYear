package teama.cse201;

import java.awt.*;

class ChartEntry {
    public int value;
    public String columnName;
    public Color color;

    public ChartEntry(int value, String columnName, Color color) {
        this.value = value;
        this.columnName = columnName;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", this.columnName, this.value);
    }
}
