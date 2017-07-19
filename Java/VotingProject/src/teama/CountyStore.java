package teama;


import java.util.ArrayList;

public class CountyStore implements EventHandler {

    /**
     * Map of countyNames mapped to their respective County
     */
    private ArrayList<County> countyList;

    /**
     * Constructor.
     */
    CountyStore() {
        this.countyList = new ArrayList<>();
    }

    /**
     * Count the number of votes for each party from the selected counties.
     *
     * @param  selectedCounties List of the counties selected in the select-box.
     * @return A `Result` instance containing the new voting totals for each party.
     */
    public Result getTotalsForSelectedCounties(ArrayList<County> selectedCounties) {
        Result result = new Result();
        for (County county : selectedCounties) {
            if (!county.isIllegal) {
                result.otherVotes += county.otherVotes;
                result.democratVotes += county.democratVotes;
                result.republicanVotes += county.republicanVotes;
            }
        }

        return result;
    }

    @Override
    public void handleEvent(Object object) {
        this.countyList = (ArrayList<County>)object;
        EventBus.fire("countyDataLoaded", countyList);
    }
}
