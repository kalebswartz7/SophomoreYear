package teama;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class VotingDataParser implements EventHandler {
    /**
     * Map with county name as the key and the
     * number of registered voters as the value.
     */
    private HashMap<String, Integer> registeredVoters;

    /**
     * Map with a name as the key for
     * quick voting total lookups.
     */
    private HashMap<String, County> countyTable;

    /**
     * Error Handler
     */
    private ErrorHandler errorHandler;

    /**
     * Constructor
     */
    public VotingDataParser() {
        countyTable = new HashMap<>();
        registeredVoters = new HashMap<>();
    }

    /**
     * Read voting data strings and
     * populate the votingTable.
     *
     * @param votingRecords List of the .
     */
    public ArrayList<County> createCountyList(ArrayList<VotingRecord> votingRecords) {
        for (VotingRecord votingRecord : votingRecords) {
            County existingCounty = countyTable.get(votingRecord.getCountyName());
            if (existingCounty != null) {
                existingCounty.otherVotes += votingRecord.getOtherVotes();
                existingCounty.democratVotes += votingRecord.getDemocratVotes();
                existingCounty.republicanVotes += votingRecord.getRepublicanVotes();
                if (existingCounty.totalVotes() > registeredVoters.get(existingCounty.name)) {
                    errorHandler.reportIllegalVotingData(existingCounty.name);
                    existingCounty.isIllegal = true;
                }

                countyTable.put(votingRecord.getCountyName(), existingCounty);
            }
        }

        ArrayList<County> counties = new ArrayList<>(countyTable.values());
        Collections.sort(counties);
        return counties;
    }

    /**
     * Read the RegisteredVoters.csv and load those values.
     *
     * @param fileName The name of the file to read the registered voters from.
     */
    public void loadRegisteredVoters(String fileName) {
        FileReader fileReader = new FileReader();
        ArrayList<String> lines = fileReader.readFile(new File(fileName));
        for (String line : lines) {
            String[] parts = line.split(",");
            String countyName = parts[0];
            int votersCount = Integer.parseInt(parts[1]);
            registeredVoters.put(countyName, votersCount);
            countyTable.put(countyName, new County(countyName, 0, 0, 0));
        }
    }

    /**
     * Create a list of voting records from a list of strings
     *
     * @param lines The list of strings
     */
    public ArrayList<VotingRecord> createVotingRecords(ArrayList<String> lines) {
        int lineNumber = 1;
        ArrayList<VotingRecord> votingRecords = new ArrayList<>();
        for (String line : lines) {
            try {
                votingRecords.add(VotingRecord.createFromString(line));
            } catch (InvalidVotingRecordException e) {
                errorHandler.reportInvalidRecord(lineNumber);
            }

            lineNumber++;
        }

        return votingRecords;
    }

    @Override
    public void handleEvent(Object object) {
        // Reset the error handler
        this.errorHandler = new ErrorHandler();

        // Reset any previous data
        countyTable.clear();
        for (String countyName : (registeredVoters.keySet())) {
            countyTable.put(countyName, new County(countyName, 0, 0, 0));
        }

        // Read contents from directory
        File directory = (File) object;
        FileReader fileReader = new FileReader();
        ArrayList<String> lines = fileReader.readDirectory(directory);

        // Create the county table (countyName that maps to County)
        ArrayList<VotingRecord> votingRecords = createVotingRecords(lines);
        ArrayList<County> counties = createCountyList(votingRecords);

        // Notify observers and send countyTable
        EventBus.fire("votingRecordsParsed", counties);
    }
}
