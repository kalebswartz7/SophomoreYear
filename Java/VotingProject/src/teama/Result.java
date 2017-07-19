package teama;

public class Result {
    public int democratVotes, republicanVotes, otherVotes;

    public Result() {
        democratVotes = 0;
        republicanVotes = 0;
        otherVotes = 0;
    }

    public int total() {
        return democratVotes + republicanVotes + otherVotes;
    }

    public String percentDemocrat() {
        return String.format("%.2f%%", total() > 0 ? democratVotes / total() * 100 : 0.0);
    }

    public String percentRepublican() {
        return String.format("%.2f%%", total() > 0 ? republicanVotes / total() * 100 : 0.0);
    }

    public String percentOther() {
        return String.format("%.2f%%", total() > 0 ? otherVotes / total() * 100 : 0.0);
    }

    public String democratVotes() {
        return Integer.toString(democratVotes);
    }

    public String republicanVotes() {
        return Integer.toString(republicanVotes);
    }

    public String otherVotes() {
        return Integer.toString(otherVotes);
    }
}
