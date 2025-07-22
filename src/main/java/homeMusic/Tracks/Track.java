package homeMusic.Tracks;

public abstract class Track {
    public String title;
    public String artist;
    public String url;

    public abstract String[] extractUrlInformation(String url);
}
