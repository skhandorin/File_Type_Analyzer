package analyzer;

public class Pattern {

    private int priority;
    private String pattern;
    private String fileType;
    private long hash;

    public Pattern(int priority, String pattern, String fileType) {
        this.priority = priority;
        this.pattern = pattern;
        this.fileType = fileType;

        this.hash = RabinKarp.getHash(pattern);
    }

    public int getPriority() {
        return priority;
    }

    public String getPattern() {
        return pattern;
    }

    public String getFileType() {
        return fileType;
    }

    public long getHash() {
        return hash;
    }
}
