package teama;

import java.util.ArrayList;

public class DisplayManager implements EventHandler {
    private ArrayList<Display> displays;
    private CountyStore countyStore;

    public DisplayManager(CountyStore countyStore) {
        displays = new ArrayList<>();
        this.countyStore = countyStore;
    }

    public void addDisplay(Display display) {
        this.displays.add(display);
    }

    public void refresh(ArrayList<County> selectedCounties) {
        Result votes = countyStore.getTotalsForSelectedCounties(selectedCounties);

        for (Display display : this.displays) {
            display.setVotes(votes);
        }
    }

    @Override
    public void handleEvent(Object object) {
        refresh((ArrayList<County>)object);
    }
}
