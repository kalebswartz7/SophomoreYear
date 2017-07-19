package teama;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class VotingRecord {
    /**
     * Vote counts.
     */
    private int democratVotes, republicanVotes, otherVotes;

    /**
     * Location names.
     */
    private String precinctName, countyName;

    /**
     * Constructor.
     */
    public VotingRecord(String countyName, String precinctName,
                        int democratVotes, int otherVotes, int republicanVotes) {
        this.countyName = countyName;
        this.precinctName = precinctName;
        this.democratVotes = democratVotes;
        this.republicanVotes = republicanVotes;
        this.otherVotes = otherVotes;
    }

    /**
     * Regex for an integer.
     */
    private static Pattern voteCountPattern = Pattern.compile("[0-9]+");

    /**
     * Regex for a string with letters only.
     */
    private static Pattern countyNamePattern = Pattern.compile("[a-zA-Z]+");

    /**
     * Regex for a string with letters or periods.
     */
    private static Pattern countyIdentifierPattern = Pattern.compile("[a-zA-Z0-9.\\s]+");

    /**
     * Constructor to create record from a string.
     *
     * @param line The string.
     */
    public static VotingRecord createFromString(String line) throws InvalidVotingRecordException {
        String[] elements = line.split(",");

        // Ensure there are the correct amount of elements on each line.
        boolean valid = elements.length == 5;

        // Ensure there are no special characters in the county name.
        valid = valid && checkCountyName(elements[0]);

        // Ensure county identifier has numbers, letters, or
        // periods, but not trailing or leading periods.
        valid = valid && checkCountyIdentifier(elements[1]);

        // Ensure the voting counts are integers.
        valid = valid && checkVoteCount(elements[2])
                && checkVoteCount(elements[3])
                && checkVoteCount(elements[4]);

        if (!valid) {
            throw new InvalidVotingRecordException();
        }

        return new VotingRecord(elements[0], elements[1], Integer.parseInt(elements[2]),
                Integer.parseInt(elements[3]), Integer.parseInt(elements[4]));
    }

    /**
     * Checks for validity in the county name, i.e. no special characters
     *
     * @param countyName The name of the county
     * @return The countyName validity.
     */
    private static boolean checkCountyName(String countyName) {
        Matcher matcher = countyNamePattern.matcher(countyName);
        return matcher.matches();
    }

    /**
     * Check that the county identifier contains letters
     *
     * @param str The county identifier
     * @return The county identifier validity.
     */
    private static boolean checkCountyIdentifier(String str) {
        Matcher matcher = countyIdentifierPattern.matcher(str);
        return matcher.matches() && !str.startsWith(".") && !str.endsWith(".");
    }

    /**
     * Check that the vote count is a valid integer.
     *
     * @param voteCount the string to validate.
     * @return The voteCount validity.
     */
    private static boolean checkVoteCount(String voteCount) {
        Matcher matcher = voteCountPattern.matcher(voteCount);
        return matcher.matches();
    }

    public int getDemocratVotes() {
        return democratVotes;
    }

    public int getRepublicanVotes() {
        return republicanVotes;
    }

    public int getOtherVotes() {
        return otherVotes;
    }

    public String getPrecinctName() {
        return precinctName;
    }

    public String getCountyName() {
        return countyName;
    }
}
