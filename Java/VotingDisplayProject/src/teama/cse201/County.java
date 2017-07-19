package teama.cse201;

public class County implements Comparable<County> {
    String name;
    int democratVotes, otherVotes, republicanVotes;
    boolean isIllegal = false;

    public County(String name, int democratVotes, int otherVotes, int republicanVotes) {
        this.name = name;
        this.democratVotes = democratVotes;
        this.otherVotes = otherVotes;
        this.republicanVotes = republicanVotes;
    }


    public int totalVotes() {
        return democratVotes + republicanVotes + otherVotes;
    }

    @Override
    public String toString() {
        String str = name;
        if (isIllegal) {
            str = "*" + name;
        }
        return str;
    }


    @Override
    public int compareTo(County o) {
        return this.name.compareTo(o.name);
    }
}
